INSERT INTO player (name)
VALUES ('Cora'),
       ('Roscoe'),
       ('Dell'),
       ('Atos');

INSERT INTO party (name)
VALUES ('D&D'),
       ('Kompanjoni'),
       ('Skvadrica');

INSERT INTO party_players (party_id, player_id)
VALUES (1, 1),
       (1, 3),
       (2, 3),
       (2, 4),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4);

INSERT INTO campaign (title, party_id)
VALUES ('Phandelver and Below', 3),
       ('Vecna', 1),
       ('Curse of Strahd', 1);

INSERT INTO session (campaign_id)
VALUES (1),
       (1),
       (2),
       (1),
       (3),
       (3);

INSERT INTO attends (session_id, player_id, attend)
VALUES (1, 1, true),
       (1, 2, true),
       (1, 3, false),
       (1, 4, false),
       (2, 1, true),
       (2, 2, true),
       (2, 3, false),
       (2, 4, true),
       (3, 1, true),
       (3, 2, true),
       (4, 1, false),
       (4, 2, true),
       (4, 3, true),
       (4, 4, true),
       (5, 1, true),
       (5, 2, true),
       (6, 1, true),
       (6, 2, true);

INSERT INTO rolls (player_id, session_id, roll_option)
VALUES (1, 1, 'ONE'),
       (2, 1, 'TWENTY'),
       (3, 1, 'TWENTY'),
       (1, 2, 'ONE'),
       (3, 2, 'TWENTY'),
       (1, 3, 'TWENTY'),
       (2, 4, 'ONE'),
       (3, 4, 'ONE'),
       (4, 4, 'TWENTY'),
       (3, 5, 'TWENTY'),
       (1, 3, 'TWENTY'),
       (3, 6, 'ONE');




