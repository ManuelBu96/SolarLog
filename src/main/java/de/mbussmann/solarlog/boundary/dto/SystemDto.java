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

import java.sql.Timestamp;

/**
 * @author Manuel Bußmann
 */
public class SystemDto {
    private String name;
    private String folder;
    private Timestamp startedAt;
    private Long serial;
    private Long inverters;
    private Long compensation;

    /**
     * Default Constructor
     */
    public SystemDto() {

    }

    /**
     * Constructor SystemDto
     * @param name Name of the System
     * @param startedAt Timestamp of the System start
     * @param serial Serial from the SolarLog
     * @param inverters Count for the Inverter
     * @param compensation Compensation for feed-in
     */
    public SystemDto(String name, String folder, Timestamp startedAt, Long serial, Long inverters, Long compensation) {
        this.name = name;
        this.folder = folder;
        this.startedAt = startedAt;
        this.serial = serial;
        this.inverters = inverters;
        this.compensation = compensation;
    }

    /**
     * Check whether not empty
     * @return Status if content null
     */
    public boolean isEmpty() {
        return this.name.isEmpty() ||
                this.folder.isEmpty() ||
                this.inverters == null ||
                this.compensation == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Long getInverters() {
        return inverters;
    }

    public void setInverters(Long inverters) {
        this.inverters = inverters;
    }

    public Long getCompensation() {
        return compensation;
    }

    public void setCompensation(Long compensation) {
        this.compensation = compensation;
    }
}
