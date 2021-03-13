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

package de.mbussmann.solarlog.boundary.dto;

import java.io.Serializable;

/**
 * @author Manuel Bußmann
 */
public class AuthenticationDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private String password;

    /**
     * Default Constructor
     */
    public AuthenticationDto() {
    }

    /**
     * Constructor
     * @param email User Email
     * @param password User Password
     */
    public AuthenticationDto(String email, String password) {
        this.email = email;
        this.password = password;
    }


    /**
     * Get the Authentication EMail
     * @return EMail
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the Authentication EMail
     * @return EMail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the Authentication Password
     * @return Password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set the Authentication Password
     * @return Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
