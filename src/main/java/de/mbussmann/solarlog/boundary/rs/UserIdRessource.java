/*
 *
 * Copyright 2021 - Manuel Bußmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package de.mbussmann.solarlog.boundary.rs;

import de.mbussmann.solarlog.boundary.dto.UserDto;
import de.mbussmann.solarlog.control.UserService;
import de.mbussmann.solarlog.util.exceptions.UserException;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Manuel Bußmann
 */
@RequestScoped
@Path("/user/{id}")
public class UserIdRessource {
    
    @Inject
    JsonWebToken token;

    @Inject
    UserService service;

    @PUT
    @RolesAllowed({"ADMINISTRATOR"})
    @SecurityRequirement(name = "apiKey")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Update User Role",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = SchemaType.OBJECT, implementation = UserDto.class))
                    ),
                    @APIResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "text/plain")
                    )
            }
    )
    @Operation(
            summary = "changeUserRole",
            description = "Change User Role by Id")
    @Tag(name = "User", description = "User API")
    public Response changeUserRole(
            @Parameter(
                    description = "User Id",
                    required = true, example = "1",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id,
            @Parameter(
                    description = "User Object",
                    required = true,
                    schema = @Schema(type = SchemaType.OBJECT))
            @RequestBody UserDto userDto) {
        Long own_id = Long.valueOf(token.getClaim("id"));
        if(id.equals(userDto.getId())) {
            if(service.checkUserAdmin(own_id)) {
                try{
                    this.service.changeUserRole(userDto);
                }catch(UserException e) {
                    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
                }
                return Response.ok(userDto).type(MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @DELETE
    @RolesAllowed({"ADMINISTRATOR"})
    @SecurityRequirement(name = "apiKey")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Remove User",
                            content = @Content(mediaType = "text/plain")
                    ),
                    @APIResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "text/plain")
                    )
            }
    )
    @Operation(
            summary = "deleteUser",
            description = "Delete Own loggedin User")
    @Tag(name = "User", description = "User API")
    public Response deleteUser(
            @Parameter(
                    description = "User Id",
                    required = true, example = "1",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id) {
        Long own_id = Long.valueOf(token.getClaim("id"));
        if(service.pruefUser(id)) {
            if(service.checkUserAdmin(own_id)) {
                try {
                    this.service.deleteUser(id);
                    return Response.ok("Delete Successfull").type(MediaType.TEXT_PLAIN).build();
                } catch (UserException e) {
                    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
                }
            }
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
