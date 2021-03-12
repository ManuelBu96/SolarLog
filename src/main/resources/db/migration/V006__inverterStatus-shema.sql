CREATE TABLE inverterStatus (
    id SERIAL NOT NULL,
    inverter_id SERIAL NOT NULL,
    timestamp timestamp NOT NULL,
    status_code BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_inverter_id
    FOREIGN KEY(inverter_id)
    REFERENCES inverter(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE statusCode (
    id SERIAL NOT NULL,
    inverter_typ text NOT NULL,
    status_code BIGINT NOT NULL,
    text text NOT NULL,
    PRIMARY KEY (id)
);