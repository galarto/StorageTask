CREATE TABLE IF NOT EXISTS products
(
    id SERIAL primary key,
    title VARCHAR(255) not null,
    description VARCHAR(4096),
    price NUMERIC not null default 0 check (price >= 0),
    is_available boolean not null default false
);