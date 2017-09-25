DELETE FROM meals;
DELETE FROM meals_with_weight;
DELETE FROM days;
DELETE FROM journey;
ALTER SEQUENCE global_seq RESTART WITH 100000;

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

INSERT INTO journey (name) VALUES
  ('journey1'),
  ('journey2'),
  ('journey3'),
  ('journey4'),
  ('journey5'),
  ('journey6'),
  ('journey7'),
  ('journey8'),
  ('journey9'),
  ('journey10');



