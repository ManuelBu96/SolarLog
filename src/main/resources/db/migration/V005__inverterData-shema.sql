CREATE TABLE inverterData (
    id SERIAL NOT NULL,
    inverter_id SERIAL NOT NULL,
    timestamp timestamp NOT NULL,
    pac_Watt BIGINT NOT NULL,
    pdc_Watt_1 BIGINT NOT NULL,
    pdc_Watt_2 BIGINT,
    pdc_Watt_3 BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_inverter_id
    FOREIGN KEY(inverter_id)
    REFERENCES inverter(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

