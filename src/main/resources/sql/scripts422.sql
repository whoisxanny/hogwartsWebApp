CREATE TABLE Human(
    id INTEGER PRIMARY KEY,
    name TEXT,
    age INTEGER,
    lisence BOOLEAN;
)

CREATE TABLE CAR(
    id INTEGER PRIMARY KEY,
    name TEXT,
    model TEXT,
    brand TEXT,
    price NUMERIC(5,2);
)

CREATE TABLE Manga_by_Authors(
    human_id INTEGER,
    car_id INTEGER,
    PRIMARY KET (human_id, car_id),
    FOREIGN KEY (human_id) REFERENCES Human(id),
    FOREIGN KEY (car_id) REFERENCES Car(id);
)