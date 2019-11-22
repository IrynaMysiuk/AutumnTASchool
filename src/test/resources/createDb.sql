create database library_test_data;
use library_test_data;

create table author (
    authorId bigint primary key unique not null,
    `authorName.first` varchar(50) not null,
    `authorName.second` varchar(50) not null,
    `birth.city` varchar(100),
    `birth.country` varchar(100),
    `birth.date` date,
    authorDescription varchar(1000),
    nationality varchar(30)
);

create table genre (
	genreId bigint primary key unique not null,
    genreName varchar(50) not null,
    genreDescription varchar(1000)
);

create table book (
    bookId bigint primary key unique not null,
    bookName varchar(255) not null,
    bookLanguage varchar(50) not null,
    bookDescription varchar(1000),
    `additional.size.height` double precision,
    `additional.size.length` double precision,
    `additional.size.width` double precision,
    `additional.pageCount` integer,
    publicationYear integer
);