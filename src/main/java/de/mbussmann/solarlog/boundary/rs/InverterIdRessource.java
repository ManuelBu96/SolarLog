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
import de.mbussmann.solarlog.boundary.dto.InverterRespDto;
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
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Manuel Bußmann
 */
@RequestScoped
@Path("/inverter/{id}")
public class InverterIdRessource {
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
                            description = "Single Inverter",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            type = SchemaType.OBJECT,
                                            implementation = SystemRespDto.class))),
                    @APIResponse(
                            responseCode = "403",
                            description = "Forbidden") })
    @Operation(
            summary = "getInverter",
            description = "Get Inverter by Id")
    @Tag(name = "Inverter", description = "Inverter API")
    public Response getInverter(
            @Parameter(
                    description = "Inverter Id",
                    required = true, example = "1",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if(this.service.pruefInverter(id)) {
            InverterRespDto inverter = this.service.getInverter(id);
            if(inverter != null) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON).entity(inverter)
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
            summary = "updateInverter",
            description = "Update Inverter by Id")
    @Tag(name = "Inverter", description = "Inverter API")
    public Response changeInverter(
            @Parameter(
                    description = "Inverter Id",
                    required = true, example = "1",
                    schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id,
            @Parameter(
                    description = "InverterDto Object to update",
                    required = true,
                    schema = @Schema(type = SchemaType.OBJECT))
            @RequestBody InverterDto updateInverter) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if(updateInverter.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(systemService.pruefSystem(updateInverter.getSystemId())) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(service.pruefInverter(id)){
            this.service.updateInverter(id, updateInverter);
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
            summary = "removeInverter",
            description = "Delete Inverter by Id")
    @Tag(name = "Inverter", description = "Inverter API")
    public Response deleteInverter(
            @Parameter(
                description = "Inverter Id",
                required = true, example = "1",
                schema = @Schema(type = SchemaType.NUMBER))
            @PathParam("id") Long id) {
        Long u_id = Long.valueOf(token.getClaim("id"));
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(service.pruefInverter(id)) { //System exist
            if(this.service.removeInverter(id)) {
                return Response.status(Response.Status.OK).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
