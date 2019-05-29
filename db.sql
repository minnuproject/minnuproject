/*
SQLyog Community
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

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `bank` varchar(50) DEFAULT NULL,
  `acct_name` varchar(50) DEFAULT NULL,
  `acct_no` varchar(30) DEFAULT NULL,
  `valid` varchar(50) DEFAULT NULL,
  `ccv` varchar(10) DEFAULT NULL,
  `balance` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

insert  into `bank`(`bank`,`acct_name`,`acct_no`,`valid`,`ccv`,`balance`) values 
('Fedral Bank','Ammu','20550208994118','06/21','084','943263.0'),
('SBI','Anu','40152348751628','04/22','151','228021.0'),
('Fedral Bank','ADMIN ACCOUNT','20321628942375','08/22','067','1040737.0');

/*Table structure for table `blocking` */

DROP TABLE IF EXISTS `blocking`;

CREATE TABLE `blocking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `blocking` */

insert  into `blocking`(`id`,`pid`,`uid`) values 
(1,13,34),
(2,13,34),
(3,18,34),
(4,13,34),
(5,13,34),
(6,13,34),
(7,16,37),
(8,16,37),
(9,16,37),
(10,16,37);

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `customer_reg` */

insert  into `customer_reg`(`uid`,`name`,`gender`,`house_name`,`street`,`state`,`country`,`pincode`,`email`,`mob`) values 
(1,'Amrutha','female','ammu villa','balussery','kerala','india',673612,'ammu34@gmail.com',9048162087),
(26,'anu','Female','sree','calicut','calicut','kerala',679643,'a@gmail.com',976767676),
(34,'Shigha','Female','Shilpa house','Ramanattukara','Kerala','India',654315,'shigha12@gmail.com',8086326345),
(35,' Neethu Nith','Female','little home','Ramanattukara','Kerala','India',654318,'neethunith@gmail.com',7025841425),
(36,'Hari','Male','Krishna','Ernakulam','Kerala','India',683585,'letters4hari@gmail.com',9048610026),
(37,'Dilijith','Male','mydream','kunnamangalam','kerala','India',673570,'diljithm4@gmail.com',8089745424),
(38,'ammu','Female','madhavam','clt','kerala','India',673215,'amru@gmail.com',8901452316),
(42,'mahi','Male','mahizzz','kunamagalam','Kerala','India',673571,'mahizen@gmail.com',9605248189),
(43,'Aswathi','Female','Achu','Balussery','Kerala','India',673612,'achu@gmail.com',9895878142);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`id`,`username`,`password`,`type`) values 
(0,'admin','admin','appowner'),
(4,'anju234@gmail.com','anju123','sellers'),
(13,'ammu34@gmail.com','ammu12','user'),
(26,'a@gmail.com','123','user'),
(27,'asus89@gmail.com','asus89','sellers'),
(28,'samsung12@gmail.com','sams12','sellers'),
(29,'redmiservice@gmail.com','redservice','sellers'),
(30,'oppomobile@gmail.com','oppomob','sellers'),
(31,'appleindia@gmail.com','applein','sellers'),
(32,'lenovoservice@gmail.com','lenovo123','sellers'),
(33,'manu123@gmail.com','manu123','reject'),
(34,'shigha12@gmail.com','shi123','blocked'),
(35,'neethunith@gmail.com','nith456','user'),
(36,'letters4hari@gmail.com','hari@12','user'),
(37,'diljithm4@gmail.com','m4diljith','blocked'),
(38,'amru@gmail.com','amru12','user'),
(42,'mahizen@gmail.com','mahi@12','user'),
(43,'achu@gmail.com','achu12','user');

/*Table structure for table `offers` */

DROP TABLE IF EXISTS `offers`;

CREATE TABLE `offers` (
  `off_no` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `offers` varchar(50) DEFAULT NULL,
  `from_date` varchar(20) DEFAULT NULL,
  `to_date` varchar(20) DEFAULT NULL,
  `old_price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`off_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `offers` */

insert  into `offers`(`off_no`,`pid`,`offers`,`from_date`,`to_date`,`old_price`) values 
(2,2,'20% offer','2019-03-20','2019-03-25',6500),
(3,1,'Holi offers,\r\n10% off','2019-03-21','2019-03-25',7000),
(5,2,'30% off','2019-04-08','2019-04-15',7000),
(6,13,'28% off,normal offer','2019-03-12','2019-04-30',6999),
(7,14,'22% off,special offer','2019-04-01','2019-05-31',10999),
(8,15,'13% off,special offer','2019-04-16','2019-05-14',28990),
(9,16,'25% off, Special offer','2019-04-16','2019-05-20',11990),
(10,17,'23% offer,normal offer','2019-04-08','2019-05-06',18000),
(11,18,'3% offfers, Normal offer','2019-04-08','2019-05-14',28000);

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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

insert  into `orders`(`order_no`,`uid`,`pid`,`bill_no`,`qty`,`price`,`date`) values 
(10,26,15,8,1,24990,'2019-05-01'),
(11,13,18,9,1,26999,'2019-05-01'),
(12,26,18,10,1,26999,'2019-05-02'),
(20,13,16,18,1,8990,'2019-05-03'),
(21,13,15,19,1,24990,'2019-05-03'),
(24,26,15,22,1,24990,'2019-05-03'),
(25,34,14,23,1,8499,'2019-05-03'),
(26,35,17,24,1,13799,'2019-05-03'),
(27,36,13,25,1,4999,'2019-05-03'),
(28,37,16,26,1,8990,'2019-05-03'),
(30,42,15,28,1,24990,'2019-05-05'),
(31,34,18,29,1,26999,'2019-05-05'),
(33,34,13,31,1,4999,'2019-05-05'),
(34,34,13,32,1,4999,'2019-05-05'),
(35,38,14,33,1,8499,'2019-05-06'),
(37,38,15,35,1,24990,'2019-05-06'),
(38,37,16,36,1,8990,'2019-05-06'),
(40,43,13,38,1,4999,'2019-05-06'),
(42,36,17,40,1,13799,'2019-05-07'),
(43,35,19,41,1,16140,'2019-05-07'),
(44,36,13,42,1,4999,'2019-05-08');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `bill_no` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bill_no`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`bill_no`,`uid`,`type`,`status`) values 
(7,26,'cashOnDelivery','pending'),
(8,26,'DebitCard','paid'),
(9,13,'cashOnDelivery','pending'),
(10,26,'cashOnDelivery','pending'),
(11,26,'cashOnDelivery','pending'),
(12,26,'cashOnDelivery','pending'),
(15,26,'cashOnDelivery','pending'),
(16,26,'DebitCard','paid'),
(18,13,'cashOnDelivery','pending'),
(20,13,'cashOnDelivery','pending'),
(21,26,'cashOnDelivery','pending'),
(22,26,'cashOnDelivery','pending'),
(23,34,'cashOnDelivery','pending'),
(24,35,'cashOnDelivery','pending'),
(25,36,'cashOnDelivery','pending'),
(26,37,'cashOnDelivery','pending'),
(27,26,'cashOnDelivery','pending'),
(28,42,'cashOnDelivery','pending'),
(29,34,'cashOnDelivery','pending'),
(30,34,'cashOnDelivery','pending'),
(31,34,'cashOnDelivery','pending'),
(32,34,'cashOnDelivery','pending'),
(33,38,'cashOnDelivery','pending'),
(34,38,'cashOnDelivery','pending'),
(35,38,'DebitCard','paid'),
(36,37,'cashOnDelivery','pending'),
(37,26,'DebitCard','paid'),
(38,43,'cashOnDelivery','pending'),
(39,26,'cashOnDelivery','pending'),
(40,36,'DebitCard','paid'),
(41,35,'DebitCard','paid'),
(42,36,'DebitCard','paid');

