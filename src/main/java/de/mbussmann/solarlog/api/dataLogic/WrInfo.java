package de.mbussmann.solarlog.api.dataLogic;

public class WrInfo {
    private String typ;
    private String rs485Endadresse;
    private Long modulLeistung;
    private String bezeichnung;
    private Long strings;
    private String string1Bezeichnung;
    private String string2Bezeichnung;
    private Long nennleistung;
    private String string3Bezeichnung;
    private Long herstellerProtkoll;
    private Long funktion;
    private Long wrInnentemp;
    private Long korrekturfaktor;
    private Long s0ZaehlerOption;

    public WrInfo() {

    }

    public WrInfo(String typ, String rs485Endadresse, Long modulLeistung, String bezeichnung, Long strings, String string1Bezeichnung, String string2Bezeichnung, Long nennleistung, String string3Bezeichnung, Long herstellerProtkoll, Long funktion, Long wrInnentemp, Long korrekturfaktor, Long s0ZaehlerOption) {
        this.typ = typ;
        this.rs485Endadresse = rs485Endadresse;
        this.modulLeistung = modulLeistung;
        this.bezeichnung = bezeichnung;
        this.strings = strings;
        this.string1Bezeichnung = string1Bezeichnung;
        this.string2Bezeichnung = string2Bezeichnung;
        this.nennleistung = nennleistung;
        this.string3Bezeichnung = string3Bezeichnung;
        this.herstellerProtkoll = herstellerProtkoll;
        this.funktion = funktion;
        this.wrInnentemp = wrInnentemp;
        this.korrekturfaktor = korrekturfaktor;
        this.s0ZaehlerOption = s0ZaehlerOption;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getRs485Endadresse() {
        return rs485Endadresse;
    }

    public void setRs485Endadresse(String rs485Endadresse) {
        this.rs485Endadresse = rs485Endadresse;
    }

    public Long getModulLeistung() {
        return modulLeistung;
    }

    public void setModulLeistung(Long modulLeistung) {
        this.modulLeistung = modulLeistung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Long getStrings() {
        return strings;
    }

    public void setStrings(Long strings) {
        this.strings = strings;
    }

    public String getString1Bezeichnung() {
        return string1Bezeichnung;
    }

    public void setString1Bezeichnung(String string1Bezeichnung) {
        this.string1Bezeichnung = string1Bezeichnung;
    }

    public String getString2Bezeichnung() {
        return string2Bezeichnung;
    }

    public void setString2Bezeichnung(String string2Bezeichnung) {
        this.string2Bezeichnung = string2Bezeichnung;
    }

    public Long getNennleistung() {
        return nennleistung;
    }

    public void setNennleistung(Long nennleistung) {
        this.nennleistung = nennleistung;
    }

    public String getString3Bezeichnung() {
        return string3Bezeichnung;
    }

    public void setString3Bezeichnung(String string3Bezeichnung) {
        this.string3Bezeichnung = string3Bezeichnung;
    }

    public Long getHerstellerProtkoll() {
        return herstellerProtkoll;
    }

    public void setHerstellerProtkoll(Long herstellerProtkoll) {
        this.herstellerProtkoll = herstellerProtkoll;
    }

    public Long getFunktion() {
        return funktion;
    }

    public void setFunktion(Long funktion) {
        this.funktion = funktion;
    }

    public Long getWrInnentemp() {
        return wrInnentemp;
    }

    public void setWrInnentemp(Long wrInnentemp) {
        this.wrInnentemp = wrInnentemp;
    }

    public Long getKorrekturfaktor() {
        return korrekturfaktor;
    }

    public void setKorrekturfaktor(Long korrekturfaktor) {
        this.korrekturfaktor = korrekturfaktor;
    }

    public Long getS0ZaehlerOption() {
        return s0ZaehlerOption;
    }

    public void setS0ZaehlerOption(Long s0ZaehlerOption) {
        this.s0ZaehlerOption = s0ZaehlerOption;
    }
}
