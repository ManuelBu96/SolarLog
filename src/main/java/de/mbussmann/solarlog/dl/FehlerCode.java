package de.mbussmann.solarlog.dl;

public class FehlerCode {
    private Long wr;
    private Long index;
    private String text;

    public FehlerCode() {

    }

    public FehlerCode(Long wr, Long index, String text) {
        this.wr = wr;
        this.index = index;
        this.text = text;
    }

    public Long getWr() {
        return wr;
    }

    public void setWr(Long wr) {
        this.wr = wr;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
