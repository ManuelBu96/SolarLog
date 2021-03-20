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

import de.mbussmann.solarlog.boundary.dto.InverterDto;

import javax.persistence.*;

/**
 * @author Manuel Bußmann
 */
@Entity
@Table(name = "inverter", schema = "public")
public class Inverter {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "system_id", nullable = false)
    private System system;

    private String serial;
    private String typ;
    private String name;
    private Long strings;
    private Long peak;
    private String orientation;

    public Inverter() {

    }

    public Inverter(System system, InverterDto newInverter) {
        this.system = system;
        this.serial = newInverter.getSerial();
        this.typ = newInverter.getTyp();
        this.name = newInverter.getName();
        this.strings = newInverter.getStrings();
        this.peak = newInverter.getPeak();
        this.orientation = newInverter.getOrientation();
    }

    public Inverter(System system, String serial, String typ, String name, Long strings, Long peak, String orientation) {
        this.system = system;
        this.serial = serial;
        this.typ = typ;
        this.name = name;
        this.strings = strings;
        this.peak = peak;
        this.orientation = orientation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
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
