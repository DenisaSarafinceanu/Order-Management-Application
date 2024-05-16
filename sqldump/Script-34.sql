create table Client(
	ID integer primary key,
	name varchar(50) not null,
    address varchar(100) not null,
	email varchar(50) not null
)

insert into Client values(1, 'Denisa', 'Str Observatorului nr 1', 'denisa@yahoo.com')
insert into Client values(2, 'Andrei', 'Str Aurel Vlaicu', 'andrei@gmail.com')

create table Product(
	id integer primary key,
	name varchar(50) not null,
	price real not null,
	stock integer not null
)

insert into Product values(1, 'tricou', 90.6, 22)

create table Orders (
	id integer primary key,
	clientID integer not null,
	productID integer not null,
	quantity varchar not null,
	foreign key (clientID) references Client(id),
	foreign key (productID) references Product(id)
)

insert into Orders values(1, 1, 1, 2)
insert into Orders values(5, 2, 1, 3)