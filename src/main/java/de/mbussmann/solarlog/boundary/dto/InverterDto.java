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

import de.mbussmann.solarlog.entity.System;

/**
 * @author Manuel Bußmann
 */
public class InverterDto {
    private Long systemId;
    private String serial;
    private String typ;
    private String name;
    private Long strings;
    private Long peak;
    private String orientation;

    /**
     * Deafault Constructor
     */
    public InverterDto() {

    }

    /**
     * Constructor {@link InverterDto}
     * @param systemId {@link System} Id
     * @param serial Serial of the {@link InverterDto}
     * @param typ Type
     * @param name Name
     * @param strings Panel Strings
     * @param peak Production peak
     * @param orientation Panel Orientation
     */
    public InverterDto(Long systemId, String serial, String typ, String name, Long strings, Long peak, String orientation) {
        this.systemId = systemId;
        this.serial = serial;
        this.typ = typ;
        this.name = name;
        this.strings = strings;
        this.peak = peak;
        this.orientation = orientation;
    }

    /**
     * Check whether not empty
     * @return Status if content null
     */
    public boolean isEmpty() {
        return this.systemId == null ||
                this.serial == null ||
                this.typ.isEmpty() ||
                this.name.isEmpty() ||
                this.strings == null ||
                this.peak == null ||
                this.orientation.isEmpty();
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStrings() {
        return strings;
    }

    public void setStrings(Long strings) {
        this.strings = strings;
    }

    public Long getPeak() {
        return peak;
    }

    public void setPeak(Long peak) {
        this.peak = peak;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
