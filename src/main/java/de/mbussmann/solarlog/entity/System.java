package de.mbussmann.solarlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "system", schema = "public")
public class System {
    @Id
    private Long id;

    private String name;
    private String folder;
    @Column(name = "started_at")
    private Timestamp startedAt;
    private Long serial;
    private Long wr_anzahl;
    private Long verguetung;

    public System() {

    }

    public System(Long id, String name, String folder, Long wr_anzahl, Long verguetung) {
        this.id = id;
        this.name = name;
        this.folder = folder;
        this.wr_anzahl = wr_anzahl;
        this.verguetung = verguetung;
    }

    public System(Long id, String name, String folder, Timestamp startedAt, Long serial, Long wr_anzahl, Long verguetung) {
        this.id = id;
        this.name = name;
        this.folder = folder;
        this.startedAt = startedAt;
        this.serial = serial;
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
