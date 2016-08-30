DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
	userId			varchar(12)		NOT NULL,
	password		varchar(12)		NOT NULL,
	name			varchar(12)		NOT NULL,
	email			varchar(50),
	phone          varchar(11),
	Message     varchar(500),
	PRIMARY KEY (userId)
);

DROP TABLE IF EXISTS BOARD;
CREATE TABLE BOARD (
	name			varchar(12)		NOT NULL,
	email			varchar(50),
	phone          varchar(11),
	Message     varchar(500),
	PRIMARY KEY (name)
);


INSERT INTO USERS (userId, password, name, email, phone, message) values ('cieljoin', 'gkswodyd', '한재용', 'cieljoin@gmail.com', '01066636316', 'HI');