package de.mbussmann.solarlog;

import de.mbussmann.solarlog.boundary.dto.AuthenticationDto;
import de.mbussmann.solarlog.boundary.dto.RegistrationDto;

import de.mbussmann.solarlog.boundary.dto.UserDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserRessource {
    private RegistrationDto regUser = new RegistrationDto("test@test.de", "Max", "Mustermann", "test");
    private AuthenticationDto loginUser = new AuthenticationDto("test@test.de", "test");
    private Map<String, String> headers = new HashMap<String,String>();
    private String token;

    @BeforeAll
    public void setup() {
        //Register
        given()
                .contentType(ContentType.JSON)
                .body(regUser)
                .when()
                .post("authentication/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        //Generate the token
        token = given()
                .contentType(ContentType.JSON)
                .body(loginUser)
                .when()
                .post("authentication/login")
                .jsonPath()
                .get("token");

        headers.put("Accept","application/json");
        headers.put("Authorization", "Bearer " + token);
    }

    @Test
    public void testGetUserRessource() {
        given()
                .headers(headers)
                .when()
                .get("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testPutUserRessource() {
        UserDto dto = new UserDto();
        dto.setFirstName("Maximilian");
        dto.setLastName("Mustermann");
        dto.setEmail(loginUser.getEmail());
        dto.setRole("USER");
        given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(dto)
                .put("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDeleteUserRessource() {
        given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body("id:2")
                .delete("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
