package de.mbussmann.solarlog.api.dl;

public class BaseVars {
    private Long anlagenKWP;
    private Long anzahlWR;
    private WrInfo[] wrInfo;
    private String titel;
    private String betreiber;
    private String mail;
    private String standort;
    private String modul;
    private String wr;
    private String leistung;
    private String inbetrieb;
    private String ausrichtung;
    private String banner1;
    private String banner2;
    private String banner3;
    private String bannerLink;
    private StatusCode[] statusCodes;
    private FehlerCode[] fehlerCodes;
    private Long verguetung;
    private String serial;
    private String firmware;
    private String firmwareDate;
    private String wrTyp;
    private String slDatum;
    private String slUhrzeit;
    private boolean isTemp;
    private boolean isOnline;

    public BaseVars() {

    }

    public BaseVars(Long anlagenKWP, Long anzahlWR, WrInfo[] wrInfo, String titel, String betreiber, String mail, String standort, String modul, String wr, String leistung, String inbetrieb, String ausrichtung, String banner1, String banner2, String banner3, String bannerLink, StatusCode[] statusCodes, FehlerCode[] fehlerCodes, Long verguetung, String serial, String firmware, String firmwareDate, String wrTyp, String slDatum, String slUhrzeit, boolean isTemp, boolean isOnline) {
        this.anlagenKWP = anlagenKWP;
        this.anzahlWR = anzahlWR;
        this.wrInfo = wrInfo;
        this.titel = titel;
        this.betreiber = betreiber;
        this.mail = mail;
        this.standort = standort;
        this.modul = modul;
        this.wr = wr;
        this.leistung = leistung;
        this.inbetrieb = inbetrieb;
        this.ausrichtung = ausrichtung;
        this.banner1 = banner1;
        this.banner2 = banner2;
        this.banner3 = banner3;
        this.bannerLink = bannerLink;
        this.statusCodes = statusCodes;
        this.fehlerCodes = fehlerCodes;
        this.verguetung = verguetung;
        this.serial = serial;
        this.firmware = firmware;
        this.firmwareDate = firmwareDate;
        this.wrTyp = wrTyp;
        this.slDatum = slDatum;
        this.slUhrzeit = slUhrzeit;
        this.isTemp = isTemp;
        this.isOnline = isOnline;
    }

    public Long getAnlagenKWP() {
        return anlagenKWP;
    }

    public void setAnlagenKWP(Long anlagenKWP) {
        this.anlagenKWP = anlagenKWP;
    }

    public Long getAnzahlWR() {
        return anzahlWR;
    }

    public void setAnzahlWR(Long anzahlWR) {
        this.anzahlWR = anzahlWR;
    }

    public WrInfo[] getWrInfo() {
        return wrInfo;
    }

    public void setWrInfo(WrInfo[] wrInfo) {
        this.wrInfo = wrInfo;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBetreiber() {
        return betreiber;
    }

    public void setBetreiber(String betreiber) {
        this.betreiber = betreiber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getWr() {
        return wr;
    }

    public void setWr(String wr) {
        this.wr = wr;
    }

    public String getLeistung() {
        return leistung;
    }

    public void setLeistung(String leistung) {
        this.leistung = leistung;
    }

    public String getInbetrieb() {
        return inbetrieb;
    }

    public void setInbetrieb(String inbetrieb) {
        this.inbetrieb = inbetrieb;
    }

    public String getAusrichtung() {
        return ausrichtung;
    }

    public void setAusrichtung(String ausrichtung) {
        this.ausrichtung = ausrichtung;
    }

    public String getBanner1() {
        return banner1;
    }

    public void setBanner1(String banner1) {
        this.banner1 = banner1;
    }

    public String getBanner2() {
        return banner2;
    }

    public void setBanner2(String banner2) {
        this.banner2 = banner2;
    }

    public String getBanner3() {
        return banner3;
    }

    public void setBanner3(String banner3) {
        this.banner3 = banner3;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }

    public StatusCode[] getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(StatusCode[] statusCodes) {
        this.statusCodes = statusCodes;
    }

    public FehlerCode[] getFehlerCodes() {
        return fehlerCodes;
    }

    public void setFehlerCodes(FehlerCode[] fehlerCodes) {
        this.fehlerCodes = fehlerCodes;
    }

    public Long getVerguetung() {
        return verguetung;
    }

    public void setVerguetung(Long verguetung) {
        this.verguetung = verguetung;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getFirmwareDate() {
        return firmwareDate;
    }

    public void setFirmwareDate(String firmwareDate) {
        this.firmwareDate = firmwareDate;
    }

    public String getWrTyp() {
        return wrTyp;
    }

    public void setWrTyp(String wrTyp) {
        this.wrTyp = wrTyp;
    }

    public String getSlDatum() {
        return slDatum;
    }

    public void setSlDatum(String slDatum) {
        this.slDatum = slDatum;
    }

    public String getSlUhrzeit() {
        return slUhrzeit;
    }

    public void setSlUhrzeit(String slUhrzeit) {
        this.slUhrzeit = slUhrzeit;
    }

    public boolean isTemp() {
        return isTemp;
    }

    public void setTemp(boolean temp) {
        isTemp = temp;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
