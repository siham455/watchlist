-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: watchlist
-- ------------------------------------------------------
-- Server version	8.4.3

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
-- Table structure for table `watchlist`
--

DROP TABLE IF EXISTS `watchlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `watchlist` (
  `id` binary(16) NOT NULL,
  `average_duration` int NOT NULL,
  `binge_worthy` bit(1) NOT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `episodes` int DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `imdb_rating` double NOT NULL,
  `seasons` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKden7i83qo2swnr5br4wgc5r7j` (`user_id`),
  CONSTRAINT `FKden7i83qo2swnr5br4wgc5r7j` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watchlist`
--

LOCK TABLES `watchlist` WRITE;
/*!40000 ALTER TABLE `watchlist` DISABLE KEYS */;
INSERT INTO `watchlist` VALUES (_binary '\0\äó\ãp`F´’µ#D“a',148,_binary '','2025-03-02 23:49:16.021338',NULL,'Sci-Fi',8.8,NULL,'Inception','MOVIE',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary '\Ø÷\Ü-\ÔLÀƒÖ¦“\İse',57,_binary '','2025-03-02 23:52:24.199059',73,'Fantasy',9.3,8,'Game of Thrones','TVSHOW',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary '9\0\Ş:+\ßE‘ºz\Ë§>”¼',154,_binary '','2025-03-02 23:52:43.828919',NULL,'Crime',8.9,NULL,'Pulp Fiction','MOVIE',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary 'B\â\Ä½I€Ç‚û£\ÚyB',139,_binary '','2025-03-02 23:53:25.478022',NULL,'Drama',8.8,NULL,'Fight Club','MOVIE',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary '{ƒYy\ÅK\ï›\É}\í\\',22,_binary '','2025-03-02 23:53:01.591572',201,'Comedy',8.9,9,'The Office','TVSHOW',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary '}\\B‘·D@IöJ!oy\Î',152,_binary '','2025-03-02 23:50:51.456496',NULL,'Action',9,NULL,'The Dark Knight','MOVIE',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary '¶¦nFx¤’„!Mj^',136,_binary '','2025-03-02 23:52:01.082524',NULL,'Sci-Fi',8.7,NULL,'The Matrix','MOVIE',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary 'Ü‚ÿ€\ß@‹Qµ@ò\í[',47,_binary '','2025-03-02 23:51:21.916068',62,'Crime',9.5,5,'Breaking Bad','TVSHOW',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary '\æÒ¢\'\å|@TŒÛƒ\Ê\Ãè¬²',51,_binary '','2025-03-02 23:51:42.995413',34,'Drama',8.7,4,'Stranger Things','TVSHOW',_binary '°\ë{£;E“ª¿ñÙºV#'),(_binary '\è¿hNC‘H,­şh_OÄ2',60,_binary '','2025-03-02 23:53:45.472875',22,'Sci-Fi',8.8,5,'Black Mirror','TVSHOW',_binary '°\ë{£;E“ª¿ñÙºV#');
/*!40000 ALTER TABLE `watchlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-02 23:59:00
