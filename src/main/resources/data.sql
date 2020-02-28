DROP TABLE IF EXISTS examples;

CREATE TABLE examples (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
);

INSERT INTO examples (title, description) VALUES
  ('Cthulhu', 'Master of Rlyeh'),
  ('Hastur', 'The King in Yellow'),
  ('Ammutseba', 'Devourer of Stars'),
  ('Eihort', 'The Pale Beast'),
  ('Kassogtha', 'Bride of Cthulhu');