/*Table structure for table `product_mgt` */

DROP TABLE IF EXISTS `product_mgt`;

CREATE TABLE `product_mgt` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `description` text,
  `photo_1` varchar(50) DEFAULT NULL,
  `photo_2` varchar(50) DEFAULT NULL,
  `video` varchar(50) DEFAULT NULL,
  `ranking` float DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `product_mgt` */

insert  into `product_mgt`(`pid`,`category`,`item_name`,`price`,`qty`,`description`,`photo_1`,`photo_2`,`video`,`ranking`) values 
(13,'mobile','Asus Zenfone Lite L1',4999,1,'2GB RAM,20GB ROM,\r\n13.84cm display,\r\n13mp+2mp|5mp Front camera,\r\n3000mAh battery','Screenshot_20190427-212652.png','Screenshot_20190427-212715.png','Asus_Zenfone_Lite_L1.mp4',-0.0339999),
(14,'mobile','Asus Zenfone max pro M1',8499,1,'3GB RAM,32GB ROM,\r\n15.21cm FHD display,\r\n13+5mp|8mp camera,\r\n5000mAh battery','Screenshot_20190427-212930.png','Screenshot_20190427-212946.png','Asus_Zenfone_Max_Pro_M1.mp4',4.211),
(15,'mobile','OPPO F11 pro',24990,1,'6GB RAM,64GB ROM,\r\n16.51cm FHD Display,\r\n48mp+5mp|16mp camera,\r\n4000mAh battery','Screenshot_20190427-214100.png','Screenshot_20190427-214103.png','Oppo_F11.mp4',11.211),
(16,'mobile','OPPO A3S',8990,1,'2GB RAM,16GB ROM,\r\n15.75cm HD Display,\r\n13mp+2mp|8mp camera,\r\n4230mAh battery\r\n','Screenshot_20190427-214149.png','Screenshot_20190427-214158.png','OPPO_A3S.mp4',-14.374),
(17,'tab','Lenovo Yoga 3',13799,1,'2GB RAM,16GB ROM,\r\n20.32cm HD Display,\r\n8mp camera,\r\n62000mAh battery','Screenshot_20190427-215227.png','Screenshot_20190427-215306.png','Lenovo_YOGA_3.mp4',0),
(18,'tab','Apple iPad 6th gen',26999,1,'4GB ROM,32GB ROM,\r\n24.64cm Quad HD display,\r\n8mp|1.2mp camera,\r\n7000mAh battery','Screenshot_20190427-215000.png','Screenshot_20190427-215004.png','Apple_Ipad_6th.mp4',0.804),
(19,'tab','Lenovo Tab 4',16140,1,'3GB RAM,16GB ROM,\r\n20.32cm  FHD Display,\r\n8mp|5mp camera,\r\n4850mAh battery','Screenshot_20190427-215350.png','Screenshot_20190427-215401.png','Lenovo_YOGA_3.mp4',1);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `review` varchar(50) DEFAULT NULL,
  `photo` varchar(80) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=latin1;

