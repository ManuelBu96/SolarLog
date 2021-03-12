package de.mbussmann.solarlog.boundary.dto;

/**
 * @author Manuel Bußmann
 */
public class StatusCodeDto {
    private String inverterTyp;
    private Long stautsCode;
    private String text;

    /**
     * Default Constructor
     */
    public StatusCodeDto() {

    }

    /**
     * Constructor StatusCodeDto
     * @param inverterTyp String of the Inverter-Type
     * @param stautsCode number to identify
     * @param text StautsCode Text
     */
    public StatusCodeDto(String inverterTyp, Long stautsCode, String text) {
        this.inverterTyp = inverterTyp;
        this.stautsCode = stautsCode;
        this.text = text;
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
