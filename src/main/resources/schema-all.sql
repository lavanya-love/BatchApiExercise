DROP TABLE employee IF EXISTS;

CREATE TABLE employee  (
    coffee_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    email VARCHAR(30),
    birth_date DATE
);