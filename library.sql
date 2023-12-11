create table if not exists users(
	user_id serial PRIMARY KEY,
	username varchar(100),
	email varchar(100),
	userPassword varchar(50),
	creationDate date DEFAULT CURRENT_DATE,
	updateDate date DEFAULT CURRENT_DATE,
	deleteDate date DEFAULT CURRENT_DATE
);

create table if not exists books (
	book_id serial PRIMARY KEY,
	bname varchar(100),
	author varchar(80),
	genre varchar(50),
	isDeleted boolean,
	isLoaned boolean,
	creationDate date DEFAULT CURRENT_DATE,
	updateDate date DEFAULT CURRENT_DATE,
	deleteDate date DEFAULT CURRENT_DATE
);

create table if not exists users_books (
	user_book_id serial PRIMARY KEY,
	user_id integer,
	book_id integer,
	creationDate date DEFAULT CURRENT_DATE,
	updateDate date DEFAULT CURRENT_DATE,
	deleteDate date DEFAULT CURRENT_DATE,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (book_id) REFERENCES books(book_id)
);
