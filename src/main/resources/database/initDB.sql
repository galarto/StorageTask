CREATE TABLE IF NOT EXISTS products
(
    id SERIAL PRIMARY KEY ,
    title VARCHAR(255)  ,
    description VARCHAR(4096)  ,
    price BIGINT ,
    is_available boolean
);