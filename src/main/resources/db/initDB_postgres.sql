DROP TABLE IF EXISTS meals_with_weight;
DROP TABLE IF EXISTS days;
DROP TABLE IF EXISTS journey;
DROP TABLE IF EXISTS user_meals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE meals (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  calories    INT NOT NULL
);

CREATE TABLE users
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  login       VARCHAR NOT NULL,
  password    VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (login);

CREATE TABLE user_meals (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  calories    INT NOT NULL,
  user_id     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE journey
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  user_id     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE days
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  journey_id  INTEGER NOT NULL,
  FOREIGN KEY (journey_id) REFERENCES journey (id) ON DELETE CASCADE
);

CREATE TABLE meals_with_weight (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  weight      INT NOT NULL,
  day_id      INTEGER NOT NULL,
  menu_name   VARCHAR NOT NULL,
  meal_id     INTEGER NOT NULL,
  FOREIGN KEY (day_id) REFERENCES days (id) ON DELETE CASCADE,
  FOREIGN KEY (meal_id) REFERENCES user_meals (id) ON DELETE CASCADE
);



