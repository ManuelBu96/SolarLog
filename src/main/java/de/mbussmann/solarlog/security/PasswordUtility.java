package de.mbussmann.solarlog.security;

import java.security.SecureRandom;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility Class for handling password Hashing.
 * 
 * @author Niklas Meyer
 */
@ApplicationScoped
public class PasswordUtility {
    
    /**
     * Number of Hash Rounds the BCrypt Algorith should use
     */
    @ConfigProperty(name = "de.hsos.swa.password.rounds")
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
