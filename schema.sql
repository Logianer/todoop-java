CREATE TABLE todo_list (
    list_id INTEGER PRIMARY KEY,
    title TEXT,
    list_type INT NOT NULL,
);

CREATE TABLE todo_note (
    note_id INTEGER PRIMARY KEY,
    content TEXT,
    list_id INT UNIQUE,
    FOREIGN KEY (list_id)
        REFERENCES todo_list (list_id)
            ON UPDATE RESTRICT
            ON DELETE CASCADE
);

CREATE TABLE todo_item (
    item_id INTEGER PRIMARY KEY,
    checked INT NOT NULL,
    content TEXT,
    list_id INT UNIQUE,
    FOREIGN KEY (list_id)
        REFERENCES todo_list (list_id)
            ON UPDATE RESTRICT
            ON DELETE CASCADE
)

