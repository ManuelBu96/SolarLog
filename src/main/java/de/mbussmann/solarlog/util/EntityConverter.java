package de.mbussmann.solarlog.util;

import de.mbussmann.solarlog.boundary.dto.*;
import de.mbussmann.solarlog.entity.*;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * utility Class for mapping Entities to their respective DTOs.
 * 
 * @author Manuel Bussmann
 */
@Dependent
public class EntityConverter {

    public EntityConverter() {
    }

    /**
     * Converts a {@link User} instance to a {@link UserDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link UserDto} with values provided by the entity
     */
    /*
    public UserDto userEntityToDtp(User entity) {
        UserDto dto = new UserDto();
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setRole(entity.getRole().toString());
        dto.setId(entity.getId());
        dto.setIsChatEnabled(entity.isChatEnabled() != null ? entity.isChatEnabled() : false);
        return dto;
    }
    */
}
