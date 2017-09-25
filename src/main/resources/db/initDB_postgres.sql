DROP TABLE IF EXISTS meals_with_weight;
DROP TABLE IF EXISTS days;
DROP TABLE IF EXISTS journey;
DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE meals (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  calories    INT NOT NULL
);

CREATE TABLE journey
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL
);

CREATE TABLE days
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  journey_id  INTEGER NOT NULL,
  FOREIGN KEY (journey_id) REFERENCES journey (id) ON DELETE CASCADE
);

CREATE TABLE meals_with_weight (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  id_table    INT NOT NULL,
  weight      INT NOT NULL,
  day_id      INTEGER NOT NULL,
  meal_id     INTEGER NOT NULL,
  FOREIGN KEY (day_id) REFERENCES days (id) ON DELETE CASCADE,
  FOREIGN KEY (meal_id) REFERENCES meals (id) ON DELETE CASCADE
);






