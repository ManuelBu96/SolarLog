package de.mbussmann.solarlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "errorCode", schema = "public")
public class ErrorCode {
    @Id
    private Long id;
    @Column(name = "inverter_typ")
    private String inverterTyp;
    @Column(name = "error_code")
    private Long errorCode;
    private String text;

    public ErrorCode() {

    }

    public ErrorCode(Long id, String inverterTyp, Long errorCode, String text) {
        this.id = id;
        this.inverterTyp = inverterTyp;
        this.errorCode = errorCode;
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

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
