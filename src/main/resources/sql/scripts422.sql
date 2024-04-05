CREATE TABLE Author(
    id INTEGER PRIMARY KEY;
    name TEXT;
    birth_date DATE;
)

CREATE TABLE MANGA(
    id INTEGER PRIMARY KEY;
    name TEXT;
    published_year DATE;
    rating NUMERIC(10,2);
)

CREATE TABLE Manga_by_Authors(
    author_id INTEGER,
    manga_id INTEGER,
    FOREIGN KEY (author_id) REFERENCES Author(id),
    FOREIGN KEY (manga_id) REFERENCES Manga(id)
)