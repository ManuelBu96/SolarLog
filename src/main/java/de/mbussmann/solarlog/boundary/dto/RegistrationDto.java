package de.mbussmann.solarlog.boundary.dto;

import java.io.Serializable;

/**
 * @author Manuel Bußmann
 */
public class RegistrationDto implements Serializable{

    private static final long serialVersionUID = 1L;
    private String email;
    private String lastName;
    private String firstName;
    private String password;

    /**
     * Default Constructor
     */
    public RegistrationDto() {
    }

    /**
     * Constructor
     * @param email mail adresse
     * @param lastName last name
     * @param firstName first name
     * @param password Password
     */
    public RegistrationDto(String email, String lastName, String firstName, String password) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
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
     * Get the User First Name
     * @return User First Name
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
     * Get the User Password
     * @return User Password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set the User Password
     * @param password User Password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
