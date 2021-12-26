-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: spark_dbms
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `batch`
--



DROP TABLE IF EXISTS `batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `batch` (
  `batchId` varchar(255) NOT NULL,
  `courseId` varchar(255) NOT NULL,
  `batchName` varchar(255) NOT NULL,
  `fee` int NOT NULL,
  `roomNumber` int NOT NULL,
  `startTime` time DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  PRIMARY KEY (`batchId`,`courseId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `batch_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch`
--

LOCK TABLES `batch` WRITE;
/*!40000 ALTER TABLE `batch` DISABLE KEYS */;
INSERT INTO `batch` VALUES ('1','1','Spark Batch 1',20000,1,'09:00:00','17:00:00'),('1','2','Spark Batch',30000,6,'11:33:00','14:34:00'),('1','4','Spark Batch',20000,4,'09:00:00','18:00:00'),('2','1','toppers batch',10000,3,'01:59:00','17:59:00'),('3','1','droppers batch',30000,4,'09:31:00','10:32:00');
/*!40000 ALTER TABLE `batch` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `courseId` varchar(255) NOT NULL,
  `courseName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  UNIQUE KEY `courseName` (`courseName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('1','IIT JEE ( Mains + Advance ) ','full fledged preparation for jee mains and advance '),('2','JEE mains','complete preparation of jee mains '),('3','BITSAT','complete BITSAT preparation '),('4','NEET','for NEET exam preparation ');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesubjectdetails`
--

DROP TABLE IF EXISTS `coursesubjectdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `coursesubjectdetails` (
  `courseId` varchar(255) NOT NULL,
  `subjectId` varchar(255) NOT NULL,
  PRIMARY KEY (`courseId`,`subjectId`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `coursesubjectdetails_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coursesubjectdetails_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `subject` (`subjectId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesubjectdetails`
--

LOCK TABLES `coursesubjectdetails` WRITE;
/*!40000 ALTER TABLE `coursesubjectdetails` DISABLE KEYS */;
INSERT INTO `coursesubjectdetails` VALUES ('3','AT'),('1','CHE'),('2','CHE'),('3','CHE'),('4','CHE'),('3','ENG'),('1','MA'),('2','MA'),('3','MA'),('1','PHY'),('2','PHY'),('3','PHY'),('4','PHY'),('4','ZLY');
/*!40000 ALTER TABLE `coursesubjectdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeId` int NOT NULL AUTO_INCREMENT,
  `basicSalary` int DEFAULT NULL,
  `joinDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `panNumber` varchar(255) NOT NULL,
  `accountNumber` varchar(255) NOT NULL,
  `houseNumber` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`employeeId`),
  UNIQUE KEY `panNumber` (`panNumber`),
  UNIQUE KEY `accountNumber` (`accountNumber`),
  KEY `userId` (`userId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,0,NULL,NULL,'AAAAA1234A','434243','22','ASsd','dfg','ert',10),(4,2000,'2021-11-05','2021-11-05','AAAAA1234C','878234872','21','Grey Fox Farm Road , TX 77002','Houston','UP',13),(10,15000,'2021-11-16',NULL,'AAABA1234U','34532432','468 ','Hillcrest Avenue ','Waltham, MA','JH',36),(11,25000,'2021-11-16',NULL,'AAAYU1234A','213312112','927',' Franklee','Philadelphia, PA ','JK',37),(12,30000,'2021-11-16',NULL,'AAATY1234C','34234234','1780',' Libby Street ','Burbank, CA','JH',38),(13,30000,'2021-11-16',NULL,'AAAUI1234A','327984279','3666 ','Michigan Avenue ','Avonmore, PA ','JK',39),(14,20000,'2021-11-17',NULL,'AAABJ1234A','824372434','21',' Franklee Lane ','Omaha, NE ','JH',40);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `enrollment` (
  `enrollmentId` int NOT NULL AUTO_INCREMENT,
  `studentId` int NOT NULL,
  `batchId` varchar(255) DEFAULT NULL,
  `courseId` varchar(255) DEFAULT NULL,
  `transactionId` int NOT NULL,
  `joinDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  PRIMARY KEY (`enrollmentId`),
  KEY `studentId` (`studentId`),
  KEY `batchId` (`batchId`,`courseId`),
  KEY `transactionId` (`transactionId`),
  CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`batchId`, `courseId`) REFERENCES `batch` (`batchId`, `courseId`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `enrollment_ibfk_3` FOREIGN KEY (`transactionId`) REFERENCES `transaction` (`transactionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (7,18,'1','1',10,NULL,NULL),(8,18,'1','4',11,'2021-11-17',NULL),(9,18,'1','2',12,'2021-11-09',NULL),(10,21,'2','1',13,NULL,NULL),(11,21,'1','4',14,NULL,NULL);
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardian`
--

DROP TABLE IF EXISTS `guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `guardian` (
  `name` varchar(255) NOT NULL,
  `studentId` int NOT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `relationWithStudent` enum('Father','Mother','Other') NOT NULL,
  PRIMARY KEY (`name`,`studentId`),
  KEY `studentId` (`studentId`),
  CONSTRAINT `guardian_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardian`
--

LOCK TABLES `guardian` WRITE;
/*!40000 ALTER TABLE `guardian` DISABLE KEYS */;
INSERT INTO `guardian` VALUES ('Laurie ',21,'Gas pumping station operator','3529 Hartland Avenue Menasha, WI 54952','q@gmail.com','Father'),('Mary C. Gatewood',18,'Cartographer','3490 Fulton Street Wheeling, WV 26003','SD201@gmail.com','Father');
/*!40000 ALTER TABLE `guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardianphonenumber`
--

DROP TABLE IF EXISTS `guardianphonenumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `guardianphonenumber` (
  `phoneNumber` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `studentId` int NOT NULL,
  PRIMARY KEY (`phoneNumber`,`name`,`studentId`),
  KEY `name` (`name`,`studentId`),
  CONSTRAINT `guardianphonenumber_ibfk_1` FOREIGN KEY (`name`, `studentId`) REFERENCES `guardian` (`name`, `studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardianphonenumber`
--

LOCK TABLES `guardianphonenumber` WRITE;
/*!40000 ALTER TABLE `guardianphonenumber` DISABLE KEYS */;
/*!40000 ALTER TABLE `guardianphonenumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `payroll` (
  `paymentRefNo` varchar(255) NOT NULL,
  `month` varchar(255) NOT NULL,
  `year` int NOT NULL,
  `salaryCredited` decimal(10,2) NOT NULL,
  `dateCredited` date NOT NULL,
  `employeeId` int NOT NULL,
  PRIMARY KEY (`paymentRefNo`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `payroll_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
INSERT INTO `payroll` VALUES ('213wefsd33','10',2021,30000.00,'2021-11-03',12),('236842we34','10',2021,30000.00,'2021-11-02',11),('2728932312','10',2021,30000.00,'2021-11-14',13),('340903958','10',2021,30000.00,'2021-11-10',11);
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `result` (
  `studentId` int NOT NULL AUTO_INCREMENT,
  `testId` int NOT NULL,
  `marksScored` int NOT NULL,
  PRIMARY KEY (`studentId`,`testId`),
  KEY `testId` (`testId`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`testId`) REFERENCES `test` (`testId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (18,10,300);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staffId` int NOT NULL AUTO_INCREMENT,
  `employeeId` int NOT NULL,
  `designation` varchar(255) NOT NULL,
  PRIMARY KEY (`staffId`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (2,4,'reception'),(5,10,'enrollment management ');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `studentId` int NOT NULL AUTO_INCREMENT,
  `dateOfBirth` date NOT NULL,
  `houseNumber` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `schoolAttending` varchar(255) DEFAULT NULL,
  `percentage10th` int DEFAULT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`studentId`),
  KEY `userId` (`userId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (18,'2021-11-04','12','Owen Lane ','Grand Rapids, MI','Jharkhand JH','vardhana school',95,28),(21,'2021-11-05','45','4238 Irish Lane Albany','Hyderbad','Jharkhand JH','vardhana',90,41);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subjectId` varchar(255) NOT NULL,
  `subjectName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subjectId`),
  UNIQUE KEY `subjectName` (`subjectName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('AT','Apptitude','covers apptitude concepts required for various competetive exams like BITSAT'),('CHE','Chemistry','covers organic , inorganic and physical portions of chemistry '),('CHE-inorganic','inorganic part of chemistry','part of jee chemistry syllabus '),('CHE-organic','Organic Chemistry','it covers organic chemistry part of iit jee chemistry syllabus'),('CHE-physical','Physical Chemistry','Physical portion of chemistry present in jee syllabus'),('ENG','English','english portion required for BISAT and other exams '),('MA','Mathematics','this subjects covers all the portions of jee syllabus'),('PHY','Physics','covers physics portion for IIT JEE and BITSAT'),('ZLY','Zoology','complete syllabus of NEET zoology ');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacherId` int NOT NULL AUTO_INCREMENT,
  `qualification` varchar(255) NOT NULL,
  `achievement` varchar(255) DEFAULT NULL,
  `teachingExpirience` int NOT NULL,
  `employeeId` int NOT NULL,
  `subjectId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherId`),
  KEY `employeeId` (`employeeId`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `subject` (`subjectId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (4,'phd maths at MIT ','Best teacher award in School in 2020.',10,11,'MA'),(5,'phd physics at MIT ','Best teacher award in University in 2020.',8,12,'PHY'),(6,'phd in chemistry ','Acted as team coach for several interschool chemistry contests and supervised students involved in similar events',10,13,'CHE'),(7,'phd physics at MIT ','Best teacher award in School in 2020.',5,14,'ENG');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacherbatchdetails`
--

DROP TABLE IF EXISTS `teacherbatchdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `teacherbatchdetails` (
  `teacherId` int NOT NULL,
  `batchId` varchar(255) NOT NULL,
  `courseId` varchar(255) NOT NULL,
  PRIMARY KEY (`teacherId`,`batchId`,`courseId`),
  KEY `batchId` (`batchId`,`courseId`),
  CONSTRAINT `teacherbatchdetails_ibfk_1` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacherbatchdetails_ibfk_2` FOREIGN KEY (`batchId`, `courseId`) REFERENCES `batch` (`batchId`, `courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacherbatchdetails`
--

LOCK TABLES `teacherbatchdetails` WRITE;
/*!40000 ALTER TABLE `teacherbatchdetails` DISABLE KEYS */;
INSERT INTO `teacherbatchdetails` VALUES (5,'1','1'),(6,'1','1');
/*!40000 ALTER TABLE `teacherbatchdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `testId` int NOT NULL AUTO_INCREMENT,
  `testName` varchar(255) NOT NULL,
  `roomNumber` int DEFAULT NULL,
  `testDate` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `maximumMarks` int NOT NULL,
  `courseId` varchar(255) DEFAULT NULL,
  `batchId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`testId`),
  KEY `batchId` (`batchId`,`courseId`) /*!80000 INVISIBLE */,
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`batchId`, `courseId`) REFERENCES `batch` (`batchId`, `courseId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (9,'mains_test_2',2,'2021-11-18','16:23:00','18:23:00',100,NULL,NULL),(10,'Advance test 1 ',3,'2021-11-18','10:00:00','13:00:00',360,'1','1'),(12,'test1',4,'2021-11-16','09:05:00','11:05:00',100,'1','1');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transactionId` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `transactionMode` enum('Offline','Online') NOT NULL DEFAULT 'Online',
  `isSuccess` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,10000.00,'2021-11-06','01:03:31','Offline',1),(2,12000.00,'2021-11-09','11:07:45','Online',0),(3,12000.00,'2021-11-09','12:41:28','Online',0),(4,12000.00,'2021-11-09','12:44:23','Online',0),(5,12000.00,'2021-11-09','12:45:15','Online',1),(6,12000.00,'2021-11-09','12:55:45','Online',1),(7,12000.00,'2021-11-09','12:57:46','Online',1),(8,20000.00,'2021-11-14','23:49:39','Offline',1),(9,20000.00,'2021-11-15','12:49:26','Online',1),(10,20000.00,'2021-11-15','13:11:20','Online',1),(11,20000.00,'2021-11-15','21:04:28','Offline',1),(12,30000.00,'2021-11-15','21:34:24','Offline',1),(13,10000.00,'2021-11-15','21:45:07','Online',1),(14,20000.00,'2021-11-16','00:21:04','Online',1);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `gender` enum('Male','Female','Not Specified') DEFAULT 'Not Specified',
  `emailAddress` varchar(255) NOT NULL,
  `dateCreated` date NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '0',
  `isEmailVerified` tinyint(1) NOT NULL DEFAULT '0',
  `lastLoginDate` date DEFAULT NULL,
  `lastLoginTime` time DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  `role` enum('ROLE_ADMIN','ROLE_TEACHER','ROLE_STAFF','ROLE_STUDENT') NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `emailAddress` (`emailAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'admin','$2a$10$iJxBQCWDHKoRvdJzVBBV1unAldnlQkdq5S.7Qp3UxQjC4cbKXW25m','spark','academy','admin','Male','admin@gmail.com','2021-10-26',1,1,'2021-11-16','00:19:34',NULL,'ROLE_ADMIN'),(10,'teacher1','$2a$10$E0j3D0nMME3ogmGLXdSDM.OO8pYvxQpH.c/D0yxFo5xrYr8wfYDt.','fhg_fname','fgh','fgh','Male','teacher1@gmail.com','2021-11-03',1,1,'2021-11-15','13:15:33',NULL,'ROLE_TEACHER'),(13,'staff2','$2a$10$R2/pfD083vFbRTk4GaMRae6ILcJ2kZmzx0fAq31KCkkctqfrqIYxG','Ruth ','A. ','Carrico','Male','staff2@gmail.com','2021-11-04',1,0,NULL,NULL,NULL,'ROLE_STAFF'),(28,'student1','$2a$12$PTLqChTnYM6x2Twpe7PHnu8pUYafcCqHIL37y1tf0og2tOd04uajK','Santiago',' A. ','Spicer','Male','madhvreddy810@gmail.com','2021-11-11',1,1,'2021-11-15','21:21:39',NULL,'ROLE_STUDENT'),(36,'staff3','$2a$10$81cIlzEGaTWiFnDYd9OU2OefilE4ses2ynafGBkWdqRQqdEm9iwZC','Ethel ','S. ','Colvin','Female','madhava810@gmail.com','2021-11-15',1,1,'2021-11-15','21:45:53','217a3abe-3bf4-4437-ba26-7f72214c8da9','ROLE_STAFF'),(37,'teacher2','$2a$10$K8t3hiVqybBBifaW3TBTbO6slm/7PDmFB7ZgEbphKPjNTj1OYCTxi','William','T','Elkins','Male','dasari.mreddy.cd.cse19@itbhu.ac.in','2021-11-15',1,1,'2021-11-15','14:03:58',NULL,'ROLE_TEACHER'),(38,'teacher3','$2a$10$B7Hub3C0o7e66l0R3wnjSOPALJtVD2uqZbrF658JfzmnphIkvHUxy','Sarah ','J.',' Berube','Male','teacher3@gmail.com','2021-11-15',1,1,'2021-11-15','13:28:28','87208864-72cf-4bd2-9f10-6ced63f0029f','ROLE_TEACHER'),(39,'teacher4','$2a$10$CGog7EpxlbZB/UcO1KdqjuAyljM7oBglK1rKeKn3fXRVuvHnKbE9G','Russell ','J. ','Jefferson','Male','teacher4@gmail.com','2021-11-15',1,1,NULL,NULL,'b2ea653b-5a74-4cf6-9d1b-989dbf3b9bce','ROLE_TEACHER'),(40,'teacher5','$2a$10$eI8MFjstH36pE8MBUNzZiuNQ2su6LV0Dym43BjLltZExR0JF7l/DG','Thomas','','Weber','Male','madhavreddy810@gmail.com','2021-11-15',1,1,'2021-11-15','21:40:00','81a1691b-21c9-422f-84db-e42eb43e162c','ROLE_TEACHER'),(41,'student3','$2a$10$GiZNZgerXJmuoGNPMHQsYelq5KB.sT1zSY39PYmMHoXZU994DZtme','madhava','','dasari','Male','dasarimadhava810@gmail.com','2021-11-15',1,1,'2021-11-20','12:35:35','7dba56d9-3860-4145-806f-4761293920fd','ROLE_STUDENT');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userphonenumber`
--

DROP TABLE IF EXISTS `userphonenumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `userphonenumber` (
  `phoneNumber` varchar(255) NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`phoneNumber`,`userId`),
  KEY `userId` (`userId`),
  CONSTRAINT `userphonenumber_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userphonenumber`
--

LOCK TABLES `userphonenumber` WRITE;
/*!40000 ALTER TABLE `userphonenumber` DISABLE KEYS */;
INSERT INTO `userphonenumber` VALUES ('1234567123',10),('9392533379',10),('1234567890',13),('1234567890',36),('1234567891',37),('1234567895',39),('1234567890',40);
/*!40000 ALTER TABLE `userphonenumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'spark_dbms'
--

--
-- Dumping routines for database 'spark_dbms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-20 13:38:24
