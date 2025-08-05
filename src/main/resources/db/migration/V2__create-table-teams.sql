CREATE TABLE teams (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    rating DOUBLE PRECISION,
    match_id BIGINT NOT NULL,
    CONSTRAINT fk_teams_match
        FOREIGN KEY (match_id)
        REFERENCES matches(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
