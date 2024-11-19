drop table if exists BOOK;
create table BOOK(
  BOOK_ID int not null AUTO_INCREMENT,
  TITLE varchar(100) NOT NULL,
  AUTHOR varchar(250) NOT NULL,
  ISBN varchar(100) UNIQUE,
  CATEGORY VARCHAR (100),
  AVAILABLE BOOLEAN,
  PRIMARY KEY ( BOOK_ID )
);
