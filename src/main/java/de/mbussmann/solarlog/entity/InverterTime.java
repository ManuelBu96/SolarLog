package de.mbussmann.solarlog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class InverterTime {
    @Id
    private Long id;

    @ManyToOne
    private Inverter inverter;

    private Timestamp timestamp;
    private Long pac_Watt;
    private Long pdc_Watt;

    public InverterTime() {

    }

    public InverterTime(Long id, Inverter inverter, Timestamp timestamp, Long pac_Watt, Long pdc_Watt) {
        this.id = id;
        this.inverter = inverter;
        this.timestamp = timestamp;
        this.pac_Watt = pac_Watt;
        this.pdc_Watt = pdc_Watt;
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

    public Long getPdc_Watt() {
        return pdc_Watt;
    }

    public void setPdc_Watt(Long pdc_Watt) {
        this.pdc_Watt = pdc_Watt;
    }
}
