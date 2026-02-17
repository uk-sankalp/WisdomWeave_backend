CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tags (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE post_categories (
                                 post_id BIGINT REFERENCES post(id) ON DELETE CASCADE,
                                 category_id BIGINT REFERENCES categories(id),
                                 PRIMARY KEY(post_id, category_id)
);

CREATE TABLE post_tags (
                           post_id BIGINT REFERENCES post(id) ON DELETE CASCADE,
                           tag_id BIGINT REFERENCES tags(id),
                           PRIMARY KEY(post_id, tag_id)
);
