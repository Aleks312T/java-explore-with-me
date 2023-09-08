CREATE TABLE  IF NOT EXISTS endpoint_hit (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app VARCHAR(32),
    uri VARCHAR(128) NOT NULL,
    ip VARCHAR(16) NOT NULL,
    timestamp timestamp WITHOUT TIME ZONE
);