/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.45 : Database - dms_dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `cd_category` */

DROP TABLE IF EXISTS `cd_category`;

CREATE TABLE `cd_category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(30) DEFAULT NULL COMMENT '类别名称',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '上级类别',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品类别表';

/*Data for the table `cd_category` */

/*Table structure for table `cd_company` */

DROP TABLE IF EXISTS `cd_company`;

CREATE TABLE `cd_company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_CODE` varchar(30) DEFAULT NULL COMMENT '单位编号',
  `COMPANY_NAME` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `COMPANY_ADDRESS` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `COMPANY_LEADER` varchar(20) DEFAULT NULL COMMENT '单位联系人',
  `COMPANY_LEADER_TEL` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录签约单位、公司';

/*Data for the table `cd_company` */

/*Table structure for table `cd_contract` */

DROP TABLE IF EXISTS `cd_contract`;

CREATE TABLE `cd_contract` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CD_MEMBER_ID` int(11) DEFAULT NULL COMMENT '合同价的客户',
  `CD_COMPANY_ID` int(11) DEFAULT NULL COMMENT '合同价的单位',
  `SCOPE` int(1) DEFAULT NULL COMMENT '1-部分产品，2-全部产品',
  `CONTRACT_PRICE` decimal(10,2) DEFAULT NULL COMMENT '仅当适用范围为全部产品时适用',
  `BENGIN_TIME` datetime DEFAULT NULL COMMENT '合同开始时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '合同结束时间',
  `IS_AUDITOR` int(1) DEFAULT NULL COMMENT '0-未审核，1-审核',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同价格表';

/*Data for the table `cd_contract` */

/*Table structure for table `cd_coupon` */

DROP TABLE IF EXISTS `cd_coupon`;

CREATE TABLE `cd_coupon` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUPON_TYPE` int(1) DEFAULT NULL COMMENT '卡或者劵,1-卡，2-劵',
  `COUPON_NUMBER` varchar(50) DEFAULT NULL COMMENT '卡号',
  `COUPON_PASSWORD` varchar(20) DEFAULT NULL COMMENT '密码',
  `BEGIN_TIME` datetime DEFAULT NULL COMMENT '有效开始时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '有效结束时间',
  `PRICE` decimal(10,2) DEFAULT NULL COMMENT '固定金额',
  `COUNT` int(11) DEFAULT NULL COMMENT '固定次数',
  `IS_USED` int(1) DEFAULT NULL COMMENT '0-未使用，1-已使用',
  `IS_USEFUL` int(1) DEFAULT NULL COMMENT '0-无效，1-有效',
  `IS_FEEDBACK` int(1) DEFAULT NULL COMMENT '0-否，1-是',
  `NOTES` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡劵维护表';

/*Data for the table `cd_coupon` */

/*Table structure for table `cd_coupon_ref` */

DROP TABLE IF EXISTS `cd_coupon_ref`;

CREATE TABLE `cd_coupon_ref` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CD_COUPON_ID` int(11) DEFAULT NULL COMMENT '卡劵',
  `REF_TYPE` varchar(50) DEFAULT NULL COMMENT '拓展类型',
  `REF_NAME` varchar(30) DEFAULT NULL COMMENT '拓展描述',
  `REF_VALUE` varchar(50) DEFAULT NULL COMMENT '拓展值',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡劵拓展';

/*Data for the table `cd_coupon_ref` */

/*Table structure for table `cd_item` */

DROP TABLE IF EXISTS `cd_item`;

