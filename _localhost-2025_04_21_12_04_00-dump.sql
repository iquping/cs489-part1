-- MySQL dump 10.13  Distrib 5.7.44, for osx10.19 (x86_64)
--
-- Host: 127.0.0.1    Database: cs489-part1
-- ------------------------------------------------------
-- Server version	5.7.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `astronaut`
--

DROP TABLE IF EXISTS `astronaut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `astronaut` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `experience_years` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `astronaut`
--

LOCK TABLES `astronaut` WRITE;
/*!40000 ALTER TABLE `astronaut` DISABLE KEYS */;
INSERT INTO `astronaut` VALUES (1,12,'Neil','Armstrong'),(2,8,'Sally','Ride'),(3,15,'Chris','Hadfield'),(4,15,'Ping','Qu');
/*!40000 ALTER TABLE `astronaut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `astronaut_satellite`
--

DROP TABLE IF EXISTS `astronaut_satellite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `astronaut_satellite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `astronaut_id` bigint(20) DEFAULT NULL,
  `satellite_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmb69qijbet80ppx19v4r2jxii` (`astronaut_id`),
  KEY `FKc9mrfoxyu1i2ebu3wa1ygjkbk` (`satellite_id`),
  CONSTRAINT `FKc9mrfoxyu1i2ebu3wa1ygjkbk` FOREIGN KEY (`satellite_id`) REFERENCES `satellite` (`id`),
  CONSTRAINT `FKmb69qijbet80ppx19v4r2jxii` FOREIGN KEY (`astronaut_id`) REFERENCES `astronaut` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `astronaut_satellite`
--

LOCK TABLES `astronaut_satellite` WRITE;
/*!40000 ALTER TABLE `astronaut_satellite` DISABLE KEYS */;
INSERT INTO `astronaut_satellite` VALUES (1,1,1),(2,1,2),(3,2,2),(4,2,3),(5,3,1),(6,3,2),(7,3,3),(8,4,3),(9,4,1),(10,4,2);
/*!40000 ALTER TABLE `astronaut_satellite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `satellite`
--

DROP TABLE IF EXISTS `satellite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `satellite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `decommissioned` bit(1) NOT NULL,
  `launch_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orbit_type` enum('GEO','LEO','MEO') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKea6puiq1cvjjl5w6xj3oq8jty` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `satellite`
--

LOCK TABLES `satellite` WRITE;
/*!40000 ALTER TABLE `satellite` DISABLE KEYS */;
INSERT INTO `satellite` VALUES (1,_binary '\0','1990-04-24','Hubble','LEO'),(2,_binary '\0','1990-04-24','Hubble Space Telescope','LEO'),(3,_binary '','2020-11-21','Sentinel-6','LEO');
/*!40000 ALTER TABLE `satellite` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-21 12:04:00
