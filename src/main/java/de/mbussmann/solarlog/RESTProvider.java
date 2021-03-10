package de.mbussmann.solarlog;

import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

/**
 * Class extends {@link javax.ws.rs.core.Application} and provides Meta-Data
 * for Swagger UI
 * 
 * @author Manuel Bußmann
 */
@OpenAPIDefinition(
    info = @Info(
        title = "SolarLog Manager",
        description = "RestAPI for SolarLog",
        version = "0.0.1"
    )
)
@SecuritySchemes(
    value = {
        @SecurityScheme(
            securitySchemeName = "apiKey",
            type = SecuritySchemeType.HTTP,
            scheme = "bearer",
            apiKeyName = "Authorization: Bearer",
            bearerFormat = "jwt"
        )
    }
)
public class RESTProvider extends Application {
    
}
