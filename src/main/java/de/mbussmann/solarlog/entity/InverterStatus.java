package de.mbussmann.solarlog.entity;

import javax.persistence.*;
import java.sql.Timestamp;

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

    public InverterStatus(Long id, Inverter inverter, Timestamp timestamp, Long stautsCode) {
        this.id = id;
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
