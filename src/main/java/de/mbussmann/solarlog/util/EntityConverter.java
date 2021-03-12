package de.mbussmann.solarlog.util;

import de.mbussmann.solarlog.boundary.dto.*;
import de.mbussmann.solarlog.entity.*;
import de.mbussmann.solarlog.entity.System;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * utility Class for mapping Entities to their respective DTOs.
 *
 * @author Manuel Bußmann
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
    public UserDto userEntityToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setRole(entity.getRole().toString());
        dto.setId(entity.getId());
        return dto;
    }

    /**
     * Converts a {@link de.mbussmann.solarlog.entity.System} instance to a {@link SystemDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link SystemDto} with values provided by the entity
     */
    public SystemDto systemEntitytoDto(System entity) {
        SystemDto dto = new SystemDto();
        dto.setName(entity.getName());
        dto.setFolder(entity.getFolder());
        dto.setStartedAt(entity.getStartedAt());
        dto.setInverters(entity.getInverters());
        dto.setCompensation(entity.getCompensation());
        return dto;
    }

    /**
     * Converts a {@link StatusCode} instance to a {@link StatusCodeDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link StatusCodeDto} with values provided by the entity
     */
    public StatusCodeDto statusCodeEntitytoDto(StatusCode entity) {
        StatusCodeDto dto = new StatusCodeDto();
        dto.setInverterTyp(entity.getInverterTyp());
        dto.setStautsCode(entity.getStautsCode());
        dto.setText(entity.getText());
        return dto;
    }

    /**
     * Converts a {@link ErrorCode} instance to a {@link ErrorCodeDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link ErrorCodeDto} with values provided by the entity
     */
    public ErrorCodeDto errorCodeEntitytoDto(ErrorCode entity) {
        ErrorCodeDto dto = new ErrorCodeDto();
        dto.setInverterTyp(entity.getInverterTyp());
        dto.setErrorCode(entity.getErrorCode());
        dto.setText(entity.getText());
        return dto;
    }
}
