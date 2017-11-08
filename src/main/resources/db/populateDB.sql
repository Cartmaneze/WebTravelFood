DELETE FROM meals;
DELETE FROM meals_with_weight;
DELETE FROM days;
DELETE FROM journey;
DELETE FROM users;
DELETE FROM user_meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, password) VALUES
  ('cartmaneze', 'slipknot'),
  ('user', 'password');

INSERT INTO meals (name, calories) VALUES
  ('гречка', 330),
  ('кукурузная крупа', 337),
  ('рис', 344),
  ('овсянка', 342),
  ('макароны', 350),
  ('хлебцы', 320),
  ('колбаса', 450),
  ('сушеное мясо', 500),
  ('конфеты', 360),
  ('сникерс', 507),
  ('козинаки', 576),
  ('сухие сливки', 580),
  ('сахар', 398),
  ('приправа', 150),
  ('соль', 0),
  ('кофе', 0),
  ('чай', 0);

INSERT INTO journey (name, user_id) VALUES
  ('journey1', 100000),
  ('journey2', 100000),
  ('journey3', 100000);

INSERT INTO user_meals (name, calories, user_id) VALUES
  ('барбарис', 100500, 100000);

INSERT INTO days (name, journey_id) VALUES
  ('1', 100019);

INSERT INTO meals_with_weight (weight, menu_name, meal_id, day_id) VALUES
  (100, 'завтрак', 100022, 100023);



