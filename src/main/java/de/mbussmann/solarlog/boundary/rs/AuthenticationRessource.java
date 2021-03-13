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

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import de.mbussmann.solarlog.boundary.dto.AuthenticationDto;
import de.mbussmann.solarlog.control.AuthenticationService;
import de.mbussmann.solarlog.util.exceptions.AuthenticationException;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

/**
 * @author Manuel Bußmann
 */
@Path("authentication/login")
@RequestScoped
public class AuthenticationRessource {
    @Inject
    AuthenticationService service;


    @POST
    @PermitAll
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Json Web Token",
                            content = @Content(mediaType = "text/plain")
                    )
            }
    )
    public Response login(
            @Parameter(
                    description = "Authentication User Object",
                    required = true,
                    schema = @Schema(type = SchemaType.OBJECT))
            @RequestBody AuthenticationDto user) {
        try{
            return Response.ok(this.service.login(user)).type(MediaType.TEXT_PLAIN).build();
        }catch(AuthenticationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }
}
