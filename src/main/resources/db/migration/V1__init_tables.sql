CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(70) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    registration VARCHAR(11) NOT NULL,
    apartment INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS schedules (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users (id),
    date_time TIMESTAMP NOT NULL,
    status VARCHAR(11) NOT NULL
);