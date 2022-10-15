/*
Navicat MySQL Data Transfer

Source Server         : bs
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2022-04-10 23:14:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_cj`
-- ----------------------------
DROP TABLE IF EXISTS `t_cj`;
CREATE TABLE `t_cj` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `courseId` varchar(100) DEFAULT NULL,
  `studentId` varchar(100) DEFAULT NULL,
  `studentcj` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cj
-- ----------------------------
INSERT INTO `t_cj` VALUES ('0fb82fb6d8474005b27168cecf747800', '5ebea95498fa404fb5a0c295d8090591', '68e65d2ea4d448338c3deef4f4e627b1', '98');
INSERT INTO `t_cj` VALUES ('c4eeb26693e2482fa6e26903bb815e25', '235ea64cfb884d5c8f2ccb7e8d21c759', '68e65d2ea4d448338c3deef4f4e627b1', '98');

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `courseName` varchar(255) DEFAULT NULL,
  `state` varchar(6) DEFAULT NULL,
  `teacherId` varchar(100) DEFAULT NULL,
  `kcdescribe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('235ea64cfb884d5c8f2ccb7e8d21c759', '微信小程序', '已完成', '0e646d2b212e429ba17c9ce76f1c2620', '获取微信信息');
INSERT INTO `t_course` VALUES ('5ebea95498fa404fb5a0c295d8090591', 'JAVA', '已完成', '0e646d2b212e429ba17c9ce76f1c2620', '面向对象');

-- ----------------------------
-- Table structure for `t_coursest`
-- ----------------------------
DROP TABLE IF EXISTS `t_coursest`;
CREATE TABLE `t_coursest` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `courseId` varchar(100) DEFAULT NULL,
  `studentId` varchar(100) DEFAULT NULL,
  `state` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_coursest
-- ----------------------------
INSERT INTO `t_coursest` VALUES ('2d9f60d55248451f9e5052d9bb765358', '235ea64cfb884d5c8f2ccb7e8d21c759', '68e65d2ea4d448338c3deef4f4e627b1', '已签到');
INSERT INTO `t_coursest` VALUES ('d074554622fa4cb4a62d017032962548', '5ebea95498fa404fb5a0c295d8090591', '68e65d2ea4d448338c3deef4f4e627b1', '已签到');

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `studentId` varchar(250) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `head` varchar(250) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('0cdcff7155324f23b2f6941785c1ecad', '00002', '小明', '男', '15208406138', null, 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `t_student` VALUES ('68e65d2ea4d448338c3deef4f4e627b1', '00001', '小明', '男', '13012345678', '/155767591327790350.jpg', 'd7f4562cdb2430e494183c17abe1ba91');

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `jobNumber` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  `rank` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('0e646d2b212e429ba17c9ce76f1c2620', '00003', '王锋', '男', '教授', '13012345678', '/155767245775537522.jpg', 'e10adc3949ba59abbe56e057f20f883e');
