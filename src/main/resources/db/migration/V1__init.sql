CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20),
                       bio TEXT,
                       avatar_url TEXT,
                       created_at TIMESTAMP
);

CREATE TABLE post (
                      id BIGSERIAL PRIMARY KEY,
                      title VARCHAR(255),
                      content TEXT,
                      published BOOLEAN,
                      author_id BIGINT REFERENCES users(id),
                      created_at TIMESTAMP,
                      updated_at TIMESTAMP
);
