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
import java.sql.Timestamp;

public class SystemRespDto extends SystemDto{
    private Long id;

    /**
     * Default Constructor
     */
    public SystemRespDto() {

    }

    /**
     * Constructor SystemDto
     * @param id {@link System} Id
     * @param name Name of the System
     * @param startedAt Timestamp of the System start
     * @param serial Serial from the SolarLog
     * @param inverters Count for the Inverter
     * @param compensation Compensation for feed-in
     */
    public SystemRespDto(Long id, String name, String folder, Timestamp startedAt, Long serial, Long inverters, Long compensation) {
        super(name, folder, startedAt, serial, inverters, compensation);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