CREATE TABLE `cd_item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_CODE` varchar(30) DEFAULT NULL COMMENT '产品编号',
  `CD_CATEGORY_ID` int(11) DEFAULT NULL COMMENT '产品归属的类别',
  `ITEM_NAME` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `ITEM_DESCRIBE` varchar(255) DEFAULT NULL COMMENT '产品描述',
  `ITEM_TYPE` varchar(30) DEFAULT NULL COMMENT '产品类型',
  `YIELDLY` varchar(30) DEFAULT NULL COMMENT '生产地',
  `SPEC` varchar(20) DEFAULT NULL COMMENT '产品规格',
  `BASE_PRICE` decimal(10,2) DEFAULT NULL COMMENT '基础价格',
  `MEMBER_PRICE` decimal(10,2) DEFAULT NULL COMMENT '会员价格',
  `PRICING_MANNER` varchar(10) DEFAULT NULL COMMENT '计价方式',
  `SELL_STATUS` int(1) DEFAULT NULL COMMENT '0-下架 1-上架',
  `SELL_UNIT` varchar(20) DEFAULT NULL COMMENT '销售单位',
  `SELL_PRICE` decimal(10,2) DEFAULT NULL COMMENT '销售金额',
  `IS_COMBINATION` int(1) DEFAULT NULL COMMENT '0-未组合，1-组合',
  `PARENT_ITEM_ID` int(11) DEFAULT NULL COMMENT '组合产品的父id',
  `IS_AUDIT` int(1) DEFAULT NULL COMMENT '0-未审核，1-审核',
  `CREATE_DATE_TIME` datetime DEFAULT NULL COMMENT '录入时间',
  `CREATE_USER_CODE` varchar(20) DEFAULT NULL COMMENT '录入人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品基础表';

/*Data for the table `cd_item` */

/*Table structure for table `cd_item_contract_item` */

DROP TABLE IF EXISTS `cd_item_contract_item`;

CREATE TABLE `cd_item_contract_item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CD_CONTRACT_ID` int(11) DEFAULT NULL COMMENT '产品合同',
  `CD_ITEM_ID` int(11) DEFAULT NULL COMMENT '当合同适用范围为1的时候，每种产品要设定对应的合同价',
  `CONTRACT_PRICE` decimal(10,2) DEFAULT NULL COMMENT '合同价格',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品合同明细';

/*Data for the table `cd_item_contract_item` */

/*Table structure for table `cd_item_ref` */

DROP TABLE IF EXISTS `cd_item_ref`;

