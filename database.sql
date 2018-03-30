drop database if exists ecafe;
create database ecafe;
use ecafe;
#getmethods table will contain delivery or pickup entries
drop table if exists orderdelivery;
create table orderdelivery(id int primary key not null, name varchar(30) not null);

drop table if exists paymentmethods;
create table paymentmethods(methodid int primary key not null, methodname varchar(30) not null);

drop table if exists customer;
create table customer (id int not null Primary Key, username varchar(30) not null, pass varchar(30) not null,paymentmethod int not null,address varchar(50) not null);

drop table if exists creditcard;
create table creditcard(userid int not null, creditcard varchar(30) not null,PRIMARY KEY(userid), FOREIGN KEY (userid) REFERENCES customer(id));

drop table if exists fooditems;
create table fooditems(itemid int Primary Key not null, itemname varchar(30) not null,description varchar(30),price Decimal(10,2) not null);

drop table if exists orderdetails;
create table orderdetails(orderid int not null, itemid int not null, countval int not null, peritemprice Decimal(10,2) not null, primary key(orderid, itemid), foreign key(itemid) references fooditems(itemid));

drop table if exists orderrepo;
create table orderrepo(orderid int not null, userid int not null, pmethodid int not null,timeval datetime not null,foreign key(pmethodid) references paymentmethods(methodid), primary key(orderid,userid), foreign key(orderid) references orderdetails(orderid));

drop table if exists currentorder;
create table currentorder(orderid int not null,  userid int not null, dmethodid int not null,timeval datetime not null, foreign key(dmethodid) references orderdelivery(id));#for showing to staff

drop table if exists users;
create table users(id int primary key not null, username varchar(30) not null, password varchar(30) not null, type varchar(20) not null);


insert into users values(1,"hassan","abc","customer"),(2,"ayesha","abc","admin"),(3,"zaid","abc","staff");

insert into orderdelivery values(1,'Delivery'),(2,'Cash');

insert into paymentmethods values(1,'Credit Card'),(2,'Pick-up);

insert into customer values(1, 'hassan','password',1,'House ABC'),(2,'Ali','password2',1,'House GHB'),(3,'Waqar','password3',2,'House QEE');

insert into creditcard values(1,'1289379127'),(2,'3981993332');

insert into fooditems values(1,'HamBurger','Beefburger', 10.45),(2,'Pizza','Cheese Pizza', 19.20);

insert into orderdetails values (1,1,1,10.45),(1,2,2,19.2),(2,1,3,10.45),(3,1,2,10.45),(3,2,2,19.2);

insert into orderrepo values(1,2,1,'2018-01-17 23:10:25'),(2,1,1,'2018-02-27 11:10:25'),(3,3,2,'2018-03-01 07:50:00');

insert into currentorder values(3,3,2,'2018-03-01 07:50:00'),(2,2,1,'2018-02-01 07:50:00');







