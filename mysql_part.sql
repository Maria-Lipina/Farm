/*Создать СУБД, в которой будет храниться информация по друзьям человека*/

DROP DATABASE IF EXISTS farm;
CREATE DATABASE farm;
USE farm;


/*Создать таблицы с иерархией животных*/

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(15)
);

DROP TABLE IF EXISTS species;
CREATE TABLE species (
id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(15)
);

DROP TABLE IF EXISTS animals;
CREATE TABLE animals (
category TINYINT UNSIGNED NOT NULL,
species TINYINT UNSIGNED NOT NULL,
name VARCHAR(15) NOT NULL UNIQUE,
birthdate DATE NOT NULL,
commands VARCHAR(100),
PRIMARY KEY (category, species, name),
FOREIGN KEY (category) REFERENCES categories(id),
FOREIGN KEY (species) REFERENCES species(id)
);


INSERT INTO categories VALUES
(1, 'pet'),
(2, 'pack animal');

INSERT INTO species VALUES
(1, 'Dog'),
(2, 'Cat'),
(3, 'Hamster'),
(4, 'Camel'),
(5, 'Horse'),
(6, 'Donkey');



/*Заполнить низкоуровневые таблицы именами(животных), командами, которые они выполняют и датами рождения*/

INSERT INTO animals VALUES
(1, 1, 'шарик', '2022-12-1', 'апорт, рядом, сидеть'),
(1, 2, 'муся', '2019-01-2', ''),
(1, 3, 'хома', '2023-02-28', 'на плечо'),
(1, 1, 'бобик', '2018-04-12', 'ко мне'),
(1, 2, 'барсик', '2021-12-5', 'играть, кис-кис'),
(2, 4, 'луи', '2020-07-07', 'пошел, стоять'),
(2, 5, 'веня', '2021-09-07', 'но, тпру, стоять'),
(2, 6, 'иа', '2023-01-08', ''),
(2, 5, 'плотва', '2017-05-03', 'но, тпру, стоять'),
(2, 6, 'вис', '2020-12-10', 'пошел, стоять');


/*Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу*/
-- Таблицы объединены фактически в задании выше

DELETE FROM animals
WHERE species = (
SELECT id FROM species WHERE name='Camel');



/* Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице*/

DROP TABLE IF EXISTS young_animals;
CREATE TABLE young_animals
SELECT name, birthdate, 
PERIOD_DIFF(EXTRACT(YEAR_MONTH FROM NOW()), EXTRACT(YEAR_MONTH FROM animals.birthdate)) AS age FROM animals
WHERE birthdate > DATE_SUB(NOW(), INTERVAL 3 YEAR) AND birthdate < DATE_SUB(NOW(), INTERVAL 1 YEAR);



/*Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.*/

SELECT categories.name AS category, species.name AS species, animals.name, animals.birthdate, young_animals.age, animals.commands FROM animals
JOIN categories ON animals.category=categories.id
JOIN species ON animals.species=species.id
JOIN young_animals ON young_animals.name = animals.name;

