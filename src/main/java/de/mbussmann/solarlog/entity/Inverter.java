package de.mbussmann.solarlog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Inverter {
    @Id
    private Long id;

    @ManyToOne
    private System system;

    private String typ;
    private String name;
    private Long strings;
    private Long peak;
    private String orientation;

    public Inverter() {

    }

    public Inverter(Long id, System system, String typ, String name, Long strings, Long peak, String orientation) {
        this.id = id;
        this.system = system;
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