CREATE TABLE `cd_item_ref` (
  `CD_ITEM_REF_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REF_TYPE` varchar(50) DEFAULT NULL COMMENT '拓展类型',
  `REF_NAME` varchar(50) DEFAULT NULL COMMENT '拓展描述',
  `REF_VALUE` varchar(50) DEFAULT NULL COMMENT '拓展值',
  `CD_ITEM_ID` int(11) DEFAULT NULL COMMENT '对应产品',
  PRIMARY KEY (`CD_ITEM_REF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品拓展表\r\n如果产品有额外属性可添加在拓展表中';

/*Data for the table `cd_item_ref` */

/*Table structure for table `cd_manager_company` */

DROP TABLE IF EXISTS `cd_manager_company`;

CREATE TABLE `cd_manager_company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CD_COMPANY_ID` int(11) DEFAULT NULL COMMENT '单位',
  `CD_MANAGER_ID` int(11) DEFAULT NULL COMMENT '客户经理',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录客户经理和单位关联关系表';

/*Data for the table `cd_manager_company` */

/*Table structure for table `cd_member` */

DROP TABLE IF EXISTS `cd_member`;

CREATE TABLE `cd_member` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MEMBER_CODE` varchar(50) DEFAULT NULL COMMENT '会员编号',
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `PHONE_NUMBER` varchar(20) DEFAULT NULL COMMENT '手机号',
  `SPARE_TEL` varchar(20) DEFAULT NULL COMMENT '备用手机号1',
  `SPARE_TEL2` varchar(20) DEFAULT NULL COMMENT '备用手机号2',
  `TEL_NUMBER` varchar(20) DEFAULT NULL COMMENT '家庭固话',
  `OFFICE_TEL` varchar(20) DEFAULT NULL COMMENT '办公室电话',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `SPARE_ADDRESS` varchar(255) DEFAULT NULL COMMENT '备用地址1',
  `SPARE_ADDRESS2` varchar(255) DEFAULT NULL COMMENT '备用地址2',
  `MEMBER_TYPE` int(1) DEFAULT NULL COMMENT '1-潜在客户，2-普通客户，3-优质客户',
  `CD_COMPANY_ID` int(11) DEFAULT NULL COMMENT '所属单位',
  `CD_USER_ID` int(11) DEFAULT NULL COMMENT '所属客服经理',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户会员表基础表';

/*Data for the table `cd_member` */

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `JOB_NAME` varchar(50) NOT NULL COMMENT '任务名称',
  `JOB_GROUP` varchar(50) NOT NULL COMMENT '任务属组',
  `JOB_STATUS` tinyint(1) NOT NULL COMMENT '任务状态：0-正常运行中，1-暂停，2-已完成，3-异常，4-阻塞，-1-无',
  `JOB_DESCRIPTION` varchar(200) NOT NULL COMMENT '任务描述',
  `CRON_EXPRESSION` varchar(50) NOT NULL COMMENT 'CRON表达式',
  `TRIGGER_ID` varchar(100) NOT NULL COMMENT '触发器ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `schedule_job` */

insert  into `schedule_job`(`ID`,`JOB_NAME`,`JOB_GROUP`,`JOB_STATUS`,`JOB_DESCRIPTION`,`CRON_EXPRESSION`,`TRIGGER_ID`) values (1,'测试任务2','AMS',1,'仅供测试使用','*/5 * * * * ?','qtz2'),(4,'测试任务','AMS',1,'仅供测试使用','*/1 * * * * ?','qtz1');

/*Table structure for table `sys_dictionary` */

DROP TABLE IF EXISTS `sys_dictionary`;

CREATE TABLE `sys_dictionary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DICTIONARY_CODE` varchar(30) DEFAULT NULL COMMENT '字典编号',
  `DICTIONARY_DESC` varchar(255) DEFAULT NULL COMMENT '字典描述',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

/*Data for the table `sys_dictionary` */

/*Table structure for table `sys_dictionary_parameter` */

DROP TABLE IF EXISTS `sys_dictionary_parameter`;

CREATE TABLE `sys_dictionary_parameter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYS_DICTIONARY_ID` int(11) DEFAULT NULL COMMENT '字典',
  `SHOW_VALUE` varchar(50) DEFAULT NULL COMMENT '显示值',
  `REAL_VALUE` varchar(50) DEFAULT NULL COMMENT '实际值',
  `SORT_VALUE` int(3) DEFAULT NULL COMMENT '排位',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0-禁用，1-启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典参数表';

/*Data for the table `sys_dictionary_parameter` */

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `ACTION_NAME` varchar(50) NOT NULL COMMENT '操作名称',
  `ACTION_DATE` datetime NOT NULL COMMENT '操作时间',
  `ACTION_PARAMS` varchar(500) DEFAULT NULL COMMENT '参数',
  `CLIENT_IP` varchar(10) NOT NULL COMMENT '客户端IP',
  `ACTION_RESULT` varchar(500) DEFAULT NULL COMMENT '操作结果',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log` */

insert  into `sys_log`(`ID`,`USER_ID`,`ACTION_NAME`,`ACTION_DATE`,`ACTION_PARAMS`,`CLIENT_IP`,`ACTION_RESULT`) values (1,1,'查询数据字典组','2016-04-07 19:55:14',NULL,'127.0.0.1',NULL),(2,1,'查询数据字典组','2016-04-07 19:55:55',NULL,'127.0.0.1',NULL),(3,1,'查看权限列表','2016-04-07 19:55:57',NULL,'127.0.0.1',NULL),(4,1,'新增权限资源','2016-04-07 19:56:18',NULL,'127.0.0.1',NULL),(5,1,'查看权限列表','2016-04-07 19:56:19',NULL,'127.0.0.1',NULL),(6,1,'新增权限资源','2016-04-07 19:56:43',NULL,'127.0.0.1',NULL),(7,1,'查看权限列表','2016-04-07 19:56:44',NULL,'127.0.0.1',NULL),(8,1,'新增权限资源','2016-04-07 19:57:07',NULL,'127.0.0.1',NULL),(9,1,'查看权限列表','2016-04-07 19:57:08',NULL,'127.0.0.1',NULL),(10,1,'新增权限资源','2016-04-07 19:57:20',NULL,'127.0.0.1',NULL),(11,1,'查看权限列表','2016-04-07 19:57:20',NULL,'127.0.0.1',NULL),(12,1,'查看权限列表','2016-04-07 19:58:21',NULL,'127.0.0.1',NULL),(13,1,'查看权限列表','2016-04-07 19:58:34',NULL,'127.0.0.1',NULL),(14,1,'查询用户组','2016-04-07 19:58:35',NULL,'127.0.0.1',NULL),(15,1,'查询用户组','2016-04-07 19:58:39',NULL,'127.0.0.1',NULL),(16,1,'查询用户组','2016-04-07 19:58:58',NULL,'127.0.0.1',NULL),(17,1,'新增用户组','2016-04-07 20:00:28',NULL,'127.0.0.1',NULL),(18,1,'查询用户组','2016-04-07 20:00:28',NULL,'127.0.0.1',NULL),(19,1,'新增用户组','2016-04-07 20:00:39',NULL,'127.0.0.1',NULL),(20,1,'查询用户组','2016-04-07 20:00:40',NULL,'127.0.0.1',NULL),(21,1,'新增用户组','2016-04-07 20:00:49',NULL,'127.0.0.1',NULL),(22,1,'查询用户组','2016-04-07 20:00:49',NULL,'127.0.0.1',NULL),(23,1,'查询用户组所属权限','2016-04-07 20:00:52',NULL,'127.0.0.1',NULL),(24,1,'查询用户组所属权限','2016-04-07 20:00:52',NULL,'127.0.0.1',NULL),(25,1,'修改用户组所属权限','2016-04-07 20:00:54',NULL,'127.0.0.1',NULL),(26,1,'修改用户组所属权限','2016-04-07 20:00:55',NULL,'127.0.0.1',NULL),(27,1,'修改用户组所属权限','2016-04-07 20:00:55',NULL,'127.0.0.1',NULL),(28,1,'修改用户组所属权限','2016-04-07 20:00:56',NULL,'127.0.0.1',NULL),(29,1,'查询用户组','2016-04-07 20:01:34',NULL,'127.0.0.1',NULL),(30,1,'查询用户','2016-04-07 20:01:35',NULL,'127.0.0.1',NULL),(31,1,'查询用户组','2016-04-07 20:01:39',NULL,'127.0.0.1',NULL),(32,1,'查询用户组下属用户','2016-04-07 20:01:41',NULL,'127.0.0.1',NULL),(33,1,'查询用户组下属用户','2016-04-07 20:01:41',NULL,'127.0.0.1',NULL),(34,1,'查询用户组','2016-04-07 20:03:53',NULL,'127.0.0.1',NULL),(35,1,'查询用户组','2016-04-07 20:04:00',NULL,'127.0.0.1',NULL),(36,1,'查询用户组下属用户','2016-04-07 20:04:01',NULL,'127.0.0.1',NULL),(37,1,'查询用户组下属用户','2016-04-07 20:04:01',NULL,'127.0.0.1',NULL),(38,1,'查询用户组','2016-04-07 20:08:07',NULL,'127.0.0.1',NULL),(39,1,'修改用户组下属用户关系','2016-04-07 20:08:09',NULL,'127.0.0.1',NULL),(40,1,'修改用户组下属用户关系','2016-04-07 20:08:09',NULL,'127.0.0.1',NULL),(41,1,'查询用户组','2016-04-07 20:08:28',NULL,'127.0.0.1',NULL),(42,1,'修改用户组下属用户关系','2016-04-07 20:08:30',NULL,'127.0.0.1',NULL),(43,1,'修改用户组下属用户关系','2016-04-07 20:08:30',NULL,'127.0.0.1',NULL),(44,1,'修改用户组下属用户关系','2016-04-07 20:08:31',NULL,'127.0.0.1',NULL),(45,1,'查询用户','2016-04-07 20:08:38',NULL,'127.0.0.1',NULL),(46,1,'查看权限列表','2016-04-07 20:08:42',NULL,'127.0.0.1',NULL),(47,1,'查询用户组','2016-04-07 20:08:44',NULL,'127.0.0.1',NULL),(48,1,'查询用户组','2016-04-07 20:08:50',NULL,'127.0.0.1',NULL),(49,1,'修改用户组下属用户关系','2016-04-07 20:08:52',NULL,'127.0.0.1',NULL),(50,1,'修改用户组下属用户关系','2016-04-07 20:08:52',NULL,'127.0.0.1',NULL),(51,1,'查询用户','2016-04-07 20:08:55',NULL,'127.0.0.1',NULL),(52,1,'查看权限列表','2016-04-07 20:09:19',NULL,'127.0.0.1',NULL),(53,1,'新增权限资源','2016-04-07 20:09:52',NULL,'127.0.0.1',NULL),(54,1,'查看权限列表','2016-04-07 20:09:53',NULL,'127.0.0.1',NULL),(55,1,'新增权限资源','2016-04-07 20:10:18',NULL,'127.0.0.1',NULL),(56,1,'查看权限列表','2016-04-07 20:10:19',NULL,'127.0.0.1',NULL),(57,1,'查询用户','2016-04-07 20:10:22',NULL,'127.0.0.1',NULL),(58,1,'查询用户','2016-04-07 20:10:27',NULL,'127.0.0.1',NULL),(59,1,'查询用户组','2016-04-07 20:10:29',NULL,'127.0.0.1',NULL),(60,1,'查询用户','2016-04-07 20:10:30',NULL,'127.0.0.1',NULL),(61,1,'查询用户','2016-04-07 20:10:40',NULL,'127.0.0.1',NULL),(62,1,'查询用户组','2016-04-07 20:11:03',NULL,'127.0.0.1',NULL),(63,1,'查询用户组所属权限','2016-04-07 20:11:06',NULL,'127.0.0.1',NULL),(64,1,'查询用户组所属权限','2016-04-07 20:11:06',NULL,'127.0.0.1',NULL),(65,1,'修改用户组所属权限','2016-04-07 20:11:07',NULL,'127.0.0.1',NULL),(66,1,'修改用户组所属权限','2016-04-07 20:11:07',NULL,'127.0.0.1',NULL),(67,1,'修改用户组所属权限','2016-04-07 20:11:07',NULL,'127.0.0.1',NULL),(68,1,'修改用户组所属权限','2016-04-07 20:11:07',NULL,'127.0.0.1',NULL),(69,1,'修改用户组所属权限','2016-04-07 20:11:07',NULL,'127.0.0.1',NULL),(70,1,'查询用户','2016-04-07 20:11:10',NULL,'127.0.0.1',NULL),(71,1,'新增用户','2016-04-07 20:11:52',NULL,'127.0.0.1',NULL),(72,1,'查询用户','2016-04-07 20:11:52',NULL,'127.0.0.1',NULL),(73,1,'查看权限列表','2016-04-07 20:11:55',NULL,'127.0.0.1',NULL),(74,1,'查询用户组','2016-04-07 20:11:57',NULL,'127.0.0.1',NULL),(75,1,'修改用户组下属用户关系','2016-04-07 20:11:59',NULL,'127.0.0.1',NULL),(76,1,'修改用户组下属用户关系','2016-04-07 20:11:59',NULL,'127.0.0.1',NULL),(77,1,'修改用户组下属用户关系','2016-04-07 20:12:00',NULL,'127.0.0.1',NULL),(78,1,'修改用户组下属用户关系','2016-04-07 20:12:00',NULL,'127.0.0.1',NULL),(79,1,'修改用户组下属用户关系','2016-04-07 20:12:06',NULL,'127.0.0.1',NULL),(80,1,'修改用户组下属用户关系','2016-04-07 20:12:06',NULL,'127.0.0.1',NULL),(81,1,'修改用户组下属用户关系','2016-04-07 20:12:08',NULL,'127.0.0.1',NULL),(82,1,'修改用户组下属用户关系','2016-04-07 20:12:09',NULL,'127.0.0.1',NULL),(83,1,'查询用户组','2016-04-07 20:12:16',NULL,'127.0.0.1',NULL),(84,1,'修改用户组下属用户关系','2016-04-07 20:12:26',NULL,'127.0.0.1',NULL),(85,1,'修改用户组下属用户关系','2016-04-07 20:12:26',NULL,'127.0.0.1',NULL),(86,1,'修改用户组下属用户关系','2016-04-07 20:12:27',NULL,'127.0.0.1',NULL),(87,1,'查看权限列表','2016-04-07 20:12:43',NULL,'127.0.0.1',NULL),(88,1,'查询订单列表','2016-04-07 20:12:45',NULL,'127.0.0.1',NULL),(89,1,'查询数据字典组','2016-04-07 20:12:49',NULL,'127.0.0.1',NULL),(90,1,'查询单位公司信息','2016-04-07 20:12:50',NULL,'127.0.0.1',NULL),(91,1,'查询客户信息','2016-04-07 20:12:50',NULL,'127.0.0.1',NULL),(92,1,'查询订单列表','2016-04-07 20:12:51',NULL,'127.0.0.1',NULL),(93,1,'查询用户','2016-04-07 20:12:54',NULL,'127.0.0.1',NULL),(94,1,'查询用户组','2016-04-07 20:12:55',NULL,'127.0.0.1',NULL),(95,1,'查看权限列表','2016-04-07 20:12:55',NULL,'127.0.0.1',NULL),(96,1,'查询组织机构信息','2016-04-07 20:12:56',NULL,'127.0.0.1',NULL),(97,1,'查看权限列表','2016-04-07 20:13:03',NULL,'127.0.0.1',NULL),(98,1,'新增权限资源','2016-04-07 20:13:25',NULL,'127.0.0.1',NULL),(99,1,'查看权限列表','2016-04-07 20:13:26',NULL,'127.0.0.1',NULL),(100,1,'查询订单列表','2016-04-07 20:13:39',NULL,'127.0.0.1',NULL),(101,1,'查询组织机构信息','2016-04-07 20:18:03',NULL,'127.0.0.1',NULL),(102,1,'新增组织机构','2016-04-07 20:18:13',NULL,'127.0.0.1',NULL),(103,1,'新增组织机构','2016-04-07 20:25:39',NULL,'127.0.0.1',NULL),(104,1,'新增组织机构','2016-04-07 20:27:36',NULL,'127.0.0.1',NULL),(105,1,'新增组织机构','2016-04-07 20:30:26',NULL,'127.0.0.1',NULL),(106,1,'新增组织机构','2016-04-07 20:31:48',NULL,'127.0.0.1',NULL),(107,1,'新增组织机构','2016-04-07 20:35:29',NULL,'127.0.0.1',NULL),(108,1,'新增组织机构','2016-04-07 20:38:30',NULL,'127.0.0.1',NULL),(109,1,'新增组织机构','2016-04-07 20:39:12',NULL,'127.0.0.1',NULL),(110,1,'查询组织机构信息','2016-04-07 20:39:13',NULL,'127.0.0.1',NULL),(111,1,'删除组织机构','2016-04-07 20:39:18',NULL,'127.0.0.1',NULL),(112,1,'查询组织机构信息','2016-04-07 20:39:19',NULL,'127.0.0.1',NULL),(113,1,'查询组织机构信息','2016-04-07 20:39:36',NULL,'127.0.0.1',NULL),(114,1,'新增组织机构','2016-04-07 20:39:43',NULL,'127.0.0.1',NULL),(115,1,'查询组织机构信息','2016-04-07 20:39:44',NULL,'127.0.0.1',NULL),(116,1,'新增组织机构','2016-04-07 20:39:51',NULL,'127.0.0.1',NULL),(117,1,'查询组织机构信息','2016-04-07 20:39:52',NULL,'127.0.0.1',NULL),(118,1,'新增组织机构','2016-04-07 20:40:02',NULL,'127.0.0.1',NULL),(119,1,'查询组织机构信息','2016-04-07 20:40:02',NULL,'127.0.0.1',NULL),(120,1,'新增组织机构','2016-04-07 20:40:18',NULL,'127.0.0.1',NULL),(121,1,'查询组织机构信息','2016-04-07 20:40:18',NULL,'127.0.0.1',NULL),(122,1,'查询订单列表','2016-04-07 20:40:24',NULL,'127.0.0.1',NULL),(123,1,'查询数据字典组','2016-04-07 20:40:27',NULL,'127.0.0.1',NULL),(124,1,'查询订单列表','2016-04-07 20:40:28',NULL,'127.0.0.1',NULL);

/*Table structure for table `sys_organization` */

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '部门描述',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '上级组织',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `sys_organization` */

insert  into `sys_organization`(`ID`,`NAME`,`DESCRIPTION`,`PARENT_ID`) values (9,'CEO','CEO',NULL),(10,'CTO','CTO',9),(11,'CFO','CFO',9),(12,'研发部','研发部',10);

/*Table structure for table `sys_permissions` */

DROP TABLE IF EXISTS `sys_permissions`;

CREATE TABLE `sys_permissions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERMISSION` varchar(50) DEFAULT NULL COMMENT '权限',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '权限描述',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0-禁用，1-启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_permissions` */

insert  into `sys_permissions`(`ID`,`PERMISSION`,`DESCRIPTION`,`AVAILABLE`) values (1,'USER_VIEW','用户管理',0),(2,'USER_GROUP_VIEW','用户组管理',0),(3,'PERMISSION_VIEW','权限管理',0),(4,'ORGANIZATION_VIEW','组织管理',0),(5,'USER_GROUP_ADD','添加用户组资源',0),(6,'USER_ADD','添加用户',0),(7,'PERMISSION_ADD','新增权限',0);

/*Table structure for table `sys_roles` */

DROP TABLE IF EXISTS `sys_roles`;

CREATE TABLE `sys_roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(30) DEFAULT NULL COMMENT '角色',
  `DESCRIPTION` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0-禁用，1-启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_roles` */

insert  into `sys_roles`(`ID`,`ROLE`,`DESCRIPTION`,`AVAILABLE`) values (1,'super_admin','超级管理员',0),(2,'user','普通用户',0),(3,'admin','管理员',0);

/*Table structure for table `sys_roles_permissions` */

DROP TABLE IF EXISTS `sys_roles_permissions`;

CREATE TABLE `sys_roles_permissions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色',
  `PERMISSION_ID` int(11) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

/*Data for the table `sys_roles_permissions` */

insert  into `sys_roles_permissions`(`ID`,`ROLE_ID`,`PERMISSION_ID`) values (1,1,1),(2,1,1),(3,1,3),(4,1,4),(5,1,2),(6,1,2),(7,1,2),(8,1,5),(9,1,6);

/*Table structure for table `sys_user_organization` */

DROP TABLE IF EXISTS `sys_user_organization`;

CREATE TABLE `sys_user_organization` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORGANIZATION_ID` int(11) DEFAULT NULL COMMENT '部门',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门用户关联表';

/*Data for the table `sys_user_organization` */

/*Table structure for table `sys_users` */

DROP TABLE IF EXISTS `sys_users`;

CREATE TABLE `sys_users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(30) DEFAULT NULL COMMENT '登陆用户名',
  `REALNAME` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `SALT` varchar(50) DEFAULT NULL COMMENT '密码盐值',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '登陆密码',
  `AGE` int(2) DEFAULT NULL COMMENT '年龄',
  `SEX` int(1) DEFAULT NULL COMMENT '1-男，2女',
  `EMAIL` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `QQ` varchar(30) DEFAULT NULL COMMENT 'QQ',
  `PHONE_NUMBER` varchar(20) DEFAULT NULL COMMENT '手机号',
  `OFFICE_TEL` varchar(20) DEFAULT NULL COMMENT '办公室电话',
  `LOCKED` int(1) DEFAULT NULL COMMENT '0-禁用，1-启用',
  `IS_MANAGE` int(1) DEFAULT NULL COMMENT '1-经理',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_users` */

insert  into `sys_users`(`ID`,`USERNAME`,`REALNAME`,`SALT`,`PASSWORD`,`AGE`,`SEX`,`EMAIL`,`QQ`,`PHONE_NUMBER`,`OFFICE_TEL`,`LOCKED`,`IS_MANAGE`) values (1,'tanxinzheng',NULL,'353e60d4c113df0a7f0a62374489d000','a5c87d20433c67bc8a4d96c737e82a85',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'taojiaqi',NULL,'25caae9d49a612dfc51bab13dae0febd','6fb713a7051f31b782ace3f3dfc1a4e9',NULL,NULL,'277303310@xx.com',NULL,NULL,NULL,0,NULL);

/*Table structure for table `sys_users_roles` */

DROP TABLE IF EXISTS `sys_users_roles`;

CREATE TABLE `sys_users_roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0-禁用，1-启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `sys_users_roles` */

insert  into `sys_users_roles`(`ID`,`USER_ID`,`ROLE_ID`,`AVAILABLE`) values (1,1,1,NULL),(4,2,1,NULL);

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `ID` int(11) NOT NULL,
  `ORDER_CODE` varchar(30) DEFAULT NULL,
  `ORDER_TYPE` int(1) DEFAULT NULL COMMENT '1-卡，2-劵，3-常规',
  `COUPON_NUMBER` varchar(50) DEFAULT NULL COMMENT '卡号(订单类型为卡时记录卡号)',
  `COUPON_PASSWORD` varchar(50) DEFAULT NULL COMMENT '卡密码(订单类型为卡时记录密码)',
  `ORDER_SOURCE` int(1) DEFAULT NULL COMMENT '1-微信订单，2-商城订单，3-客服下单，4-采摘订单',
  `PAYMENT_MODE` int(1) DEFAULT NULL COMMENT '1-预付款，2-后付款，3-免费',
  `ORDER_STATUS` varchar(30) DEFAULT NULL COMMENT '订单状态',
  `CONSIGNEE_PHONE` varchar(11) DEFAULT NULL COMMENT '收货人手机',
  `CONSIGNEE_NAME` varchar(30) DEFAULT NULL COMMENT '收货人姓名',
  `CONSIGNEE_ADDRESS` varchar(200) DEFAULT NULL COMMENT '收货人地址',
  `POSTCODE` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `TRANSPORT_MODE` int(1) DEFAULT NULL COMMENT '运送方式 1-快递，2-自提',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '订单备注',
  `TOTAL_AMOUNT` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '订单创建时间',
  `CREATE_USER` varchar(30) DEFAULT NULL COMMENT '订单创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `tb_order` */

/*Table structure for table `tb_order_item` */

DROP TABLE IF EXISTS `tb_order_item`;

CREATE TABLE `tb_order_item` (
  `ID` int(11) NOT NULL,
  `TB_ORDER_ID` int(11) DEFAULT NULL COMMENT '订单ID',
  `ORDER_NO` varchar(30) DEFAULT NULL COMMENT '订单编号',
  `ITEM_CODE` varchar(30) DEFAULT NULL COMMENT '商品编码',
  `ITEM_NAME` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `ITEM_PRICE` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `ITEM_QTY` int(5) DEFAULT NULL COMMENT '商品数量',
  `ITEM_UNIT` varchar(30) DEFAULT NULL COMMENT '商品单位',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表';

/*Data for the table `tb_order_item` */

/* Function  structure for function  `query_children_organization` */

/*!50003 DROP FUNCTION IF EXISTS `query_children_organization` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `query_children_organization`(organization_id INt(11)) RETURNS varchar(4000) CHARSET utf8
BEGIN
	DECLARE s_temp VARCHAR(4000);
	DECLARE s_temp_chd VARCHAR(4000);
	SET s_temp = '$';
	SET s_temp_chd = cast(organization_id as char);
	WHILE s_temp_chd is not NULL DO
	SET s_temp = CONCAT(s_temp,',',s_temp_chd);
	SELECT group_concat(id) INTO s_temp_chd FROM sys_organization where FIND_IN_SET(parent_id,s_temp_chd)>0;
	END WHILE;
	return s_temp;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
