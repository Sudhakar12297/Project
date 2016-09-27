mysql -u Sudhakar -p

sudhakarms

use Railway;

create table userDetails(firstName varchar(50) NOT NULL,lastName varchar(50) NOT NULL,userName varchar(50) NOT NULL,password varchar(50) NOT NULL,isAdmin varchar(10) NOT NULL);

insert into userDetails values('Sudhakar','P','IAmAdmin','iamadmin','yes');

create table trainDetails(trainNo int(5) NOT NULL,trainName varchar(50) NOT NULL,source varchar(50) NOT NULL,destination varchar(50) NOT NULL,sourceTime time NOT NULL,destTime time NOT NULL,via varchar(100) NOT NULL);

insert into trainDetails values('11111','Ernakulam Express','Bangalore','Ernakulam','05:30:00','21:00:00','Hosur,Dharmapuri,Salem,Erode,Coimbatore,Palakkad,Thrissur');
