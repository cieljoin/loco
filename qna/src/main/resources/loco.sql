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


INSERT INTO USERS VALUES('cieljoin', 'gkswodyd', '한재용', 'cieljoin@gmail.com', '01066636316', 'HI');