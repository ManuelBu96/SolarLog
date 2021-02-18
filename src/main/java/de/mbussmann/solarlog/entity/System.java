package de.mbussmann.solarlog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class System {
    @Id
    private Long id;

    private String name;
    private Long wr_anzahl;
    private Long verguetung;

    public System() {

    }

    public System(Long id, String name, Long wr_anzahl, Long verguetung) {
        this.id = id;
        this.name = name;
        this.wr_anzahl = wr_anzahl;
        this.verguetung = verguetung;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWr_anzahl() {
        return wr_anzahl;
    }

    public void setWr_anzahl(Long wr_anzahl) {
        this.wr_anzahl = wr_anzahl;
    }

    public Long getVerguetung() {
        return verguetung;
    }

    public void setVerguetung(Long verguetung) {
        this.verguetung = verguetung;
    }
}
