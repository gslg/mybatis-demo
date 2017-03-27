/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : mybatis-demo

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-03-27 19:18:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `bio` text,
  `favourite_section` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES ('101', 'jim', '********', 'jim@ibatis.apache.org', '', 'NEWS');
INSERT INTO `author` VALUES ('102', 'sally', '********', 'sally@ibatis.apache.org', null, 'VIDEOS');
INSERT INTO `author` VALUES ('104', 'lg', 'lg', 'lg@qq.com', 'zzzzzzzzzzzz', null);
INSERT INTO `author` VALUES ('105', 'lg', 'lg', 'lg@qq.com', 'zzzzzzzzzzzz', null);
INSERT INTO `author` VALUES ('106', 'lg', 'lg', 'lg@qq.com', 'zzzzzzzzzzzz', null);
INSERT INTO `author` VALUES ('107', 'ly', 'ly', 'ly@qq.com', 'kkkkkkkkkkkkkk', null);
INSERT INTO `author` VALUES ('108', 'lg', 'lg', 'lg@qq.com', 'zzzzzzzzzzzz', null);
INSERT INTO `author` VALUES ('109', 'ly', 'ly', 'ly@qq.com', 'kkkkkkkkkkkkkk', null);

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '101', 'Jim Business');
INSERT INTO `blog` VALUES ('2', '102', 'Bally Slog');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `name` mediumtext NOT NULL,
  `comment` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', 'troll', 'I disagree and think...');
INSERT INTO `comment` VALUES ('2', '1', 'anonymous', 'I agree and think troll is an...');
INSERT INTO `comment` VALUES ('3', '3', 'rider', 'I prefer motorcycles to monster trucks...');
INSERT INTO `comment` VALUES ('4', '2', 'another', 'I don not agree and still think troll is an...');

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES ('1', null);
INSERT INTO `node` VALUES ('2', '1');
INSERT INTO `node` VALUES ('3', '1');
INSERT INTO `node` VALUES ('4', '2');
INSERT INTO `node` VALUES ('5', '2');
INSERT INTO `node` VALUES ('6', '3');
INSERT INTO `node` VALUES ('7', '3');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) DEFAULT NULL,
  `author_id` int(11) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `section` varchar(25) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `body` text NOT NULL,
  `draft` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '101', '2007-12-05 00:00:00', 'NEWS', 'Corn nuts', 'I think if I never smelled another corn nut it would be too soon...', '1');
INSERT INTO `post` VALUES ('2', '1', '101', '2008-01-12 00:00:00', 'VIDEOS', 'Paul Hogan on Toy Dogs', 'That\'s not a dog.  THAT\'s a dog!', '0');
INSERT INTO `post` VALUES ('3', '2', '102', '2007-12-05 00:00:00', 'PODCASTS', 'Monster Trucks', 'I think monster trucks are great...', '1');
INSERT INTO `post` VALUES ('4', '2', '102', '2008-01-12 00:00:00', 'IMAGES', 'Tea Parties', 'A tea party is no place to hold a business meeting...', '0');

-- ----------------------------
-- Table structure for post_tag
-- ----------------------------
DROP TABLE IF EXISTS `post_tag`;
CREATE TABLE `post_tag` (
  `post_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_tag
-- ----------------------------
INSERT INTO `post_tag` VALUES ('1', '1');
INSERT INTO `post_tag` VALUES ('1', '2');
INSERT INTO `post_tag` VALUES ('1', '3');
INSERT INTO `post_tag` VALUES ('2', '1');
INSERT INTO `post_tag` VALUES ('4', '3');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'funny');
INSERT INTO `tag` VALUES ('2', 'cool');
INSERT INTO `tag` VALUES ('3', 'food');
