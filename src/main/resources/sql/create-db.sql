DROP TABLE IF EXISTS DOG;
CREATE TABLE DOG(
    id INT NOT NULL auto_increment,
    name VARCHAR(50),
    birth_date DATE,
    height INT,
    weight INT,
    PRIMARY KEY (id)
);