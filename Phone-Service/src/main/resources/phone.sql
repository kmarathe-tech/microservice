/*
SQLyog Community Edition- MySQL GUI v8.02 
MySQL - 5.5.24 : Database - phone
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`phone` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `phone`;

/*Table structure for table `phone` */

CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number_type` varchar(10) DEFAULT NULL,
  `number` decimal(10,0) DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
