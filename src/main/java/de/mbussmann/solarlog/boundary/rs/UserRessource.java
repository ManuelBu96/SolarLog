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

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import de.mbussmann.solarlog.boundary.dto.UserDto;
import de.mbussmann.solarlog.control.UserService;
import de.mbussmann.solarlog.util.exceptions.UserException;

/**
 * @author Manuel Bußmann
 */
@RequestScoped
@Path("/user")
public class UserRessource {
    
    @Inject
    JsonWebToken token;

    @Inject
    UserService service;

    @GET
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Get User Data",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = SchemaType.OBJECT, implementation = UserDto.class))
                    )
            }
    )
    @SecurityRequirement(name = "apiKey")
    public Response getUserById() {
        Long id = Long.valueOf(token.getClaim("id"));
        if(id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.OK)
                .entity(this.service.getUserById(id))
                .type(MediaType.APPLICATION_JSON).build();
        } catch (UserException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }

    @PUT
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Update User Data",
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
    @SecurityRequirement(name = "apiKey")
    public Response changeOwnData(
            @Parameter(
                    description = "User Object",
                    required = true,
                    schema = @Schema(type = SchemaType.OBJECT))
            @RequestBody UserDto userDto) {
        Long id = Long.valueOf(token.getClaim("id"));
        if(id !=  userDto.getId()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("CANNOT CHANGE ID").type(MediaType.TEXT_PLAIN).build();
        }
        try{
            this.service.changeOwnUserData(userDto);
        }catch(UserException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
        return Response.ok(userDto).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Update User Data",
                            content = @Content(mediaType = "text/plain")
                    ),
                    @APIResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "text/plain")
                    )
            }
    )
    @SecurityRequirement(name = "apiKey")
    public Response deleteOwnUser() {
        Long id = Long.valueOf(token.getClaim("id"));
        try{
            this.service.deleteUser(id);
            return Response.ok("Delete Successfull").type(MediaType.TEXT_PLAIN).build();
        }catch(UserException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }
}
