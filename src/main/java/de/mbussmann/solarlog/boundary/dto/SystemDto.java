package de.mbussmann.solarlog.boundary.dto;

import java.sql.Timestamp;

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
