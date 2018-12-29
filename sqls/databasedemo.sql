-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 172.16.7.16    Database: test_db
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `links`
--

DROP TABLE IF EXISTS `links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `links` (
  `LinkID` int(11) NOT NULL,
  `LinkName` varchar(45) DEFAULT NULL,
  `LinkURL` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`LinkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `links`
--

LOCK TABLES `links` WRITE;
/*!40000 ALTER TABLE `links` DISABLE KEYS */;
INSERT INTO `links` VALUES (1,'2','3'),(2,'3','4'),(3,'Table','Table'),(4,'TableView ','http://localhost:8080/HelloWorld/TableView.jsp?TableName=Links'),(5,'6','7'),(6,'7','8'),(10,'TableField','http://localhost:8080/HelloWorld/TableEdit.jsp?TableName=Links'),(11,'11','11'),(12,'12','12'),(13,'TableView','http://localhost:8080/HelloWorld/TableView.jsp?Table=Links'),(14,'TableView','www.baidu.com'),(15,'16','17'),(16,'16','16'),(17,'17','www.baidu.com');
/*!40000 ALTER TABLE `links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tablefields`
--

DROP TABLE IF EXISTS `tablefields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tablefields` (
  `TableName` varchar(64) NOT NULL,
  `FieldName` varchar(64) NOT NULL,
  `FieldAlias` varchar(64) DEFAULT NULL,
  `FieldSequence` int(11) DEFAULT NULL,
  `DataType` varchar(32) DEFAULT NULL,
  `DataLength` int(11) DEFAULT NULL,
  `DefaultValue` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`TableName`,`FieldName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablefields`
--

LOCK TABLES `tablefields` WRITE;
/*!40000 ALTER TABLE `tablefields` DISABLE KEYS */;
INSERT INTO `tablefields` VALUES ('customerhistoryinvest','ContractID','合同号',7,'varchar',64,NULL),('customerhistoryinvest','CustomerID','客户id',2,'int',64,NULL),('customerhistoryinvest','EndDate','合同结束日期',5,'datetime',64,NULL),('customerhistoryinvest','ID','ID',1,'int',64,NULL),('customerhistoryinvest','Interest','所获得利息',6,'int',64,NULL),('customerhistoryinvest','InvestAmount','当前投资金额',3,'int',64,NULL),('customerhistoryinvest','InvestFrom','投资渠道',8,'varchar',64,NULL),('customerhistoryinvest','StartDate','合同开始日期',4,'datetime',64,NULL),('customerhistoryinvest','TimeCreated','创建时间',10,'datetime',64,'now()'),('customerhistoryinvest','TimeUpdated','更新时间',9,'datetime',64,'now()'),('customers','Age','Age',3,'int',64,NULL),('customers','CustomerName','CustomerName',2,'varchar',64,NULL),('customers','CustomersID','CustomersID',1,'int',64,NULL),('customers','IDCode','IDCode',6,'varchar',64,NULL),('customers','InvestAmount','InvestAmount',7,'int',64,NULL),('customers','PhoneNumber','PhoneNumber',5,'int',64,NULL),('customers','PotentionInvestAmount','PotentionInvestAmount',8,'int',64,NULL),('customers','Sex','Sex',4,'varchar',64,NULL),('customers','TimeCreated','TimeCreated',10,'datetime',64,'now()'),('customers','TimeUpdated','TimeUpdated',9,'datetime',64,'now()'),('dm_new_customer','age','age',7,'int',64,NULL),('dm_new_customer','customer_id','customer_id',3,'varchar',64,NULL),('dm_new_customer','customer_key','customer_key',2,'int',64,NULL),('dm_new_customer','idcode','idcode',4,'varchar',64,NULL),('dm_new_customer','name','name',5,'link',64,NULL),('dm_new_customer','sex','sex',6,'varchar',64,NULL),('dm_new_customer','total','total',8,'decimal',64,NULL),('dm_new_customer','y_month','y_month',1,'varchar',64,NULL),('links','LinkID','链接号',1,'int',64,NULL),('links','LinkName','链接名',2,'varchar',64,NULL),('links','LinkURL','链接URL',3,'varchar',64,NULL),('tablefields','DataLength','DataLength',6,'int',64,NULL),('tablefields','DataType','DataType',5,'varchar',64,NULL),('tablefields','DefaultValue','DefaultValue',7,'varchar',64,NULL),('tablefields','FieldAlias','FieldAlias',3,'varchar',64,NULL),('tablefields','FieldName','FieldName',2,'varchar',64,NULL),('tablefields','FieldSequence','FieldSequence',4,'int',64,NULL),('tablefields','TableName','TableName',1,'varchar',64,NULL),('tables','TableAlias','TableAlias',2,'varchar',64,NULL),('tables','TableKey','TableKey',4,'varchar',64,NULL),('tables','TableName','TableName',1,'varchar',64,NULL),('tables','TableSchema','TableSchema',3,'varchar',64,NULL);
/*!40000 ALTER TABLE `tablefields` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tables` (
  `TableName` varchar(64) NOT NULL,
  `TableAlias` varchar(64) DEFAULT NULL,
  `TableSchema` varchar(32) DEFAULT NULL,
  `TableKey` varchar(512) DEFAULT NULL,
  `IfLoadODS` int(11) DEFAULT '0',
  PRIMARY KEY (`TableName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES ('admin_company','公司','guquan_zhengshi','',1),('admin_dept','部门','guquan_zhengshi','',1),('admin_menu','菜单','guquan_zhengshi','',1),('admin_operation_log','系统操作日志表','guquan_zhengshi','',1),('admin_permissions','权限表','guquan_zhengshi','',1),('admin_position','职位','guquan_zhengshi','',1),('admin_positionlv','职级','guquan_zhengshi','',1),('admin_roles','admin_roles','guquan_zhengshi',NULL,1),('admin_role_menu','角色和目录对应表','guquan_zhengshi','',1),('admin_role_permissions','角色_权限表','guquan_zhengshi','',1),('admin_role_users','用户与角色对应表','guquan_zhengshi','',1),('admin_slider','轮播图','guquan_zhengshi','',1),('admin_users','用户表','guquan_zhengshi','',1),('admin_user_permissions','权限表','guquan_zhengshi','',1),('cash','现金流表','guquan_zhengshi','',1),('cert','合格者认证表','guquan_zhengshi','',1),('cert_gr','合格者认证_个人认证表','guquan_zhengshi','',1),('cert_gz','合格者认证_风险告知书表','guquan_zhengshi','',1),('cert_jg','合格者认证_机构认证表','guquan_zhengshi','',1),('contract','签约信息','guquan_zhengshi','',1),('CustomerHistoryInvest','客户投资历史表','test_db','ID',0),('Customers','客户表','test_db','CustomersID',0),('dm_new_customer','新客户表','test_db',NULL,0),('fund','基金列表','guquan_zhengshi','',1),('fund_link','基金属性表','guquan_zhengshi','',1),('fund_link_sys','基金_基金属性关联表','guquan_zhengshi','',1),('fund_pro','基金_项目对应表','guquan_zhengshi','',1),('investor','投资客户表','guquan_zhengshi','',1),('investor_bank','投资者银行账户表','guquan_zhengshi','',1),('investor_fund_sys','投资者_基金对应表','guquan_zhengshi','',1),('Links','系统连接表','test_db',NULL,0),('pro_company','项目标的列表','guquan_zhengshi','',1),('pro_company_employee','标的公司联系人表','guquan_zhengshi','',1),('pro_cost','项目费用表','guquan_zhengshi','',1),('pro_file','项目文档-文件','guquan_zhengshi','',1),('pro_filetag','项目文件标签','guquan_zhengshi','',1),('pro_filetype_dept','项目文档-部门','guquan_zhengshi','',1),('pro_filetype_position','项目文档-职位','guquan_zhengshi','',1),('pro_filetype_positionlv','项目文档-职级','guquan_zhengshi','',1),('pro_filetype_role','项目文档-角色','guquan_zhengshi','',1),('pro_filetype_time','项目文档-时效','guquan_zhengshi','',1),('pro_filetype_user','项目文档-用户','guquan_zhengshi','',1),('pro_file_tag','项目文件-标签','guquan_zhengshi','',1),('pro_file_time','项目文件-时效','guquan_zhengshi','',1),('pro_file_type','项目文档-节点','guquan_zhengshi','',1),('pro_file_view_log','项目文件-操作日志','guquan_zhengshi','',1),('pro_hetong','项目合同表','guquan_zhengshi','',1),('pro_log','项目日志表','guquan_zhengshi','',1),('pro_pay','项目划款表','guquan_zhengshi','',1),('pro_pay_process','项目划款类别表','guquan_zhengshi','',1),('pro_process','审批流程表','guquan_zhengshi','',1),('pro_process_type','审批流程类别表','guquan_zhengshi','',1),('pro_project','项目表','guquan_zhengshi','',1),('pro_track','项目跟踪表','guquan_zhengshi','',1),('pro_user_team','用户_团队关联表','guquan_zhengshi','',1),('sys_link','行业，领域，地区二级联动表','guquan_zhengshi','',1),('TableFields','系统字段配置表','test_db',NULL,0),('Tables','系统表配置表','test_db',NULL,0);
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-29 16:16:25
