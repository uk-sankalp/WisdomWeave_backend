CREATE TABLE comments (
                          id BIGSERIAL PRIMARY KEY,
                          comment TEXT NOT NULL,
                          post_id BIGINT REFERENCES post(id) ON DELETE CASCADE,
                          user_id BIGINT REFERENCES users(id),
                          created_at TIMESTAMP
);

CREATE TABLE likes (
                       id BIGSERIAL PRIMARY KEY,
                       post_id BIGINT REFERENCES post(id) ON DELETE CASCADE,
                       user_id BIGINT REFERENCES users(id),
                       UNIQUE(post_id, user_id)
);
