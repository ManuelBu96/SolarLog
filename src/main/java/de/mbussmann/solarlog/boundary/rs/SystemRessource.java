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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Manuel Bußmann
 */
@RequestScoped
@Path("/system")
public class SystemRessource {
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
                    description = "System List",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(
                                    type = SchemaType.ARRAY,
                                    implementation = SystemRespDto.class)))})
    @Operation(
            summary = "getAllSystem",
            description = "Get all System")
    @Tag(name = "System", description = "System API")
    public Response getAllSystem() {
        Long u_id = Long.valueOf(token.getClaim("id"));
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(this.service.getSystems())
                .build();
    }

    @POST
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @SecurityRequirement(name = "apiKey")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "204",
                    description = "Create Successfully"),
            @APIResponse(
                    responseCode = "400",
                    description = "Bad Request") })
    @Operation(
            summary = "createSystem",
            description = "Create a new System")
    @Tag(name = "System", description = "System API")
    public Response createArticle(
            @Parameter(
                    description = "SystemDto Object",
                    required = true,
                    schema = @Schema(type = SchemaType.OBJECT))
            @RequestBody SystemDto newSystem) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if(newSystem.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        this.service.createSystem(newSystem);
        return Response.ok().build();
    }
}
