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
     * Converts a List of {@link de.mbussmann.solarlog.entity.System} instance to a List of {@link SystemDto}
     * instance.
     *
     * Uses {@link EntityConverter#systemEntitytoDto(de.mbussmann.solarlog.entity.System)} for converting a
     * single instance
     *
     * @param entityList: The List of Entities to be converted
     * @return a new List of {@link SystemDto} with values provided by the entities
     */
    public List<SystemDto> systemEntityListtoDto(List<System> entityList) {
        //return entityList.stream().map(entity -> this.systemEntitytoDto(entity)).collect(Collectors.toList());
        return entityList.stream().map(this::systemEntitytoDto).collect(Collectors.toList());
    }

    /**
     * Converts a {@link Inverter} instance to a {@link InverterDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link InverterDto} with values provided by the entity
     */
    public InverterDto inverterEntityDto(Inverter entity) {
        InverterDto dto = new InverterDto();
        dto.setSystem(entity.getSystem());
        dto.setSerial(entity.getSerial());
        dto.setTyp(entity.getTyp());
        dto.setName(entity.getName());
        dto.setStrings(entity.getStrings());
        dto.setPeak(entity.getPeak());
        dto.setOrientation(entity.getOrientation());
        return dto;
    }

    /**
     * Converts a List of {@link Inverter} instance to a List of {@link InverterDto}
     * instance.
     *
     * Uses {@link EntityConverter#inverterEntityDto(Inverter)} for converting a
     * single instance
     *
     * @param entityList: The List of Entities to be converted
     * @return a new List of {@link InverterDto} with values provided by the entities
     */
    public List<InverterDto> inverterEntityListDto(List<Inverter> entityList) {
        return entityList.stream().map(this::inverterEntityDto).collect(Collectors.toList());
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
