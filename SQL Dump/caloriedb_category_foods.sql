-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: caloriedb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category_foods`
--

DROP TABLE IF EXISTS `category_foods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_foods` (
  `food_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`food_id`,`category_id`),
  KEY `FKlv44jgg9goruqwqxlvctkb65` (`category_id`),
  CONSTRAINT `FKfpek0qarakw55ittf5ahpf8uh` FOREIGN KEY (`food_id`) REFERENCES `foods` (`id`),
  CONSTRAINT `FKlv44jgg9goruqwqxlvctkb65` FOREIGN KEY (`category_id`) REFERENCES `foodcategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_foods`
--

LOCK TABLES `category_foods` WRITE;
/*!40000 ALTER TABLE `category_foods` DISABLE KEYS */;
INSERT INTO `category_foods` VALUES (8,3),(42,3),(43,3),(136,3),(137,3),(10,4),(44,4),(45,4),(46,4),(47,4),(49,4),(50,4),(51,4),(156,4),(157,4),(158,4),(159,4),(160,4),(4,5),(12,5),(28,5),(29,5),(30,5),(122,5),(79,6),(80,6),(81,6),(82,6),(83,6),(84,6),(85,6),(86,6),(154,6),(155,6),(1,8),(3,8),(5,8),(9,8),(13,8),(14,8),(15,8),(16,8),(17,8),(18,8),(19,8),(20,8),(21,8),(22,8),(24,8),(25,8),(26,8),(27,8),(52,8),(53,8),(54,8),(55,8),(56,8),(57,8),(58,8),(59,8),(60,8),(61,8),(62,8),(63,8),(64,8),(65,8),(66,8),(67,8),(68,8),(69,8),(70,8),(120,8),(123,8),(124,8),(125,8),(126,8),(127,8),(128,8),(139,8),(140,8),(141,8),(142,8),(143,8),(144,8),(71,9),(72,9),(73,9),(74,9),(75,9),(76,9),(77,9),(78,9),(150,9),(151,9),(152,9),(153,9),(87,10),(88,10),(89,10),(90,10),(91,10),(92,10),(93,10),(94,10),(95,10),(121,10),(145,10),(146,10),(147,10),(148,10),(149,10),(6,11),(31,11),(32,11),(33,11),(34,11),(35,11),(36,11),(37,11),(129,11),(130,11),(131,11),(38,12),(39,12),(40,12),(41,12),(132,12),(133,12),(134,12),(135,12),(7,13),(138,13);
/*!40000 ALTER TABLE `category_foods` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-14 22:29:37
