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
    partyId INT,
    createdAt TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (partyId) REFERENCES party (id)
);

CREATE TABLE party_players
(

)



