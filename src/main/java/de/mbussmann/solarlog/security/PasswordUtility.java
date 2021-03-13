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

package de.mbussmann.solarlog.security;

import java.security.SecureRandom;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility Class for handling password Hashing.
 *
 * @author Manuel Bußmann
 */
@ApplicationScoped
public class PasswordUtility {
    
    /**
     * Number of Hash Rounds the BCrypt Algorith should use
     */
    @ConfigProperty(name = "de.mbussmann.solarlog.rounds")
    private int numberOfRounds;


    
    /**
     * Generates the Hash of the user password using BCrypt.
     * 
     * @param userPassword the password the user entered
     * @return String
     */
    public String hashPassword(String userPassword) {
        if(userPassword == null || userPassword.isEmpty()) {
            return null;
        }
        SecureRandom random = new SecureRandom();
        return BCrypt.hashpw(userPassword, BCrypt.gensalt(this.numberOfRounds, random));
    }

    
    /** 
     * Checks if the entered Password matches the Hashed Password stored in the Database
     * 
     * @param providedPassword the password the user entered
     * @param hashedPassword the hashed password stored in the database
     * @return boolean
     */
    public boolean isPasswordValid(String providedPassword, String hashedPassword) {
        if(providedPassword == null || providedPassword.isEmpty()) {
            return false;
        }
        if(hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(providedPassword, hashedPassword);
    }
}
