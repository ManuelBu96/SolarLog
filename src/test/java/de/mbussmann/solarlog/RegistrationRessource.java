package de.mbussmann.solarlog;

import static io.restassured.RestAssured.given;
import de.mbussmann.solarlog.boundary.dto.RegistrationDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RegistrationRessource {

    @Test
    public void testPostRegistrationRessource() {
        RegistrationDto dto = new RegistrationDto();
        dto.setEmail("test@test.de");
        dto.setFirstName("Max");
        dto.setLastName("Mustermann");
        dto.setPassword("Test1234");
        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("authentication/register")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}