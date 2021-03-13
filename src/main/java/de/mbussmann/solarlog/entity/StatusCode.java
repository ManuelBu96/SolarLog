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

import de.mbussmann.solarlog.boundary.dto.StatusCodeDto;
import javax.persistence.*;

@Entity
@Table(name = "statusCode", schema = "public")
public class StatusCode {
    @Id
    private Long id;
    @Column(name = "inverter_typ")
    private String inverterTyp;
    @Column(name = "status_code")
    private Long stautsCode;
    private String text;

    public StatusCode() {

    }

    public StatusCode(StatusCodeDto statusCodeDto) {
        this.inverterTyp = statusCodeDto.getInverterTyp();
        this.stautsCode = statusCodeDto.getStautsCode();
        this.text = statusCodeDto.getText();
    }

    public StatusCode(String inverterTyp, Long stautsCode, String text) {
        this.inverterTyp = inverterTyp;
        this.stautsCode = stautsCode;
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

    public Long getStautsCode() {
        return stautsCode;
    }

    public void setStautsCode(Long stautsCode) {
        this.stautsCode = stautsCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
