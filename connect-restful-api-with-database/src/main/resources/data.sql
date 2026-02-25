INSERT INTO user_details(id, birth_date, name)
VALUES(10001, current_date(), 'Daddy');

INSERT INTO user_details(id, birth_date, name)
VALUES(10002, current_date(), 'Mommy');

INSERT INTO user_details(id, birth_date, name)
VALUES(10003, current_date(), 'Sissy');

INSERT INTO user_details(id, birth_date, name)
VALUES(10004, current_date(), 'Cat');

INSERT INTO user_details(id, birth_date, name)
VALUES(10005, current_date(), 'Katze');

INSERT INTO user_details(id, birth_date, name)
VALUES(10006, current_date(), 'Gato');

INSERT INTO post(id, description, user_id)
VALUES(20001, 'I want to learn AWS', 10005);

INSERT INTO post(id, description, user_id)
VALUES(20002, 'I want to learn Sonic', 10005);

INSERT INTO post(id, description, user_id)
VALUES(20003, 'I want to learn Poopy', 10001);

INSERT INTO post(id, description, user_id)
VALUES(20004, 'I want to learn 123', 10006);

INSERT INTO post(id, description, user_id)
VALUES(20005, 'I want to learn Yoga', 10003);

INSERT INTO post(id, description, user_id)
VALUES(20006, 'I want to learn Trash', 10003);