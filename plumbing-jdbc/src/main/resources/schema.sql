create table if not exists SanitaryWare (
  id varchar(4) not null,
  name varchar(50) not null,
  type varchar(10) not null
);

create table if not exists Basket (
  id identity,
  name varchar(50) not null,
  createdAt timestamp not null
);

create table if not exists Basket_SanitaryWare (
  basket bigint not null,
  sanitaryWare varchar(4) not null
);

alter table Basket_SanitaryWare
    add foreign key (basket) references Basket(id);
alter table Basket_SanitaryWare
    add foreign key (sanitaryWare) references SanitaryWare(id);

create table if not exists SanitaryWare_Order (
	id identity,
	deliveryName varchar(50) not null,
	deliveryStreet varchar(50) not null,
	deliveryCity varchar(50) not null,
	deliveryState varchar(2) not null,
	deliveryZip varchar(10) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists SanitaryWare_Order_Basket (
	basketOrder bigint not null,
	basket bigint not null
);

alter table SanitaryWare_Order_Basket
    add foreign key (basketOrder) references SanitaryWare_Order(id);
alter table SanitaryWare_Order_Basket
    add foreign key (basket) references Basket(id);
