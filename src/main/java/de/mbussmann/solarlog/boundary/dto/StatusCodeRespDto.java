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
public class StatusCodeRespDto extends StatusCodeDto{
    private Long id;

    /**
     * Default Constructor
     */
    public StatusCodeRespDto() {

    }

    /**
     * Constructor StatusCodeDto
     * @param id {@link de.mbussmann.solarlog.entity.StatusCode} Id
     * @param inverterTyp String of the Inverter-Type
     * @param stautsCode number to identify
     * @param text StautsCode Text
     */
    public StatusCodeRespDto(Long id, String inverterTyp, Long stautsCode, String text) {
        super(inverterTyp, stautsCode, text);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
