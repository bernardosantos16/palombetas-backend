CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    rating DOUBLE PRECISION,
    times_champion INTEGER,
    times_mvp INTEGER,
    team_id BIGINT,
    is_active BOOLEAN,
    CONSTRAINT fk_players_team
        FOREIGN KEY (team_id)
        REFERENCES teams(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
