CREATE TABLE inverter (
    id SERIAL NOT NULL,
    system_id SERIAL NOT NULL,
    serial text NOT NULL UNIQUE,
    typ text NOT NULL,
    name text NOT NULL,
    strings BIGINT NOT NULL,
    peak BIGINT NOT NULL,
    orientation text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_system_id
    FOREIGN KEY(system_id)
    REFERENCES system(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

