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

package de.mbussmann.solarlog.util;

import de.mbussmann.solarlog.boundary.dto.*;
import de.mbussmann.solarlog.entity.*;
import de.mbussmann.solarlog.entity.System;

import javax.enterprise.context.Dependent;
import java.util.List;
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
     * Converts a {@link de.mbussmann.solarlog.entity.System} instance to a {@link SystemRespDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link SystemRespDto} with values provided by the entity
     */
    public SystemRespDto systemEntitytoRespDto(System entity) {
        SystemRespDto dto = new SystemRespDto();
        dto.setName(entity.getName());
        dto.setFolder(entity.getFolder());
        dto.setStartedAt(entity.getStartedAt());
        dto.setInverters(entity.getInverters());
        dto.setCompensation(entity.getCompensation());
        return dto;
    }

    /**
     * Converts a List of {@link de.mbussmann.solarlog.entity.System} instance to a List of {@link SystemRespDto}
     * instance.
     *
     * Uses {@link EntityConverter#systemEntitytoRespDto(de.mbussmann.solarlog.entity.System)} for converting a
     * single instance
     *
     * @param entityList: The List of Entities to be converted
     * @return a new List of {@link SystemRespDto} with values provided by the entities
     */
    public List<SystemRespDto> systemEntityListtoRespDto(List<System> entityList) {
        //return entityList.stream().map(entity -> this.systemEntitytoDto(entity)).collect(Collectors.toList());
        return entityList.stream().map(this::systemEntitytoRespDto).collect(Collectors.toList());
    }

    /**
     * Converts a {@link Inverter} instance to a {@link InverterRespDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link InverterRespDto} with values provided by the entity
     */
    public InverterRespDto inverterEntityRespDto(Inverter entity) {
        InverterRespDto dto = new InverterRespDto();
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
     * Converts a List of {@link Inverter} instance to a List of {@link InverterRespDto}
     * instance.
     *
     * Uses {@link EntityConverter#inverterEntityDto(Inverter)} for converting a
     * single instance
     *
     * @param entityList: The List of Entities to be converted
     * @return a new List of {@link InverterRespDto} with values provided by the entities
     */
    public List<InverterRespDto> inverterEntityListRespDto(List<Inverter> entityList) {
        return entityList.stream().map(this::inverterEntityRespDto).collect(Collectors.toList());
    }

    /**
     * Converts a {@link StatusCode} instance to a {@link StatusCodeRespDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link StatusCodeRespDto} with values provided by the entity
     */
    public StatusCodeRespDto statusCodeEntitytoRespDto(StatusCode entity) {
        StatusCodeRespDto dto = new StatusCodeRespDto();
        dto.setInverterTyp(entity.getInverterTyp());
        dto.setStautsCode(entity.getStautsCode());
        dto.setText(entity.getText());
        return dto;
    }

    /**
     * Converts a List of {@link StatusCode} instance to a List of {@link StatusCodeRespDto}
     * instance.
     *
     * Uses {@link EntityConverter#statusCodeEntitytoRespDto(StatusCode)} for converting a
     * single instance
     *
     * @param entityList: The List of Entities to be converted
     * @return a new List of {@link StatusCodeRespDto} with values provided by the entities
     */
    public List<StatusCodeRespDto> statusCodeEntityListtoRespDto(List<StatusCode> entityList) {
        return entityList.stream().map(this::statusCodeEntitytoRespDto).collect(Collectors.toList());
    }

    /**
     * Converts a {@link ErrorCode} instance to a {@link ErrorCodeRespDto} instance
     *
     * @param entity: The Entity to be converted
     * @return a new {@link ErrorCodeRespDto} with values provided by the entity
     */
    public ErrorCodeRespDto errorCodeEntitytoRespDto(ErrorCode entity) {
        ErrorCodeRespDto dto = new ErrorCodeRespDto();
        dto.setInverterTyp(entity.getInverterTyp());
        dto.setErrorCode(entity.getErrorCode());
        dto.setText(entity.getText());
        return dto;
    }

    /**
     * Converts a List of {@link ErrorCode} instance to a List of {@link ErrorCodeRespDto}
     * instance.
     *
     * Uses {@link EntityConverter#errorCodeEntitytoRespDto(ErrorCode)} for converting a
     * single instance
     *
     * @param entityList: The List of Entities to be converted
     * @return a new List of {@link ErrorCodeRespDto} with values provided by the entities
     */
    public List<ErrorCodeRespDto> errorCodeEntityListtoRespDto(List<ErrorCode> entityList) {
        return entityList.stream().map(this::errorCodeEntitytoRespDto).collect(Collectors.toList());
    }
}
