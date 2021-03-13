package de.mbussmann.solarlog.boundary.dto;

import de.mbussmann.solarlog.entity.System;

public class InverterDto {
    private System system;
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
     * @param system {@link System}
     * @param serial Serial of the {@link InverterDto}
     * @param typ Type
     * @param name Name
     * @param strings Panel Strings
     * @param peak Production peak
     * @param orientation Panel Orientation
     */
    public InverterDto(System system, String serial, String typ, String name, Long strings, Long peak, String orientation) {
        this.system = system;
        this.serial = serial;
        this.typ = typ;
        this.name = name;
        this.strings = strings;
        this.peak = peak;
        this.orientation = orientation;
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
