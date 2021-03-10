package de.mbussmann.solarlog.control;

import de.mbussmann.solarlog.boundary.dto.UserDto;
import de.mbussmann.solarlog.entity.User;
import de.mbussmann.solarlog.util.exceptions.UserException;

/**
 * @author Niklas Meyer
 */
public interface UserService {
    UserDto getUserById(Long id) throws UserException;
    void changeOwnUserData(UserDto userDto) throws UserException;
    void deleteUser(Long id)  throws UserException;
    User getEntity(Long id);
    User getUserByEmail(String email);

}
