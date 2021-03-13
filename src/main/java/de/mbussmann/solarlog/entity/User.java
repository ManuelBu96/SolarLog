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

package de.mbussmann.solarlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Manuel Bußmann
 */
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq_seq", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String email;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    /**
     * Default Constructor
     */
    public User() {
    }

    /**
     * Get the User ID
     * @return UserID
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the User E-Mail
     * @return User E-Mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the User First Name
     * @return User First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the User Last Name
     * @return User Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the User Password
     * @return User Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the User Role
     * @return User Role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Set the User Id
     * @param id UserID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set the User E-Mail
     * @param email User E-Mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the User First Name
     * @param firstName User First Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set the User Last Name
     * @param lastname User Last Name
     */
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    /**
     * Set the User Password
     * @param password User Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the User Role
     * @param role User Role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Check if the User is the same
     * @param obj User Object
     * @return Status if the User Objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj.getClass() != this.getClass()) {
            return false;
        }
        final User otherUser = (User) obj;
        return otherUser.getId().equals(this.getId());
    }
}

