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

import de.mbussmann.solarlog.boundary.dto.AuthenticationDto;
import de.mbussmann.solarlog.boundary.dto.RegistrationDto;
import de.mbussmann.solarlog.boundary.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Class for Testing algorithm
 *
 * @author Manuel Bußmann
 */
@ApplicationScoped
public class Helper {
    @Inject
    Flyway flyway;

    private Map<String, String> userHeaders = new HashMap<String,String>();
    private Map<String, String> adminHeaders = new HashMap<String,String>();
    private String userToken;
    private String adminToken;
    private RegistrationDto regUser = new RegistrationDto("test@test.de", "Max", "Mustermann", "test");
    private AuthenticationDto loginUser = new AuthenticationDto("test@test.de", "test");
    private AuthenticationDto loginAdmin = new AuthenticationDto("admin@admin.com", "admin");

    public void loginUser() {
        //Generate the User token
        userToken = given()
                .contentType(ContentType.JSON)
                .body(loginUser)
                .when()
                .post("authentication/login")
                .getBody()
                .asString();

        userHeaders.put("Accept","application/json");
        userHeaders.put("Authorization", "Bearer " + userToken);
    }

    public void loginAdmin() {
        //Generate the token
        adminToken = given()
                .contentType(ContentType.JSON)
                .body(loginAdmin)
                .when()
                .post("authentication/login")
                .getBody()
                .asString();

        adminHeaders.put("Accept","application/json");
        adminHeaders.put("Authorization", "Bearer " + adminToken);
    }

    public UserDto getUser(Map<String, String> header) {
        this.loginUser();
        ValidatableResponse res = given()
                .headers(userHeaders)
                .when()
                .get("/user")
                .then()
                .statusCode(HttpStatus.SC_OK);
        return res.extract().body().as(UserDto.class);
    }

    public void destruct() {
        //Reset Database
        flyway.clean();
        flyway.migrate();
        //Register User
        given()
                .contentType(ContentType.JSON)
                .body(regUser)
                .when()
                .post("authentication/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    public void destroy() {
        userHeaders.clear();
        adminHeaders.clear();
        userToken = "";
        adminToken = "";
    }

    public Map<String, String> getUserHeaders() {
        return userHeaders;
    }

    public Map<String, String> getAdminHeaders() {
        return adminHeaders;
    }

    public RegistrationDto getRegUser() {
        return regUser;
    }

    public void setRegUser(RegistrationDto regUser) {
        this.regUser = regUser;
    }

    public AuthenticationDto getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(AuthenticationDto loginUser) {
        this.loginUser = loginUser;
    }

    public AuthenticationDto getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(AuthenticationDto loginAdmin) {
        this.loginAdmin = loginAdmin;
    }
}
