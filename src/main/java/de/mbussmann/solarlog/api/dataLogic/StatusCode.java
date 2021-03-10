package de.mbussmann.solarlog.api.dl;

public class StatusCode {
    private Long wr;
    private Long index;
    private String text;

    public StatusCode() {

    }

    public StatusCode(Long wr, Long index, String text) {
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
