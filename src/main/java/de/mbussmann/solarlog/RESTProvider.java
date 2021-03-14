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

package de.mbussmann.solarlog;

import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
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
                version = "0.0.1",
                contact = @Contact(
                        name = "Example API Support",
                        url = "https://github.com/ManuelBu96/SolarLog"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html")
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
