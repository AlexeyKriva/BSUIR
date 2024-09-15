CREATE TABLE documents(
    id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    content VARCHAR NOT NULL,
    published_at DATE NOT NULL
)