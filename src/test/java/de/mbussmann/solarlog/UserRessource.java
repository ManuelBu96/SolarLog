package de.mbussmann.solarlog;

import de.mbussmann.solarlog.boundary.dto.UserDto;
import de.mbussmann.solarlog.entity.UserRole;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserRessource {
    @Inject
    Helper helper;

    @BeforeEach
    public void destruct() {
        helper.destruct();
    }

    @AfterEach
    public void destroy() {
        helper.destroy();
    }

    @Test
    @Order(1)
    @DisplayName("Get User")
    public void testGetUserRessource() {
        helper.loginUser();
        given()
                .headers(helper.getUserHeaders())
                .when()
                .get("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(2)
    @DisplayName("Get Admin")
    public void testGetAdminRessource() {
        helper.loginAdmin();
        given()
                .headers(helper.getAdminHeaders())
                .when()
                .get("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(3)
    @DisplayName("Update User-Data")
    public void testPutUserRessource() {
        helper.loginUser();
        UserDto dto = helper.getUser(helper.getUserHeaders());
        dto.setFirstName("Maximilian");
        dto.setLastName("Lustig");
        given()
                .headers(helper.getUserHeaders())
                .contentType(ContentType.JSON)
                .body(dto)
                .put("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(4)
    @DisplayName("Update User-Role - ADMIN")
    public void testPutUserIdRessource() {
        helper.loginAdmin();
        //User ID 1
        UserDto dto = helper.getUser(helper.getUserHeaders());
        dto.setRole((UserRole.ADMINISTRATOR).toString());
        given()
                .headers(helper.getAdminHeaders())
                .contentType(ContentType.JSON)
                .body(dto)
                .put("/user/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(5)
    @DisplayName("Delete User - ADMIN")
    public void testDeleteUserIdRessource() {
        helper.loginAdmin();
        //User ID 1
        given()
                .headers(helper.getAdminHeaders())
                .contentType(ContentType.JSON)
                .body("id:1")
                .delete("/user/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(6)
    @DisplayName("Delete Own User")
    public void testDeleteUserRessource() {
        helper.loginUser();
        given()
                .headers(helper.getUserHeaders())
                .contentType(ContentType.JSON)
                .body("id:2")
                .delete("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
