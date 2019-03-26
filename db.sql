/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.1.32-community : Database - project_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`project_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `project_db`;

/*Table structure for table `customer_reg` */

DROP TABLE IF EXISTS `customer_reg`;

CREATE TABLE `customer_reg` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `house_name` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mob` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `customer_reg` */

insert  into `customer_reg`(`uid`,`name`,`gender`,`house_name`,`street`,`state`,`country`,`pincode`,`email`,`mob`) values 
(1,'minnu','female','raroth','balussery','kerala','india',673612,'minnu12@gmail.com',9605808458),
(2,'Anjali','female','pootholi','balussery','kerala','india',673612,'anjalib@gmail.com',9946128506);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`id`,`username`,`password`,`type`) values 
(0,'admin','admin','appowner'),
(1,'xyzanu@gmail.com','anu456','reject'),
(4,'anju234@gmail.com','anju123','sellers'),
(5,'minnu@gmail.com','min2323','reject'),
(6,'amru96@gmail.com','amru1212','sellers');

/*Table structure for table `offers` */

DROP TABLE IF EXISTS `offers`;

CREATE TABLE `offers` (
  `off_no` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `offers` varchar(50) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `new_price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`off_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `offers` */

insert  into `offers`(`off_no`,`pid`,`offers`,`from_date`,`to_date`,`new_price`) values 
(2,2,'special offers','2019-03-22','2019-03-24',7000);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_no` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `bill_no` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

insert  into `orders`(`order_no`,`uid`,`pid`,`bill_no`,`qty`,`price`,`date`) values 
(1,1,1,1,1,8000,'2019-03-21');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `bill_no` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `bank` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `acct_no` bigint(20) DEFAULT NULL,
  `valid` date DEFAULT NULL,
  `ccv` int(11) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bill_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`bill_no`,`uid`,`bank`,`username`,`acct_no`,`valid`,`ccv`,`amount`,`pincode`,`type`,`status`) values 
(1,1,'fedral','Minnu R',2300014679,'2019-04-18',21,8000,623612,'cash on','payed');

/*Table structure for table `product_mgt` */

DROP TABLE IF EXISTS `product_mgt`;

CREATE TABLE `product_mgt` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `photo_1` varchar(50) DEFAULT NULL,
  `photo_2` varchar(50) DEFAULT NULL,
  `video` varchar(50) DEFAULT NULL,
  `ranking` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `product_mgt` */

insert  into `product_mgt`(`pid`,`category`,`item_name`,`price`,`qty`,`description`,`photo_1`,`photo_2`,`video`,`ranking`) values 
(1,'mobile','vivo x1',7500,1,'high resolution,2GB RAM,5MP Camera','IMG-20180330-WA0065.jpg','IMG-20180223-WA0004.jpg','VID-20170402-WA0005.mp4',5),
(2,'mobile','Asus',8000,5,'Full HD display,\r\n3GB RAM,\r\n13MH camera\r\n','FB_20171230_17_19_45_Saved_Picture.jpg','IMG-20170801-WA0012.jpg','VID-20170402-WA0005.mp4',0);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `review` varchar(50) DEFAULT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `review` */

insert  into `review`(`rid`,`uid`,`pid`,`review`,`photo`,`date`) values 
(4,2,1,'bad','IMG-20170801-WA0012.jpg','2019-03-10');

/*Table structure for table `sellers_reg` */

DROP TABLE IF EXISTS `sellers_reg`;

CREATE TABLE `sellers_reg` (
  `sid` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `company_name` varchar(50) DEFAULT NULL,
  `building_no` int(11) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `mob` bigint(20) DEFAULT NULL,
  `office_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sellers_reg` */

insert  into `sellers_reg`(`sid`,`name`,`company_name`,`building_no`,`city`,`state`,`country`,`pincode`,`email`,`mob`,`office_no`) values 
(1,'anu','xyz',3421,'calicut','kerala','india',673824,'xyzanu@gmail.com',9048231469,'+912654219'),
(4,'Anju','anju\"s',1234,'anjali96@gmail.com','kerala','india',673612,'anju234@gmail.com',9085421706,'+912653421'),
(5,'minnu','minmin',1561,'balussery','kerala','india',673613,'minnu@gmail.com',9946185203,'+912641376'),
(6,'Amru','my shop',1890,'nanmanda','kerala','india',673613,'amru96@gmail.com',9623175088,'+912675423');

/*Table structure for table `site_notification` */

DROP TABLE IF EXISTS `site_notification`;

CREATE TABLE `site_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `site_notification` */

insert  into `site_notification`(`id`,`message`,`date`) values 
(1,'site is under maintance','2019-03-14'),
(2,'maintance','2019-03-14'),
(3,'slow connection','2019-03-14');

/*Table structure for table `stock_notification` */

DROP TABLE IF EXISTS `stock_notification`;

CREATE TABLE `stock_notification` (
  `stk_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`stk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stock_notification` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
