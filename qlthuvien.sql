-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: qlthuvien
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `bophan`
--

DROP TABLE IF EXISTS `bophan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bophan` (
  `idbophan` int NOT NULL AUTO_INCREMENT,
  `BoPhan` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idbophan`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bophan`
--

LOCK TABLES `bophan` WRITE;
/*!40000 ALTER TABLE `bophan` DISABLE KEYS */;
INSERT INTO `bophan` VALUES (1,'Ngoại Ngữ'),(2,'Kinh Tế'),(3,'Luật'),(4,'Xây Dựng'),(5,'CNTT');
/*!40000 ALTER TABLE `bophan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmuc` (
  `iddanhmuc` int NOT NULL AUTO_INCREMENT,
  `DanhMuc` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`iddanhmuc`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmuc`
--

LOCK TABLES `danhmuc` WRITE;
/*!40000 ALTER TABLE `danhmuc` DISABLE KEYS */;
INSERT INTO `danhmuc` VALUES (1,'Tài Liệu'),(2,'Học Liệu'),(3,'Tiểu Thuyết'),(4,'Self Help');
/*!40000 ALTER TABLE `danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docgia`
--

DROP TABLE IF EXISTS `docgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docgia` (
  `maDG` int NOT NULL AUTO_INCREMENT,
  `Ho` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Ten` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `MaDoiTuong` int NOT NULL,
  `MaBoPhan` int NOT NULL,
  `HanTheBD` date NOT NULL,
  `HanTheKT` date NOT NULL,
  `Email` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SDT` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maDG`),
  KEY `fkdt_idx` (`MaDoiTuong`),
  KEY `fk_dg_bp_idx` (`MaBoPhan`),
  CONSTRAINT `fk_dg_bp` FOREIGN KEY (`MaBoPhan`) REFERENCES `bophan` (`idbophan`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dg_dt` FOREIGN KEY (`MaDoiTuong`) REFERENCES `doituong` (`iddoituong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docgia`
--

LOCK TABLES `docgia` WRITE;
/*!40000 ALTER TABLE `docgia` DISABLE KEYS */;
INSERT INTO `docgia` VALUES (1,'Nguyễn Duy','Tân','Nam','2001-01-01',1,5,'2019-01-01','2023-01-01','duytan@gmail,com','Hồ Chí Minh','098989898'),(2,'Đào Thị','C','Nữ','1989-01-01',2,1,'2015-01-01','2030-01-01','daothic@gmail.com','Bình Dương','033215454'),(3,'Nguyễn Trọng','Nhân','Nam','2001-01-01',1,5,'2019-01-01','2023-01-01','trongnhan@gmai.com','Hồ Chí Minh','096946464'),(4,'Vũ Trọng','Thắng','Nam','2001-01-01',1,5,'2019-01-01','2023-01-01','trongthang@gmail.com','Hồ Chí Minh','089464213'),(5,'Nguyễn Duy Hải','Anh','Nam','2001-01-01',3,5,'2019-01-01','2023-01-01','haianh@gmail.com','Hà Tĩnh','098215464');
/*!40000 ALTER TABLE `docgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doituong`
--

DROP TABLE IF EXISTS `doituong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doituong` (
  `iddoituong` int NOT NULL AUTO_INCREMENT,
  `DoiTuong` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`iddoituong`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doituong`
--

LOCK TABLES `doituong` WRITE;
/*!40000 ALTER TABLE `doituong` DISABLE KEYS */;
INSERT INTO `doituong` VALUES (1,'Sinh Viên'),(2,'Giảng Viên'),(3,'Nhân Viên');
/*!40000 ALTER TABLE `doituong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muonsach`
--

DROP TABLE IF EXISTS `muonsach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muonsach` (
  `maDG` int NOT NULL,
  `idSach` int NOT NULL,
  `TinhTrang` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgayMuon` date NOT NULL,
  `HanTra` date NOT NULL,
  `NgayTra` date DEFAULT NULL,
  `SoNgay` int DEFAULT NULL,
  `TienPhat` int DEFAULT NULL,
  PRIMARY KEY (`maDG`,`idSach`,`NgayMuon`),
  KEY `fk_ms_sach_idx` (`idSach`),
  CONSTRAINT `fk_ms_dg` FOREIGN KEY (`maDG`) REFERENCES `docgia` (`maDG`),
  CONSTRAINT `fk_ms_sach` FOREIGN KEY (`idSach`) REFERENCES `sach` (`idsach`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muonsach`
--

LOCK TABLES `muonsach` WRITE;
/*!40000 ALTER TABLE `muonsach` DISABLE KEYS */;
INSERT INTO `muonsach` VALUES (1,1,'C','2022-01-01','2022-04-22','2022-04-22',82,46000),(1,2,'R','2022-01-01','2022-04-22','2022-04-22',34,9000),(2,3,'R','2022-01-01','2022-04-22','2022-04-22',25,NULL),(3,1,'R','2022-01-01','2022-04-22','2022-04-22',31,2000),(3,2,'C','2022-01-01','2022-04-22','2022-04-22',52,25000),(3,4,'C','2022-01-01','2022-04-22','2022-04-22',20,NULL),(4,2,'R','2022-01-01','2022-04-22','2022-04-22',25,NULL),(4,3,'C','2022-01-01','2022-04-22','2022-04-22',40,30000),(5,3,'C','2022-01-01','2022-04-22','2022-04-22',51,14000);
/*!40000 ALTER TABLE `muonsach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `idNV` int NOT NULL,
  `HoVaTen` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `Password` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Duy Tân','1'),(2,'Trọng Nhân','2'),(3,'Trọng Thắng','3'),(4,'Hải Anh','4');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sach` (
  `idsach` int NOT NULL,
  `TenSach` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Mota` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `NamXuatBan` int DEFAULT NULL,
  `NoiXuatBan` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaDanhMuc` int NOT NULL,
  `MaViTri` int NOT NULL,
  `SoLuong` int NOT NULL,
  PRIMARY KEY (`idsach`),
  KEY `fk_sach_dm_idx` (`MaDanhMuc`),
  KEY `fk_sach_vitri_idx` (`MaViTri`),
  KEY `fk_sach_nhapsach_idx` (`idsach`,`SoLuong`),
  CONSTRAINT `fk_sach_dm` FOREIGN KEY (`MaDanhMuc`) REFERENCES `danhmuc` (`iddanhmuc`),
  CONSTRAINT `fk_sach_vitri` FOREIGN KEY (`MaViTri`) REFERENCES `vitri` (`idvitri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES (1,'Kiểm Thử Phần Mềm','testing',2015,'ĐH Mở HCM',2,1,34),(2,'Trắc Nghiệm TOPIC','thi bằng topic',2019,'NXB Giáo Dục',1,3,12),(3,'Đắc Nhân Tâm','self help',2012,'NXB Kim Đồng',4,2,22),(4,'Triết học Mác-Lênin','triết',2015,'NXB Giáo Dục',2,1,28),(5,'10 Vạn Câu hỏi vì sao','khoa học',2013,'NXB Chị Ong Vàng',1,3,10),(6,'Nhà Giả Kim','kinh doanh',2016,'NXB Quốc Tế',3,1,4);
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tacgia`
--

DROP TABLE IF EXISTS `tacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tacgia` (
  `idSach` int NOT NULL,
  `tentg` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idSach`,`tentg`),
  CONSTRAINT `fk_tg_sach` FOREIGN KEY (`idSach`) REFERENCES `sach` (`idsach`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tacgia`
--

LOCK TABLES `tacgia` WRITE;
/*!40000 ALTER TABLE `tacgia` DISABLE KEYS */;
INSERT INTO `tacgia` VALUES (1,'Dương T'),(2,'Trần A'),(3,'Nguyễn C'),(4,'Đào B'),(5,'Trọng N'),(6,'Paulo Coe');
/*!40000 ALTER TABLE `tacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vitri`
--

DROP TABLE IF EXISTS `vitri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vitri` (
  `idvitri` int NOT NULL AUTO_INCREMENT,
  `TenKe` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaKhu` int NOT NULL,
  PRIMARY KEY (`idvitri`),
  KEY `fk_vitri_dm_idx` (`MaKhu`),
  CONSTRAINT `fk_vitri_dm` FOREIGN KEY (`MaKhu`) REFERENCES `danhmuc` (`iddanhmuc`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vitri`
--

LOCK TABLES `vitri` WRITE;
/*!40000 ALTER TABLE `vitri` DISABLE KEYS */;
INSERT INTO `vitri` VALUES (1,'a',2),(2,'b',1),(3,'c',3),(4,'d',3),(5,'e',2);
/*!40000 ALTER TABLE `vitri` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-22  9:16:52
