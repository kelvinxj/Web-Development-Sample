create table book
(
	id int not null primary key auto_increment,
	title varchar(80),
	description varchar(2000),
	pubdate date,
	price decimal(10,2)
);

create table test1
(
	date1 date,
	last_update_dt datetime
);