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

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Manuel Bußmann
 */
@Entity
@Table(name = "inverterStatus", schema = "public")
public class InverterStatus {
    @Id
    private Long id;
    @ManyToOne
    private Inverter inverter;
    private Timestamp timestamp;
    @Column(name = "status_code")
    private Long stautsCode;

    public InverterStatus() {

    }

    public InverterStatus(Inverter inverter, Timestamp timestamp, Long stautsCode) {
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.stautsCode = stautsCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inverter getInverter() {
        return inverter;
    }

    public void setInverter(Inverter inverter) {
        this.inverter = inverter;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getStautsCode() {
        return stautsCode;
    }

    public void setStautsCode(Long stautsCode) {
        this.stautsCode = stautsCode;
    }
}