/*Data for the table `review` */

insert  into `review`(`rid`,`uid`,`pid`,`review`,`photo`,`rating`,`date`) values 
(18,26,18,'Awesome product,good display','Screenshot_20190427-2150002.png',3.5,'2019-05-01'),
(31,26,16,'waste of money','Screenshot_20190427-2141492.png',4.5,'2019-05-03'),
(32,26,16,'good','Screenshot_20190427-2141492.png',2.5,'2019-05-03'),
(33,26,15,'awesome','Screenshot_20190427-2141002.png',3.5,'2019-05-03'),
(35,26,13,'good phone','Screenshot_20190427-2126522.png',3,'2019-05-03'),
(36,26,14,'Nice phone','Screenshot_20190427-2129302.png',4,'2019-05-03'),
(37,26,16,'heavy heating','Screenshot_20190427-2152272.png',2.5,'2019-05-03'),
(38,26,17,'heavy heating','Screenshot_20190427-2152272.png',2.5,'2019-05-03'),
(41,26,17,'bad','Screenshot_20190427-2153062.png',1.5,'2019-05-03'),
(42,26,18,'awesome product','Screenshot_20190427-2155182.png',4,'2019-05-03'),
(43,36,13,'bad','Screenshot_20190427-2127152.png',2,'2019-05-03'),
(44,37,16,'average','IMG-20190501-WA0000.jpg',2.5,'2019-05-03'),
(45,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(46,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(47,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(48,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(49,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(50,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(51,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(52,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(53,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(54,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(55,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(56,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(57,26,16,'bad','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(58,26,16,'nice','Screenshot_20190427-2139362.png',1.5,'2019-05-03'),
(59,26,14,'nice product','IMG-20190501-WA0001.jpg',2.5,'2019-05-03'),
(60,26,15,'excellent','Screenshot_20190417-2156032.png',3.5,'2019-05-03'),
(65,34,18,'bad','DSC_0658.JPG',1.5,'2019-05-04'),
(70,42,15,'nice','magazine-unlock-01-2.3.1173-_C9BDA5DAF0756B9870864498F535297C.jpg',2,'2019-05-05'),
(73,34,14,'good','that_innerpeace___Bvye5eWHFSW___.jpg',2,'2019-05-05'),
(74,34,17,'good','go2words___BkcQtwmlH7B___.jpg',2,'2019-05-05'),
(75,38,13,'average','anjuna_____BrXsvWKBxhwOPxPF_6NTTjePlqQRhBd0QXnM5k0___.jpg',2,'2019-05-06'),
(83,38,14,'nice product','anjuna_____BrXsvWKBxhwOPxPF_6NTTjePlqQRhBd0QXnM5k0___.jpg',3,'2019-05-06'),
(84,37,16,'bad','magazine-unlock-01-2.3.1173-_C9BDA5DAF0756B9870864498F535297C.jpg',2,'2019-05-06'),
(85,37,16,'very bad','magazine-unlock-01-2.3.1173-_C9BDA5DAF0756B9870864498F535297C.jpg',2,'2019-05-06'),
(86,37,16,'heating','magazine-unlock-01-2.3.1173-_C9BDA5DAF0756B9870864498F535297C.jpg',2,'2019-05-06'),
(89,26,14,'good','magazine-unlock-01-2.3.1173-_C9BDA5DAF0756B9870864498F535297C.jpg',2,'2019-05-06'),
(93,26,18,'good','Screenshot_20190505-181749_Samsung_Experience_Home.jpg',3,'2019-05-06'),
(94,26,15,'good','FB_IMG_1548855983413.jpg',3,'2019-05-06'),
(95,26,15,'good','FB_IMG_1548855983413.jpg',3,'2019-05-06'),
(96,26,15,'good','FB_IMG_1548855983413.jpg',3,'2019-05-06'),
(97,26,15,'nice camera','IMG-20160926-WA0003.jpg',3,'2019-05-07'),
(98,26,15,'nice camera','IMG-20160926-WA0003.jpg',3,'2019-05-07'),
(99,26,15,'nice camera','IMG-20160926-WA0003.jpg',3,'2019-05-07'),
(100,36,13,'average','Screenshot_20190427-2126522.png',2,'2019-05-07'),
(101,36,13,'average','Screenshot_20190427-2126522.png',2,'2019-05-07'),
(102,36,13,'average','Screenshot_20190427-2126522.png',2,'2019-05-07'),
(103,36,13,'average','Screenshot_20190427-2126522.png',2,'2019-05-07'),
(104,36,13,'nice','Screenshot_20190427-2127152.png',2,'2019-05-07'),
(105,35,19,'good','Screenshot_20190427-2154012.png',2.5,'2019-05-07'),
(106,36,13,'good','Screenshot_20190427-2155182.png',2.5,'2019-05-08'),
(107,36,13,'good','Screenshot_20190427-2155182.png',2.5,'2019-05-08');

/*Table structure for table `sel_product` */

DROP TABLE IF EXISTS `sel_product`;

CREATE TABLE `sel_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `sel_product` */

insert  into `sel_product`(`id`,`pid`,`sid`) values 
(1,8,4),
(2,1,4),
(3,9,4),
(4,10,4),
(5,12,4),
(6,13,27),
(7,14,27),
(8,15,30),
(9,16,30),
(10,17,32),
(11,18,31),
(12,19,32);

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
(4,'Anju','myShop',1234,'calicut','kerala','india',673612,'anju234@gmail.com',9085421706,'+912653421'),
(27,'Liao','Asus',5443,'Mumbai','Maharashtra','India',400058,'asus89@gmail.com',9923147804,'+916159860'),
(28,'Lee Byung','Samsung',3421,'Gurgaon','Haryana','India',122202,'samsung12@gmail.com',8012563289,'+914881342'),
(29,'Jun Lei','Redmi',6573,'Bangalore','Karnataka','India',560103,'redmiservice@gmail.com',9745690214,'+916230286'),
(30,'Chen Yona','Oppo',6721,'Gurgaon','Haryana','India',122202,'oppomobile@gmail.com',7021367891,'+914932777'),
(31,'Stev Jobs','Apple',2989,'Hyderabad','Telangana','India',500012,'appleindia@gmail.com',9456781245,'+916480749'),
(32,'John Liu','Lenovo',3478,'Gurgaon','Haryana','India',122018,'lenovoservice@gmai.com',8907341287,'+914860172'),
(33,'manu','my building',1234,'calicut','kerala','India',673612,'manu123@gmail.com',9076341278,'+916734152');

/*Table structure for table `site_notification` */

DROP TABLE IF EXISTS `site_notification`;

CREATE TABLE `site_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(50) DEFAULT NULL,
  `date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `site_notification` */

insert  into `site_notification`(`id`,`message`,`date`) values 
(1,'site is under maintance','2019-03-14'),
(3,'slow connection','2019-03-14'),
(4,'very bad connection','2019-04-03'),
(5,'server problem','2019-04-03'),
(7,'bad network','2019-04-10');

/*Table structure for table `stock_notification` */

DROP TABLE IF EXISTS `stock_notification`;

CREATE TABLE `stock_notification` (
  `stk_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`stk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `stock_notification` */

insert  into `stock_notification`(`stk_id`,`pid`,`sid`,`stock`,`date`) values 
(1,1,4,7,'2019-04-01'),
(6,13,27,4,'2019-05-01'),
(7,14,27,2,'2019-05-01'),
(8,15,30,0,'2019-05-01'),
(9,16,30,4,'2019-05-01'),
(10,17,32,3,'2019-05-01'),
(11,18,31,2,'2019-05-01');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
