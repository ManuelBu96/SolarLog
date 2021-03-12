package de.mbussmann.solarlog.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "statusCode", schema = "public")
public class StatusCode {
    @Id
    private Long id;
    @Column(name = "inverter_typ")
    private String inverterTyp;
    @Column(name = "status_code")
    private Long stautsCode;
    private String text;

    public StatusCode() {

    }

    public StatusCode(Long id, String inverterTyp, Long stautsCode, String text) {
        this.id = id;
        this.inverterTyp = inverterTyp;
        this.stautsCode = stautsCode;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInverterTyp() {
        return inverterTyp;
    }

    public void setInverterTyp(String inverterTyp) {
        this.inverterTyp = inverterTyp;
    }

    public Long getStautsCode() {
        return stautsCode;
    }

    public void setStautsCode(Long stautsCode) {
        this.stautsCode = stautsCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}