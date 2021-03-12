package de.mbussmann.solarlog.boundary.dto;

/**
 * @author Manuel Bußmann
 */
public class ErrorCodeDto {
    private String inverterTyp;
    private Long errorCode;
    private String text;

    /**
     * Default Constructor
     */
    public ErrorCodeDto() {

    }

    /**
     * Constructor ErrorCodeDto
     * @param inverterTyp String of the Inverter-Type
     * @param errorCode number to identify
     * @param text ErrorCode Text
     */
    public ErrorCodeDto(String inverterTyp, Long errorCode, String text) {
        this.inverterTyp = inverterTyp;
        this.errorCode = errorCode;
        this.text = text;
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
