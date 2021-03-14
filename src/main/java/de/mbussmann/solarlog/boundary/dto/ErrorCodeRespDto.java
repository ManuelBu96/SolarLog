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
public class ErrorCodeRespDto {
    private Long id;
    private String inverterTyp;
    private Long errorCode;
    private String text;

    /**
     * Default Constructor
     */
    public ErrorCodeRespDto() {

    }

    /**
     * Constructor ErrorCodeDto
     * @param id {@link de.mbussmann.solarlog.entity.ErrorCode} Id
     * @param inverterTyp String of the Inverter-Type
     * @param errorCode number to identify
     * @param text ErrorCode Text
     */
    public ErrorCodeRespDto(Long id, String inverterTyp, Long errorCode, String text) {
        this.inverterTyp = inverterTyp;
        this.errorCode = errorCode;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInverterTyp() {
        return inverterTyp;
    }

    public void setInverterTyp(String inverterTyp) {
        this.inverterTyp = inverterTyp;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
