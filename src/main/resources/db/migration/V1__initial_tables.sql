-- auto-generated definition
CREATE TABLE player
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE party
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE game
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    party_id INT,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    FOREIGN KEY (party_id) REFERENCES party (id)
);

CREATE TABLE party_players
(
    party_id INT REFERENCES party (id),
    player_id INT REFERENCES player (id),
    PRIMARY KEY (player_id, party_id)
);

CREATE TYPE roll_option AS ENUM ('ONE', 'TWENTY');

CREATE TABLE rolls
(
    id          SERIAL PRIMARY KEY,
    player_id   int NOT NULL,
    game_id     int NOT NULL,
    roll_option roll_option NOT NULL ,
    FOREIGN KEY (player_id) REFERENCES player (id),
    FOREIGN KEY (game_id) REFERENCES game (id)
);


