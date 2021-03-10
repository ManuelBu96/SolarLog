package de.mbussmann.solarlog.control;

import de.mbussmann.solarlog.boundary.dto.AuthenticationDto;
import de.mbussmann.solarlog.boundary.dto.RegistrationDto;
import de.mbussmann.solarlog.util.exceptions.AuthenticationException;

/**
 * @author Manuel Bußmann
 */
public interface AuthenticationService {
    
    String login(AuthenticationDto user) throws AuthenticationException;
    void registerNewUser(RegistrationDto newUser) throws AuthenticationException;
}
