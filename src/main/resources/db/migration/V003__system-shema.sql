CREATE TABLE system (
    id SERIAL NOT NULL,
    name text NOT NULL UNIQUE,
    folder text NOT NULL,
    started_at timestamp, --make sense
    serial BIGINT, --make sense
    wr_anzahl BIGINT default 0,
    verguetung BIGINT default 0,
    PRIMARY KEY (id)
);

