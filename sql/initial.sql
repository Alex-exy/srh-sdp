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


DROP TABLE IF EXISTS borrows;

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


DROP TABLE IF EXISTS books;

CREATE TABLE books
(
    book_id           BIGSERIAL       PRIMARY KEY,
    book_name          VARCHAR(100)       NOT NULL,
    subtitles          VARCHAR(50)        NOT NULL,
    language           VARCHAR(20)        NOT NULL,
    isbn               VARCHAR(20)        NOT NULL,
    publish_date       VARCHAR(20)        NOT NULL,
    book_author        VARCHAR(100)       NOT NULL,
    genre              VARCHAR(50)        NOT NULL,
    price              VARCHAR(10)        NOT NULL,
    book_description   VARCHAR(200)       NOT NULL,
    addition_date      TIMESTAMP          DEFAULT CURRENT_TIMESTAMP,
    update_date        TIMESTAMP          DEFAULT CURRENT_TIMESTAMP,
    library_id         VARCHAR(20)        NOT NULL,
    doi                VARCHAR(20)        NOT NULL

);

COMMENT ON COLUMN books.doi IS 'DIGITAL OBJECT IDENTIFIERS ARE UNIQUE ALPHANUMERIC CODES ASSIGNED BY PUBLISHERS';
COMMENT ON COLUMN books.price IS 'PRICE IS IN EUROS';

DROP TABLE IF EXISTS genre;

CREATE TABLE genre
(
    genre_id             SERIAL           PRIMARY KEY,
    genre_name           VARCHAR(64)      UNIQUE NOT NULL
);

insert into genre(genre_name)values
 ('Action and adventure'),
 ('Art/architecture'),
 ('Alternate history'),
 ('Autobiography'),
 ('Anthology'),
 ('Biography'),
 ('Chick lit'),
 ('Business/economics'),
 ('Children'),
 ('Crafts/hobbies'),
 ('Classic'),
 ('Cookbook'),
 ('Comic book'),
 ('Diary'),
 ('Coming-of-age'),
 ('Dictionary'),
 ('Crime'),
 ('Encyclopedia'),
 ('Drama'),
 ('Guide'),
 ('Fairytale'),
 ('Health/fitness'),
 ('Fantasy'),
 ('History'),
 ('Graphic novel'),
 ('Home and garden'),
 ('Historical fiction'),
 ('Humor'),
 ('Horror'),
 ('Journal'),
 ('Mystery'),
 ('Math'),
 ('Paranormal romance'),
 ('Memoir'),
 ('Picture book'),
 ('Philosophy'),
 ('Poetry'),
 ('Prayer'),
 ('Political thriller'),
 ('Religion, spirituality, and new age'),
 ('Romance'),
 ('Textbook'),
 ('Satire'),
 ('Science fiction'),
 ('Review'),
 ('Short story'),
 ('Science'),
 ('Suspense'),
 ('Self help'),
 ('Thriller'),
 ('Sports and leisure'),
 ('Western'),
 ('Travel'),
 ('Young adult'),
 ('True crime');
