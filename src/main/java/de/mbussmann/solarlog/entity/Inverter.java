package de.mbussmann.solarlog.entity;

import de.mbussmann.solarlog.boundary.dto.InverterDto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inverter", schema = "public")
public class Inverter {
    @Id
    private Long id;

    @ManyToOne
    private System system;

    private String serial;
    private String typ;
    private String name;
    private Long strings;
    private Long peak;
    private String orientation;

    public Inverter() {

    }

    public Inverter(InverterDto inverterDto) {
        this.system = inverterDto.getSystem();
        this.serial = inverterDto.getSerial();
        this.typ = inverterDto.getTyp();
        this.name = inverterDto.getName();
        this.strings = inverterDto.getStrings();
        this.peak = inverterDto.getPeak();
        this.orientation = inverterDto.getOrientation();
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
