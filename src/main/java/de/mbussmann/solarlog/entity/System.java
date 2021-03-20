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

package de.mbussmann.solarlog.entity;

import de.mbussmann.solarlog.boundary.dto.SystemDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author Manuel Bußmann
 */
@Entity
@Table(name = "system", schema = "public")
public class System {
    @Id
    private Long id;

    private String name;
    private String folder;
    @Column(name = "started_at")
    private Timestamp startedAt;
    private Long serial;
    private Long inverters;
    private Long compensation;

    public System() {

    }

    public System(SystemDto systemDto) {
        this.name = systemDto.getName();
        this.folder = systemDto.getFolder();
        this.startedAt = systemDto.getStartedAt();
        this.serial = systemDto.getSerial();
        this.inverters = systemDto.getInverters();
        this.compensation = systemDto.getCompensation();
    }

    public System(String name, String folder, Long inverters, Long compensation) {
        this.name = name;
        this.folder = folder;
        this.inverters = inverters;
        this.compensation = compensation;
    }

    public System(String name, String folder, Timestamp startedAt, Long serial, Long inverters, Long compensation) {
        this.name = name;
        this.folder = folder;
        this.startedAt = startedAt;
        this.serial = serial;
        this.inverters = inverters;
        this.compensation = compensation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
