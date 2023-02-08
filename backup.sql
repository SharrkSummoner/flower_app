-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: course
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `client_id` int NOT NULL AUTO_INCREMENT,
  `fio` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `client_login` varchar(100) DEFAULT NULL,
  `client_password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Булгаков Семён Григорьевич','г. Нижний Новгород, ул. Советской армии','2002-10-22','bulg','123321'),(2,'Чичиков  Павел Иванович','г. Нижний Новгород, ул. Борисова','2004-02-20','chich','123321'),(3,'Брюзгалин Никита Фёдорович','г. Нижний Новгород, ул. Батумская','2003-01-10','bruzg','123321'),(4,'Пыркин Виталий Андреевич','г. Нижний Новгород, ул. Городская','2003-08-04','pyrk','123321'),(5,'Мамонов Антон Дмитриевич','г. Нижний Новгород, ул. Студенческая','2004-10-20','mamon','123321'),(6,'Иванов Иван Иванович','дом Пушкина, ул. Колот','2003-05-24','ivan','ivan');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;	employee_has_orders

--
-- Table structure for table `clients_has_orders`
--

DROP TABLE IF EXISTS `clients_has_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients_has_orders` (
  `clients_client_id` int NOT NULL,
  `orders_order_id` int NOT NULL,
  PRIMARY KEY (`orders_order_id`,`clients_client_id`),
  KEY `clients_has_orders_FK` (`clients_client_id`),
  CONSTRAINT `clients_has_orders_FK` FOREIGN KEY (`clients_client_id`) REFERENCES `clients` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clients_has_orders_FK_1` FOREIGN KEY (`orders_order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients_has_orders`
--

LOCK TABLES `clients_has_orders` WRITE;
/*!40000 ALTER TABLE `clients_has_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `clients_has_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL,
  `FIO` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `last_enter` datetime DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Стрелов Михаил Денисович','Администратор','г. Нижний Новгород, ул. Алматовская','1999-10-10','strelov','strelov','2022-10-30 00:00:00'),(2,'Букин Виталий Романович','Менеджер','г. Нижний Новгород, ул. Абаканская','2000-01-02','bukin','kostik228','2022-11-02 00:00:00'),(3,'Рысин Роман Дмитриевич','Курьер','г. Нижний Новгород, ул. Кантауровская','2000-01-07','rysin','romasuper111','2022-11-01 00:00:00'),(4,'Чичваркин Евгений Александрович','Курьер','г. Нижний Новгород, ул. Кантауровская','2002-07-09','kyk','kyk123123','2022-10-31 00:00:00'),(5,'Рябынин Илья Алексеевич','Курьер','г. Нижний Новгород, ул. Армейская','1999-08-17','ryba','ilyabely12345','2022-11-03 00:00:00'),(6,'Семёнов Максим Андреевич','Менеджер','г. Нижний Новгород, ул. Кантауровская','2003-04-18','semen','maxposn','2022-11-19 00:00:00'),(20,'Петров П. П.','Курьер','Москва-сити','2004-10-12','petr','petr',NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_has_orders`
--

DROP TABLE IF EXISTS `employee_has_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_has_orders` (
  `employee_employee_id` int NOT NULL,
  `orders_order_id` int NOT NULL,
  PRIMARY KEY (`employee_employee_id`,`orders_order_id`),
  KEY `employee_has_orders_FK_1` (`orders_order_id`),
  CONSTRAINT `employee_has_orders_FK` FOREIGN KEY (`employee_employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_has_orders_FK_1` FOREIGN KEY (`orders_order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_has_orders`
--

LOCK TABLES `employee_has_orders` WRITE;
/*!40000 ALTER TABLE `employee_has_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_has_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `date_order` date DEFAULT NULL,
  `time_date` time DEFAULT NULL,
  `client_id` int DEFAULT NULL,
  `service_id` int DEFAULT NULL,
  `state_order` varchar(100) DEFAULT NULL,
  `time_order` int DEFAULT NULL,
  `service_service_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_FK` (`service_id`),
  CONSTRAINT `orders_FK` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2003-12-01','12:30:00',1,1,NULL,NULL,NULL),(5,'2022-11-02','10:13:49',2,2,'Отменено',0,NULL),(6,'2022-11-02','12:10:14',4,2,'Завершено',34,NULL),(9,'2022-11-03','14:37:58',2,1,'Завершено',26,NULL),(10,'2022-11-03','19:12:38',3,3,'Завершено',19,NULL),(11,'2022-11-19','18:09:43',2,3,'Завершено',30,NULL),(14,'2022-11-19','20:20:20',1,2,'Завершено',29,NULL),(17,'2022-11-20','12:12:12',4,4,'Завершено',11,NULL),(19,'2022-11-20','03:59:40',2,2,'Завершено',20,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `service_id` int NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Самовывоз',30),(2,'Доставка в квартиру',35),(3,'Доставка в костюме',35),(4,'Поздравительное мероприятие с цветами',40),(10,'Красивая упаковка',1000);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-20  1:43:30
