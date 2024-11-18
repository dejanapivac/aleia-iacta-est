-- auto-generated definition
CREATE TABLE player
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE party
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE campaign
(
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(255)                NOT NULL,
    party_id   INT REFERENCES party (id),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE party_players
(
    party_id  INT REFERENCES party (id) ON DELETE CASCADE,
    player_id INT REFERENCES player (id) ON DELETE CASCADE,
    PRIMARY KEY (player_id, party_id)
);

CREATE TABLE session
(
    id          SERIAL PRIMARY KEY,
    campaign_id INT REFERENCES campaign (id) ON DELETE CASCADE,
    played_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE roll_option AS ENUM ('ONE', 'TWENTY');

CREATE TABLE rolls
(
    id          SERIAL PRIMARY KEY,
    player_id   int         NOT NULL REFERENCES player (id) ON DELETE CASCADE,
    session_id  int         NOT NULL REFERENCES session (id) ON DELETE CASCADE,
    roll_option roll_option NOT NULL
);

CREATE TABLE attends
(
    session_id INT REFERENCES session (id) ON DELETE CASCADE,
    player_id  INT REFERENCES player (id) ON DELETE CASCADE,
    attend     BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (session_id, player_id)
);


