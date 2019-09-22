CREATE DATABASE IF NOT EXISTS Rent;

CREATE TABLE IF NOT EXISTS RentRecord (
	id int unsigned not null auto_increment,
    price int not null,
    source_type varchar(20) not null,
    rent_type varchar(20) not null,
    house_type varchar(20) not null,
    district varchar(20) not null,
    address varchar(100) not null,
    title varchar(100) not null,
    page_url varchar(180) not null,
    release_date datetime not null,
    page_content varchar(1000) not null,
    gender_limit varchar(10) not null,
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;