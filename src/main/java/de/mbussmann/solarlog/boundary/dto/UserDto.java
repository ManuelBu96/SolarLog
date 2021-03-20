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

import de.mbussmann.solarlog.entity.UserRole;

/**
 * @author Manuel Bußmann
 */
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String email;

    /**
     * Default Constructor
     */
    public UserDto() {
    }

    /**
     * Constructor
     * @param id UserID
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param role User Role as String
     * @param email User Mail
     */
    public UserDto(Long id, String firstName, String lastName, String role, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
    }

    /**
     * Constructor
     * @param id UserID
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param role User Role as Object
     * @param email User Mail
     */
    public UserDto(Long id, String firstName, String lastName, UserRole role, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role.toString();
        this.email = email;
    }


    /**
     * Get the User ID
     * @return User ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Set the User ID
     * @param id User ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the User First Name
     * @return User FIrst Name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set the User First Name
     * @param firstName User First Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the User Last Name
     * @return User Last Name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the User Last Name
     * @param lastName User Last Name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the User Role as String
     * @return UserRole
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Set the User Role
     * @param role UserRole
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get the User Mail
     * @return User Mail
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the User Mail
     * @param email User Mail
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
