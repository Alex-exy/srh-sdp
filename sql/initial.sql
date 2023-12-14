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
    school_id         INT                 NOT NULL,
    registration_date TIMESTAMP           DEFAULT CURRENT_TIMESTAMP,
    update_time       TIMESTAMP           DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN users.user_role IS 'S - Student, T - Teacher';
COMMENT ON COLUMN users.user_status IS 'A - Active, O - Overdue, F - Frozen, I - Inactive';

ALTER SEQUENCE public.users_user_id_seq RESTART WITH 1000;

-- Test users, password: 123456
INSERT INTO users(email, user_role, first_name, family_name, address, user_status, password_hash, school_id)
VALUES('jack.muller@stud.hochschule-heidelberg.de', 'S', 'a', 'b', 'Germany', 'A', '$2a$10$EZAvbMiNBodibBxH3i2BRuHcehAngMJ6pbLhP6b5SFAEpIdU/qIZS', 1000);

drop table if exists schools;

CREATE TABLE schools
(
    school_id            SERIAL PRIMARY KEY,
    school_name          VARCHAR(255) UNIQUE NOT NULL,
    create_time          TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    update_time          TIMESTAMP  DEFAULT CURRENT_TIMESTAMP
);
ALTER SEQUENCE public.schools_school_id_seq RESTART WITH 1000;

insert into schools(school_id, school_name)
values(1000, 'SRH University Heidelberg'),(1001, 'Heidelberg University');

ALTER TABLE users ADD FOREIGN KEY (school_id) REFERENCES schools (school_id) ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE TABLE admins
(
    admin_user_name      VARCHAR(64) UNIQUE NOT NULL,
    admin_password_hash  VARCHAR(64) NOT NULL,
    create_time          TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    update_time          TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);

-- default administrator: admin 123456
insert into admins(admin_user_name, admin_password_hash)
values ('admin','$2a$10$EZAvbMiNBodibBxH3i2BRuHcehAngMJ6pbLhP6b5SFAEpIdU/qIZS');

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
    book_id            BIGSERIAL       PRIMARY KEY,
    book_name          VARCHAR(64)        NOT NULL,
    subtitles          VARCHAR(128)       NOT NULL,
    language           VARCHAR(32)        NOT NULL,
    isbn               VARCHAR(15)        NOT NULL,
    publish_date       VARCHAR(20)        NOT NULL,
    book_author        VARCHAR(255)       NOT NULL,
    genre_id           INT                NOT NULL,
    price              VARCHAR(10)        NOT NULL,
    book_description   VARCHAR(1024)      NOT NULL,
    addition_date      TIMESTAMP          DEFAULT CURRENT_TIMESTAMP,
    update_date        TIMESTAMP          DEFAULT CURRENT_TIMESTAMP,
    library_id         INT                NOT NULL,
    doi                VARCHAR(20)        NULL

);
CREATE INDEX idx_book_name ON books (book_name);
CREATE INDEX idx_book_author ON books (book_author);
CREATE INDEX idx_isbn ON books (isbn);
CREATE INDEX idx_doi ON books (doi);


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

ALTER SEQUENCE public.genre_genre_id_seq RESTART WITH 100;

DROP TABLE IF EXISTS libraries

CREATE TABLE libraries
(
    library_id             SERIAL           PRIMARY KEY,
    library_name           VARCHAR(64)      UNIQUE NOT NULL
);
