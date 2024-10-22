-- auto-generated definition
CREATE TABLE player
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE party
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE game
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    party_id INT,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (party_id) REFERENCES party (id)
);

CREATE TABLE party_players
(
    party_id INT NOT NULL REFERENCES party (id),
    player_id INT NOT NULL REFERENCES player (id),
    PRIMARY KEY (player_id, party_id)
)



