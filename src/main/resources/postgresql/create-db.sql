DROP TABLE IF EXISTS DOG;
CREATE TABLE DOG(
    id SERIAL NOT NULL,
    name VARCHAR(50),
    birth_date DATE,
    height INT,
    weight INT,
    PRIMARY KEY (id)
);