create table restaurant(
	id int auto_increment primary key,
	name varchar(255) not null,
	address varchar(255) not null
);

create table customer(
	id int auto_increment primary key,
	name varchar(255) not null,
	email varchar(255) not null
);

create table reservation(
	id int auto_increment primary key,
	restaurant_Id int,
	customer_Id int,
	foreign key (restaurant_Id) references Restaurant(id),
	foreign key (customer_Id) references Customer(id)
);