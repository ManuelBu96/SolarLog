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
 * @author Niklas Meyer
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
