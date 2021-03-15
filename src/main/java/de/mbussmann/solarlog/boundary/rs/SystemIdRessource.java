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

import de.mbussmann.solarlog.boundary.dto.SystemDto;
import de.mbussmann.solarlog.boundary.dto.SystemRespDto;
import de.mbussmann.solarlog.control.SystemService;
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
@Path("/system/{id}")
public class SystemIdRessource {
    @Inject
    JsonWebToken token;

    @Inject
    SystemService service;

    @GET
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @SecurityRequirement(name = "apiKey")
    @APIResponses(value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Single System",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            type = SchemaType.OBJECT,
                                            implementation = SystemRespDto.class))),
                    @APIResponse(
                            responseCode = "403",
                            description = "Forbidden") })
    @Operation(
            summary = "getSystem",
            description = "Get System by Id")
    @Tag(name = "System", description = "System API")
    public Response getSystem(
            @Parameter(
                    description = "System Id",
                    required = true, example = "1",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if(this.service.pruefSystem(id)) {
            SystemRespDto system = this.service.getSystem(id);
            if(system != null) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON).entity(system)
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @SecurityRequirement(name = "apiKey")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "204",
                            description = "Update Successfull"),
                    @APIResponse(
                            responseCode = "400",
                            description = "Bad Request") })
    @Operation(
            summary = "updateSystem",
            description = "Update System by Id")
    @Tag(name = "System", description = "System API")
    public Response changeSystem(
            @Parameter(
                    description = "System Id",
                    required = true, example = "1",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id,
            @Parameter(
                    description = "SystemDto Object to update",
                    required = true,
                    schema = @Schema(type = SchemaType.OBJECT))
            @RequestBody SystemDto updateSystem) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if(updateSystem.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(service.pruefSystem(id)){
            this.service.updateSystem(id, updateSystem);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @SecurityRequirement(name = "apiKey")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "204",
                            description = "Remove Successfull"),
                    @APIResponse(responseCode = "400",
                            description = "Bad Request") })
    @Operation(
            summary = "removeSystem",
            description = "Delete System by Id")
    @Tag(name = "System", description = "System API")
    public Response deleteSystem(
            @Parameter(
                description = "System Id",
                required = true, example = "1",
                schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(service.pruefSystem(id)) { //System exist
            if(this.service.removeSystem(id)) {
                return Response.status(Response.Status.OK).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
