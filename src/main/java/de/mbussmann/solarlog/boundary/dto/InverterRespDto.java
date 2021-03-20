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

package de.mbussmann.solarlog.boundary.dto;

/**
 * @author Manuel Bußmann
 */
public class InverterRespDto extends InverterDto {
    private Long id;

    /**
     * Deafault Constructor
     */
    public InverterRespDto() {

    }

    /**
     * Constructor {@link InverterRespDto}
     * @param id {@link de.mbussmann.solarlog.entity.Inverter} Id
     * @param systemId {@link System} Id
     * @param serial Serial of the {@link InverterRespDto}
     * @param typ Type
     * @param name Name
     * @param strings Panel Strings
     * @param peak Production peak
     * @param orientation Panel Orientation
     */
    public InverterRespDto(Long id, Long systemId, String serial, String typ, String name, Long strings, Long peak, String orientation) {
        super(systemId,serial,typ,name,strings,peak,orientation);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
