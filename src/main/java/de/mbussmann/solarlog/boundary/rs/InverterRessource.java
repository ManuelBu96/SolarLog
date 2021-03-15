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

import de.mbussmann.solarlog.boundary.dto.InverterDto;
import de.mbussmann.solarlog.boundary.dto.SystemRespDto;
import de.mbussmann.solarlog.control.InverterService;
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
@Path("/inverter")
public class InverterRessource {
    @Inject
    JsonWebToken token;

    @Inject
    InverterService service;

    @Inject
    SystemService systemService;

    @GET
    @RolesAllowed({"ADMINISTRATOR", "USER"})
    @SecurityRequirement(name = "apiKey")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Inverter List",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(
                                    type = SchemaType.ARRAY,
                                    implementation = SystemRespDto.class)))})
    @Operation(
            summary = "getAllInverter",
            description = "Get all Inverter")
    @Tag(name = "Inverter", description = "Inverter API")
    public Response getAllInverter(
            @Parameter(
                    description = "System Id",
                    required = true,
                    example = "1",
                    schema = @Schema(type = SchemaType.NUMBER))
            @RequestBody Long systemId) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if(systemService.pruefSystem(systemId)) {
            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(this.service.getInverters(systemId))
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
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
            summary = "createInverter",
            description = "Create a new Inverter")
    @Tag(name = "Inverter", description = "Inverter API")
    public Response createInverter(
            @Parameter(
                    description = "InverterDto Object",
                    required = true,
                    schema = @Schema(type = SchemaType.OBJECT))
            @RequestBody InverterDto newInverter) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if(newInverter.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(systemService.pruefSystem(newInverter.getSystemId())) {
            this.service.createInverter(newInverter);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
