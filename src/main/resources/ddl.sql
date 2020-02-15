CREATE TABLE `dianpingdb`.`user` (
	`id` int NOT NULL AUTO_INCREMENT,
	`create_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
	`updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
	`telphone` varchar(40) NOT NULL,
	`password` varchar(200) NOT NULL,
	`nick_name` varchar(40) NOT NULL DEFAULT '',
	`gender` int DEFAULT 0,
	PRIMARY KEY (`id`),
	UNIQUE `telephone_unique_index` USING BTREE (`telphone`) comment ''
) COMMENT='';


CREATE TABLE `dianpingdb`.`seller` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(80) NOT NULL DEFAULT '',
	`created_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
	`updated_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
	`remark_score` decimal(2,1) NOT NULL DEFAULT 0,
	`disable_flag` int NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)
) COMMENT='';

CREATE TABLE `dianpingdb`.`category` (
	`id` int NOT NULL AUTO_INCREMENT,
	`created_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
	`updated_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
	`name` varchar(20) NOT NULL DEFAULT '',
	`icon_url` varchar(200) NOT NULL DEFAULT '',
	`sort` int NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`),
	UNIQUE `name_unique_index` USING BTREE (`name`) comment ''
) COMMENT='';

CREATE TABLE `dianpingdb`.`shop` (
	`id` int NOT NULL AUTO_INCREMENT,
	`created_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
	`updated_at` datetime NOT NULL DEFAULT '0000-01-01 00:00:00',
	`name` varchar(80) NOT NULL DEFAULT '',
	`remark_score` decimal(2,1) NOT NULL DEFAULT 0,
	`price_per_man` int NOT NULL DEFAULT 0,
	`latitude` decimal(10,6) NOT NULL DEFAULT 0,
	`longtitude` decimal(10,6) NOT NULL DEFAULT 0,
	`category_id` int NOT NULL DEFAULT 0,
	`tags` varchar(2000) NOT NULL DEFAULT '',
	`start_time` varchar(200) NOT NULL DEFAULT '',
	`end_time` varchar(200) NOT NULL DEFAULT '',
	`address` varchar(200) NOT NULL DEFAULT '',
	`seller_id` int NOT NULL DEFAULT 0,
	`icon_url` varchar(100) NOT NULL DEFAULT '',
	PRIMARY KEY (`id`)
) COMMENT='';