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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author Manuel Bußmann
 */
@Entity
@Table(name = "inverterData", schema = "public")
public class InverterData {
    @Id
    private Long id;

    @ManyToOne
    private Inverter inverter;

    private Timestamp timestamp;
    private Long pac_Watt;
    private Long pdc_Watt_1;
    private Long pdc_Watt_2;
    private Long pdc_Watt_3;

    public InverterData() {

    }

    public InverterData(Inverter inverter, Timestamp timestamp, Long pac_Watt, Long pdc_Watt_1) {
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.pac_Watt = pac_Watt;
        this.pdc_Watt_1 = pdc_Watt_1;
    }

    public InverterData(Inverter inverter, Timestamp timestamp, Long pac_Watt, Long pdc_Watt_1, Long pdc_Watt_2) {
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.pac_Watt = pac_Watt;
        this.pdc_Watt_1 = pdc_Watt_1;
        this.pdc_Watt_2 = pdc_Watt_2;
    }

    public InverterData(Inverter inverter, Timestamp timestamp, Long pac_Watt, Long pdc_Watt_1, Long pdc_Watt_2, Long pdc_Watt_3) {
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.pac_Watt = pac_Watt;
        this.pdc_Watt_1 = pdc_Watt_1;
        this.pdc_Watt_2 = pdc_Watt_2;
        this.pdc_Watt_3 = pdc_Watt_3;
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

    public Long getPac_Watt() {
        return pac_Watt;
    }

    public void setPac_Watt(Long pac_Watt) {
        this.pac_Watt = pac_Watt;
    }

    public Long getPdc_Watt_1() {
        return pdc_Watt_1;
    }

    public void setPdc_Watt_1(Long pdc_Watt_1) {
        this.pdc_Watt_1 = pdc_Watt_1;
    }

    public Long getPdc_Watt_2() {
        return pdc_Watt_2;
    }

    public void setPdc_Watt_2(Long pdc_Watt_2) {
        this.pdc_Watt_2 = pdc_Watt_2;
    }

    public Long getPdc_Watt_3() {
        return pdc_Watt_3;
    }

    public void setPdc_Watt_3(Long pdc_Watt_3) {
        this.pdc_Watt_3 = pdc_Watt_3;
    }
}
