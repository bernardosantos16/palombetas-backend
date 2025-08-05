ALTER TABLE matches
ADD COLUMN player_mvp_id BIGINT;

ALTER TABLE matches
ADD CONSTRAINT fk_matches_player_mvp
    FOREIGN KEY (player_mvp_id)
    REFERENCES players(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE;