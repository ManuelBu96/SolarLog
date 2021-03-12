package de.mbussmann.solarlog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "inverterData", schema = "public")
public class InverterData {
    @Id
    private Long id;

    @ManyToOne
    private Inverter inverter;

    private Timestamp timestamp;
    private Long pac_Watt;
    private Long pdc_Watt_1;
    private Long pdc_Watt_2;
    private Long pdc_Watt_3;

    public InverterData() {

    }

    public InverterData(Long id, Inverter inverter, Timestamp timestamp, Long pac_Watt, Long pdc_Watt_1) {
        this.id = id;
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.pac_Watt = pac_Watt;
        this.pdc_Watt_1 = pdc_Watt_1;
    }

    public InverterData(Long id, Inverter inverter, Timestamp timestamp, Long pac_Watt, Long pdc_Watt_1, Long pdc_Watt_2) {
        this.id = id;
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.pac_Watt = pac_Watt;
        this.pdc_Watt_1 = pdc_Watt_1;
        this.pdc_Watt_2 = pdc_Watt_2;
    }

    public InverterData(Long id, Inverter inverter, Timestamp timestamp, Long pac_Watt, Long pdc_Watt_1, Long pdc_Watt_2, Long pdc_Watt_3) {
        this.id = id;
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.pac_Watt = pac_Watt;
        this.pdc_Watt_1 = pdc_Watt_1;
        this.pdc_Watt_2 = pdc_Watt_2;
        this.pdc_Watt_3 = pdc_Watt_3;
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

    public Long getPac_Watt() {
        return pac_Watt;
    }

    public void setPac_Watt(Long pac_Watt) {
        this.pac_Watt = pac_Watt;
    }

    public Long getPdc_Watt_1() {
        return pdc_Watt_1;
    }

    public void setPdc_Watt_1(Long pdc_Watt_1) {
        this.pdc_Watt_1 = pdc_Watt_1;
    }

    public Long getPdc_Watt_2() {
        return pdc_Watt_2;
    }

    public void setPdc_Watt_2(Long pdc_Watt_2) {
        this.pdc_Watt_2 = pdc_Watt_2;
    }

    public Long getPdc_Watt_3() {
        return pdc_Watt_3;
    }

    public void setPdc_Watt_3(Long pdc_Watt_3) {
        this.pdc_Watt_3 = pdc_Watt_3;
    }
}
