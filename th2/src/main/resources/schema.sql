create table if not exists product (
	code varchar(20),
    description varchar(100),
    price float,
    primary key(code)
);