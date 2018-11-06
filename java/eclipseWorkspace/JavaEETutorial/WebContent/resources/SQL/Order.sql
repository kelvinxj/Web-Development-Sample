create table customerOrder
(
	orderID int not null primary key auto_increment,
	discount int,
	lastUpdate timestamp,
	shipmentInfo varchar(255),
	status char(1)
)

insert into customerOrder(discount, lastUpdate,shipmentinfo, status)
values(20,current_timestamp, 'shipped from JK','A');