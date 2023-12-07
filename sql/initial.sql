CREATE DATABASE hd_library;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id           BIGSERIAL PRIMARY KEY,
    email             VARCHAR(320) UNIQUE NOT NULL,
    user_role         CHAR(1)             NOT NULL DEFAULT 'S',
    first_name        VARCHAR(50)         NOT NULL,
    family_name       VARCHAR(50)         NOT NULL,
    address           VARCHAR(255)        NOT NULL,
    user_status       CHAR(1)             NOT NULL DEFAULT 'A',
    password_hash     VARCHAR(64)         NOT NULL,
    registration_date TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
    update_time       TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN users.user_role IS 'S - Student, F - Faculty, L - Librarian';
COMMENT ON COLUMN users.user_status IS 'A - Active, O - Overdue, F - Frozen, I - Inactive';

ALTER SEQUENCE public.users_user_id_seq RESTART WITH 1000;

-- Test users, password: 123456
INSERT INTO users(email, user_role, first_name, family_name, address, user_status, password_hash)
VALUES ('mike.muller@stud.hochschule-heidelberg.de', 'S', 'a', 'b', 'Germany', 'A',
        '$2a$10$EZAvbMiNBodibBxH3i2BRuHcehAngMJ6pbLhP6b5SFAEpIdU/qIZS');


drop table if exists borrows;

CREATE TABLE borrows (
     borrow_id BIGSERIAL PRIMARY KEY,
     user_id BIGINT not null,
     book_id BIGINT not null,
     borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     return_date TIMESTAMP,
     borrow_status CHAR(1) not null DEFAULT 'B',
     update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN borrows.borrow_status IS 'B - Borrowed, R - Returned, D - Delayed';
ALTER SEQUENCE public.borrows_borrow_id_seq RESTART WITH 1000;