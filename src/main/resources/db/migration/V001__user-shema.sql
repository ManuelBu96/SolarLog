CREATE TABLE users (
    id SERIAL NOT NULL,
    email text NOT NULL UNIQUE,
    firstname text NOT NULL,
    lastname text NOT NULL,
    password text NOT NULL,
    role text default 'USER',
    PRIMARY KEY (id)
);

