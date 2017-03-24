/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-03-24 19:57:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(20) NOT NULL,
  `studentAge` int(11) NOT NULL,
  `studentPhone` varchar(20) NOT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'Jack', '20', '000000');
INSERT INTO `student` VALUES ('2', 'Mark', '21', '111111');
INSERT INTO `student` VALUES ('3', 'Lily', '22', '222222');
INSERT INTO `student` VALUES ('4', 'Lucy', '23', '333333');
