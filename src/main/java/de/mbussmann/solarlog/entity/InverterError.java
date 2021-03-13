package de.mbussmann.solarlog.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "inverterStatus", schema = "public")
public class InverterError {
    @Id
    private Long id;
    @ManyToOne
    private Inverter inverter;
    private Timestamp timestamp;
    @Column(name = "error_code")
    private Long errorCode;

    public InverterError() {

    }

    public InverterError(Inverter inverter, Timestamp timestamp, Long stautsCode) {
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.errorCode = errorCode;
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

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }
}
