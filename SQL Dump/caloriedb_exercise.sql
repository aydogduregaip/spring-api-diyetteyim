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
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `id` int NOT NULL,
  `calorie` double DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (97,15,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/kos.jpg','Koşu','a1f59680-fd36-4f13-8998-684ec19292a6'),(98,13,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/bisiket.jpg','Bisiklet sürmek','7f69becd-3921-4bed-a9eb-7ca12a932234'),(99,10,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/basketball.jpg','Basketbol','62d2e9ef-0101-443b-91ab-80a11947920d'),(100,14,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/ippatlama.jpg','İp atlamak','6604e4b1-9ed5-4011-8f3f-9e640b995165'),(101,8,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/enis(1).jpg','Tenis oynamak','d9a71322-ffc4-44d4-ad53-95164eb2d934'),(102,4,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/yurumek(1).jpg','Yürüyüş yapmak','7eee5f0e-c00f-41a3-a287-5845fff18f46'),(103,5,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/voleybol(1).jpg','Voleybol oynamak ','ce0414f5-ae85-4e68-bc0a-b9d2aa8e741b'),(104,8,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/futboloynamak.jpg','Futbol oynamak','99016ff1-c35b-445a-87fc-f4f1f7643196'),(105,8,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/squad.jpg','Squat Egzersizi','9a310bd8-a236-413d-b36d-4d94ccda3cff'),(106,8,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/lunge.jpg','Lunge Egzersizi','626afc7e-ba03-4fb1-954d-9ada04dc83ac'),(107,6,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/pushups.jpg','Push ups','1dfe6c08-089d-4398-9d25-d5fcbeeca9a1'),(108,6,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/mekik.jpg','Mekik çekmek','bc05870f-b249-43fb-a27a-f60713d35422'),(109,5,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/wallsi.jpg','Wall sit','581b462a-5187-4b55-b727-072f9d7229e1'),(110,7,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/kolegzersizi.jpg','Kol egzersizleri','c964d531-935a-478e-9969-2ae0caf2b0d8'),(111,7,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/jumping-jack.jpg','Jumping Jack','18e48978-0976-4a5c-81fe-20b48a454df9'),(112,10,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/pplank.jpg','Plank','e6f8b0cf-6186-4cc7-9b0c-8a413bbd68b6'),(113,6,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/ziplama.jpg','Zıplamak','10ef53e3-90ae-4b27-8793-9463b3ab9650'),(114,6,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/bowling(1).jpg','Bowling oynamak','fc0927f0-7b13-4de3-a46e-e0180b081238'),(115,10,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/sepp.jpg','Step yapmak (Düşük yoğunluklu)','3afdc358-7d23-44a6-9074-519dadf243a7'),(116,5,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/atbinmek(1).jpg','At binmek','69c659e4-4731-4afe-8308-c59ff1c21228'),(117,8,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/dalis(1).jpg','Dalış yapmak','4d83e0a4-c26a-4fe6-9a9e-3300b8c802ea'),(118,9,'https://shreddedbrothers.com/uploads/blogs/ckeditor/files/kayak.jpg','Kayak yapmak','8b8a4903-c90e-431a-8add-cfa0bb5ad089');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-14 22:29:36
