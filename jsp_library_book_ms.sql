/*
Navicat MySQL Data Transfer

Source Server         : 127.0.01
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : jsp_library_book_ms

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-17 13:35:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_info`
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `bookid` int(12) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(50) NOT NULL,
  `type` varchar(10) NOT NULL,
  `isbn` varchar(30) NOT NULL,
  `author` varchar(20) NOT NULL,
  `press` varchar(20) NOT NULL,
  `pubtime` datetime NOT NULL,
  `allquantity` int(10) NOT NULL,
  `aviquantity` int(10) NOT NULL,
  `imgpath` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=22283 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('11111', '数据库', '计算机', '123456', '文凯', '机械工业出版社', '2015-06-09 00:00:00', '4', '3', 'OT20160222100159068.jpg');
INSERT INTO `book_info` VALUES ('22222', '大数据', '计算机', '123123', '大卫', '机械工业出版社', '2018-02-07 00:00:00', '3', '1', 'OT20160222100159134.jpg');
INSERT INTO `book_info` VALUES ('22223', '算法导论（第2版）', '计算机', '21212', '易大义，沈云宝，李有法 编', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159139.jpg');
INSERT INTO `book_info` VALUES ('22224', '代码大全（第2版）', '计算机', '23124234', '李桂成编著', '北京大学出版社', '2003-07-01 00:00:00', '3', '2', 'OT20160222100159155.jpg');
INSERT INTO `book_info` VALUES ('22225', 'C++ Primer中文版（第4版）', '计算机', '234234', '何满喜，曹飞龙', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '3', 'OT20160222100159157.jpg');
INSERT INTO `book_info` VALUES ('22226', '设计模式：可复用面向对象软件的基础', '计算机', '23435456', '钱焕延编著', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159171.jpg');
INSERT INTO `book_info` VALUES ('22227', '浪潮之巅', '计算机', '57686', '李铭明 ，江开忠 编 ', '电子工业出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159197.jpg');
INSERT INTO `book_info` VALUES ('22228', 'Java编程思想（第4版）', '计算机', '79879', '吴筑筑 主编，于江明 副主编 ', '机械工业出版社', '2018-02-07 00:00:00', '3', '3', 'OT20160222100159334.jpg');
INSERT INTO `book_info` VALUES ('22229', 'Java核心技术 卷1：基础知识', '计算机', '78978', '黄云清 等', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159429.jpg');
INSERT INTO `book_info` VALUES ('22230', 'Java核心技术 卷2：高级特性', '计算机', '9789', '康兆敏，方秀男　编著', '北京大学出版社', '2003-07-01 00:00:00', '3', '3', 'OT20160222100159449.jpg');
INSERT INTO `book_info` VALUES ('22231', '人月神话', '计算机', '345345345', '大卫', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '3', 'OT20160222100159486.jpg');
INSERT INTO `book_info` VALUES ('22232', 'Linux内核编程', '计算机', '34534', '易大义，沈云宝，李有法 编', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159704.jpg');
INSERT INTO `book_info` VALUES ('22233', 'C程序设计语言（第2版新版）', '计算机', '534547', '李桂成编著', '电子工业出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159983.jpg');
INSERT INTO `book_info` VALUES ('22234', '编程之美：微软技术面试心得', '计算机', '76978', '钱焕延编著', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160417102333653.jpg');
INSERT INTO `book_info` VALUES ('22235', '代码之美', '计算机', '9780', '\r\n李铭明 ，江开忠 编 ', '北京大学出版社', '2003-07-01 00:00:00', '3', '3', 'OT20160417102333278.jpg');
INSERT INTO `book_info` VALUES ('22236', '软件随想录：程序员部落酋长Joel谈软件', '计算机', '978978', '吴筑筑 主编，于江明 副主编 ', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '3', 'OT20160222100159775.jpg');
INSERT INTO `book_info` VALUES ('22237', '架构之美', '计算机', '9-34523', '黄云清 等', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159068.jpg');
INSERT INTO `book_info` VALUES ('22238', '国外计算机科学经典教材：Unix & Linux大学教程', '计算机', '90-786', '康兆敏，方秀男　编著', '电子工业出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159134.jpg');
INSERT INTO `book_info` VALUES ('22239', '深入理解计算机系统（原书第2版）', '计算机', '31243242', '大卫', '机械工业出版社', '2018-02-07 10:49:09', '3', '3', 'OT20160222100159139.jpg');
INSERT INTO `book_info` VALUES ('22240', 'UNIX网络编程 卷1：套接字联网API', '计算机', '34234', '易大义，沈云宝，李有法 编', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159155.jpg');
INSERT INTO `book_info` VALUES ('22241', 'UNIX网络编程 卷2：进程间通信', '计算机', '57686', '李桂成编著', '北京大学出版社', '2003-07-01 00:00:00', '3', '3', 'OT20160222100159157.jpg');
INSERT INTO `book_info` VALUES ('22242', '黑客与画家：硅谷创业之父Paul Graham文集', '计算机', '657658', '何满喜，曹飞龙', '机械工业出版社', '2018-02-07 10:49:09', '3', '3', 'OT20160222100159986.jpg');
INSERT INTO `book_info` VALUES ('22243', '软件架构的艺术', '计算机', '78978', '钱焕延编著', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159197.jpg');
INSERT INTO `book_info` VALUES ('22244', 'Effective C++中文版', '计算机', '9789', '\r\n李铭明 ，江开忠 编 ', '电子工业出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159334.jpg');
INSERT INTO `book_info` VALUES ('22245', 'Effective Java中文版（第2版）', '计算机', '345345345', '吴筑筑 主编，于江明 副主编 ', '机械工业出版社', '2018-02-07 10:49:09', '3', '3', 'OT20160222100159429.jpg');
INSERT INTO `book_info` VALUES ('22246', 'PHP经典实例（第2版）', '计算机', '534547', '康兆敏，方秀男　编著', '北京大学出版社', '2003-07-01 00:00:00', '3', '3', 'OT20160222100159486.jpg');
INSERT INTO `book_info` VALUES ('22247', 'C++ 编程思想 第1卷', '计算机', '657658', '大卫', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '3', 'OT20160222100159704.jpg');
INSERT INTO `book_info` VALUES ('22248', 'C++ 编程思想 第2卷   两卷合订本', '计算机', '76978', '易大义，沈云宝，李有法 编', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159983.jpg');
INSERT INTO `book_info` VALUES ('22249', 'PHP & MySQL Web数据库应用开发指南（第2版）', '计算机', '34534', '黄云清 等', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159449.jpg');
INSERT INTO `book_info` VALUES ('22250', '自动机理论、语言和计算导论', '计算机', '79879', '何满喜，曹飞龙', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '3', 'OT20160222100159171.jpg');
INSERT INTO `book_info` VALUES ('22251', 'Linux内核设计的艺术：图解Linux操作系统架构设计与实现原理', '计算机', '9780', '李桂成编著', '电子工业出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159986.jpg');
INSERT INTO `book_info` VALUES ('22252', 'Python参考手册（第4版）', '计算机', '9-34523', '钱焕延编著', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159068.jpg');
INSERT INTO `book_info` VALUES ('22253', '数据库系统导论（原书第8版）', '计算机', '978978', '何满喜，曹飞龙', '机械工业出版社', '2018-02-07 10:49:09', '3', '3', 'OT20160417102333653.jpg');
INSERT INTO `book_info` VALUES ('22254', '提高C++性能的编程技术', '计算机', '31243242', '吴筑筑 主编，于江明 副主编 ', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '3', 'OT20160222100159139.jpg');
INSERT INTO `book_info` VALUES ('22255', '从网管员到CTO：网络设备配置与管理实战详解', '计算机', '34234', '黄云清 等', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159155.jpg');
INSERT INTO `book_info` VALUES ('22256', 'Python灰帽子', '计算机', '90-786', '\r\n李铭明 ，江开忠 编 ', '北京大学出版社', '2003-07-01 00:00:00', '3', '3', 'OT20160222100159134.jpg');
INSERT INTO `book_info` VALUES ('22257', '深入理解计算机系统（修订版）', '计算机', '57686', '康兆敏，方秀男　编著', '电子工业出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159157.jpg');
INSERT INTO `book_info` VALUES ('22258', 'UNIX编程艺术', '计算机', '79879', '大卫', '机械工业出版社', '2018-02-07 10:49:09', '3', '3', 'OT20160222100159171.jpg');
INSERT INTO `book_info` VALUES ('22259', 'Microsoft.NET框架程序设计', '计算机', '9789', '李桂成编著', '北京大学出版社', '2003-07-01 00:00:00', '3', '3', 'OT20160222100159334.jpg');
INSERT INTO `book_info` VALUES ('22260', '代码整洁之道', '计算机', '345345345', '何满喜，曹飞龙', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '2', 'OT20160222100159429.jpg');
INSERT INTO `book_info` VALUES ('22261', '编程珠玑（第2版）、编程珠玑（续）', '计算机', '34534', '钱焕延编著', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159449.jpg');
INSERT INTO `book_info` VALUES ('22262', '大话设计模式', '计算机', '534547', '\r\n李铭明 ，江开忠 编 ', '电子工业出版社', '2013-08-01 00:00:00', '3', '2', 'OT20160222100159486.jpg');
INSERT INTO `book_info` VALUES ('22263', 'C#开发宝典', '计算机', '657658', '吴筑筑 主编，于江明 副主编 ', '机械工业出版社', '2018-02-07 10:49:09', '3', '3', 'OT20160222100159704.jpg');
INSERT INTO `book_info` VALUES ('22264', '深入理解Linux内核（第3版）', '计算机', '76978', '黄云清 等', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159983.jpg');
INSERT INTO `book_info` VALUES ('22265', 'UNIX环境高级编程 （第2版）', '计算机', '9780', '康兆敏，方秀男　编著', '北京大学出版社', '2003-07-01 00:00:00', '3', '3', 'OT20160222100159068.jpg');
INSERT INTO `book_info` VALUES ('22266', 'WCF服务编程：.NET开发者决战SOA的制胜利剑（第3版）', '计算机', '978978', '大卫', '北京理工大学出版社', '2011-04-01 00:00:00', '3', '3', 'OT20160222100159134.jpg');
INSERT INTO `book_info` VALUES ('22267', '现代编译原理:C语言描述 （虎书）', '计算机', '9-34523', '易大义，沈云宝，李有法 编', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159139.jpg');
INSERT INTO `book_info` VALUES ('22268', '高级编译器设计与实现 （鲸书）', '计算机', '90-786', '李桂成编著', '电子工业出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159155.jpg');
INSERT INTO `book_info` VALUES ('22269', '编译原理（第2版）（龙书）', '计算机', '31243242', '何满喜，曹飞龙', '机械工业出版社', '2018-02-07 10:49:09', '3', '3', 'OT20160222100159157.jpg');
INSERT INTO `book_info` VALUES ('22270', 'Windows核心编程 （第5版）', '计算机', '34234', '钱焕延编著', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159171.jpg');
INSERT INTO `book_info` VALUES ('22271', 'C++标准程序库：自修教程与参考手册', '计算机', '978978', '李铭明 ，江开忠 编 ', '北京大学出版社', '2003-07-01 00:00:00', '3', '2', 'OT20160222100159197.jpg');
INSERT INTO `book_info` VALUES ('22272', '软件框架设计的艺术', '计算机', '90-786', '黄云清 等', '科学出版社', '2015-12-01 00:00:00', '3', '3', 'OT20160222100159429.jpg');
INSERT INTO `book_info` VALUES ('22273', '设计原本：计算机科学巨匠Frederick P.Brooks的思考', '计算机', '9-34523', '吴筑筑 主编，于江明 副主编 ', '北京理工大学出版社', '2011-04-01 00:00:00', '2', '2', 'OT20160222100159334.jpg');
INSERT INTO `book_info` VALUES ('22274', '深入理解Java虚拟机:JVM高级特性与最佳实践', '计算机', '78978', '易大义，沈云宝，李有法 编', '清华大学出版社', '2013-08-01 00:00:00', '3', '3', 'OT20160222100159197.jpg');
INSERT INTO `book_info` VALUES ('22275', '蘑菇大全', '生活', '214342', '哈哈', '被打出版色', '2018-04-03 00:00:00', '3', '3', '1.jpg');
INSERT INTO `book_info` VALUES ('22276', 'Java编程思想', 'IT', '2436274368', 'ethids', '机械工业出版社', '2015-04-01 00:00:00', '2', '2', 'java.jpg');
INSERT INTO `book_info` VALUES ('22277', 'Java核心技术（卷一）', '计算机', '1231231', 'thsd', '机械工业出版社', '2018-04-03 00:00:00', '2', '2', 'javahx.jpg');
INSERT INTO `book_info` VALUES ('22279', 'mogu', '123', '123', '123', '123', '2018-04-11 00:00:00', '4', '4', '1.jpg');
INSERT INTO `book_info` VALUES ('22280', '11', '33', '55', '22', '44', '2020-01-17 00:00:00', '2', '2', 'kj021320.jpg.jsp');
INSERT INTO `book_info` VALUES ('22281', '11', '33', '55', '22', '44', '2020-01-17 00:00:00', '2', '2', 'kj021320.jpg.jsp');
INSERT INTO `book_info` VALUES ('22282', '1', '3', '5', '2', '4', '2020-01-14 00:00:00', '1', '1', 'kj021320.jsp');

-- ----------------------------
-- Table structure for `borrow_info`
-- ----------------------------
DROP TABLE IF EXISTS `borrow_info`;
CREATE TABLE `borrow_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(10) NOT NULL,
  `bookid` int(12) NOT NULL,
  `bortime` datetime NOT NULL,
  `rettime` datetime NOT NULL,
  `reltime` datetime DEFAULT NULL,
  `overfine` float(6,0) DEFAULT NULL,
  PRIMARY KEY (`id`,`bookid`,`userid`),
  KEY `useridfk` (`userid`),
  CONSTRAINT `useridfk` FOREIGN KEY (`userid`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=668 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_info
-- ----------------------------
INSERT INTO `borrow_info` VALUES ('665', '141402103', '11111', '2019-12-21 00:00:00', '2020-02-19 00:00:00', null, null);
INSERT INTO `borrow_info` VALUES ('666', '141402103', '22222', '2019-12-21 00:00:00', '2020-01-05 00:00:00', null, null);
INSERT INTO `borrow_info` VALUES ('667', '141402103', '22227', '2019-12-21 00:00:00', '2020-02-19 00:00:00', '2019-12-21 20:21:23', '0');

-- ----------------------------
-- Table structure for `sys_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_info`;
CREATE TABLE `sys_info` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_info
-- ----------------------------
INSERT INTO `sys_info` VALUES ('9527', 'admin', 'admin');
INSERT INTO `sys_info` VALUES ('9528', '郭圣', '1');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(10) NOT NULL,
  `name` varchar(12) NOT NULL,
  `college` varchar(20) NOT NULL,
  `major` varchar(20) NOT NULL,
  `classes` varchar(20) NOT NULL,
  `imgpath` varchar(50) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('141402103', '张晓', '信息科学与工程学院', '软件工程', '软件工程1502', '1464940721982.png', '123456');
INSERT INTO `user_info` VALUES ('3141906201', '王秋甘', '信息科学与工程学院', '软件工程', '软件工程1502', '3151906101.jpg', '3141906201');
INSERT INTO `user_info` VALUES ('3141906207', '李松遥', '机械科学与工程学院', '机械工程及其自动化', '机械工程及其自动化1501', '3151906203.jpg', '3141906207');
INSERT INTO `user_info` VALUES ('3141906214', '吴洛铭', '机械科学与工程学院', '机械工程及其自动化', '机械工程及其自动化1501', '3151906212.jpg', '3141906214');
INSERT INTO `user_info` VALUES ('3150907110', '彭靖凌', '机械科学与工程学院', '机械工程及其自动化', '机械工程及其自动化1501', '3151906101.jpg', '3150907110');
INSERT INTO `user_info` VALUES ('3151906218', '汤佳宏', '信息科学与工程学院', '软件工程', '软件工程1502', '3151906212.jpg', '3151906218');
INSERT INTO `user_info` VALUES ('3151906219', '伍惠剑', '信息科学与工程学院', '软件工程', '软件工程1502', '3151906101.jpg', '3151906219');
INSERT INTO `user_info` VALUES ('3151906222', '陆滨', '信息科学与工程学院', '物联网工程', '物联网工程1501', '1464940721982.png', '3151906222');
INSERT INTO `user_info` VALUES ('3151906223', '迟进涛', '信息科学与工程学院', '物联网工程', '物联网工程1502', '1464940721982.png', '3151906223');
