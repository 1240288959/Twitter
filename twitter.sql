/*
MySQL Data Transfer
Source Host: 127.0.0.1
Source Database: twitter
Target Host: 127.0.0.1
Target Database: twitter
Date: 2018/2/26 17:10:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for attention
-- ----------------------------
CREATE TABLE `attention` (
  `id` varchar(255) NOT NULL,
  `attent` varchar(255) DEFAULT NULL,
  `attented` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe5gpgemi3jgvq1j2x953k77q4` (`attent`),
  KEY `FK92byqkdvq48qckb7i8pk41iqj` (`attented`),
  CONSTRAINT `FK92byqkdvq48qckb7i8pk41iqj` FOREIGN KEY (`attented`) REFERENCES `user` (`id`),
  CONSTRAINT `FKe5gpgemi3jgvq1j2x953k77q4` FOREIGN KEY (`attent`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
CREATE TABLE `comment` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `twitter` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlxlm2octv83r6g7kkfxc9iwbi` (`user`),
  KEY `FKrdnf2og1y1o70bngpviepmdp1` (`twitter`),
  KEY `FKiv5gvib8r4qx2vunyjd1yu1i2` (`parent`),
  CONSTRAINT `FKiv5gvib8r4qx2vunyjd1yu1i2` FOREIGN KEY (`parent`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKlxlm2octv83r6g7kkfxc9iwbi` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKrdnf2og1y1o70bngpviepmdp1` FOREIGN KEY (`twitter`) REFERENCES `twitter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for praise
-- ----------------------------
CREATE TABLE `praise` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `user` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `twitter` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK66fqyg1041riak2jdmtxww8lp` (`twitter`),
  KEY `FKnl54km01okjwp34vc8g8v41ku` (`user`),
  CONSTRAINT `FK66fqyg1041riak2jdmtxww8lp` FOREIGN KEY (`twitter`) REFERENCES `twitter` (`id`),
  CONSTRAINT `FKnl54km01okjwp34vc8g8v41ku` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `praise_ibfk_2` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `praise_ibfk_3` FOREIGN KEY (`twitter`) REFERENCES `twitter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for twitter
-- ----------------------------
CREATE TABLE `twitter` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKswqwiccmd8j5qxb3mnx4cpemd` (`user`),
  CONSTRAINT `FKswqwiccmd8j5qxb3mnx4cpemd` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL COMMENT '0为女，1为男',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `UKsbnb6yc7t6tqtgt5a87ddr01d` (`name`,`mobile`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `attention` VALUES ('200c360a-5812-4470-a57d-89b501623646', '1', '177331f4-cacc-4ffc-8b0b-414fb973f1f2');
INSERT INTO `attention` VALUES ('23d8b620-d1ff-43aa-a5b8-8c94bf1adedc', '1', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `attention` VALUES ('44a5aac2-a648-482f-addd-2e1997f8717a', '5a541f51-d9e6-48d5-b015-db18e9a2a168', 'ce460b55-5012-4d8d-b6ab-4e7ab4b690dc');
INSERT INTO `attention` VALUES ('4ad18b9c-093d-4649-8718-016d88d523c3', '1', 'ce460b55-5012-4d8d-b6ab-4e7ab4b690dc');
INSERT INTO `attention` VALUES ('6bee2339-2d3d-49f0-8b9f-809725cc13d6', 'ce460b55-5012-4d8d-b6ab-4e7ab4b690dc', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `attention` VALUES ('a1dbbf53-2835-45e8-b671-63c5498465c0', '5a541f51-d9e6-48d5-b015-db18e9a2a168', '177331f4-cacc-4ffc-8b0b-414fb973f1f2');
INSERT INTO `attention` VALUES ('f8eb09ea-72f4-4328-80d3-5e69771c0c35', '5a541f51-d9e6-48d5-b015-db18e9a2a168', '1');
INSERT INTO `comment` VALUES ('50bfa839-56e5-43df-b1f0-036d098d7a5c', '3333333333333333333333333', '2018-02-13 16:17:31', '3', '5a541f51-d9e6-48d5-b015-db18e9a2a168', null);
INSERT INTO `comment` VALUES ('5b37ddc4-7160-471f-bced-a9ef94210778', 'asdfaewgta', '2018-02-26 14:45:45', 'ad2edc91-f5cf-4936-a0d6-e5c07c51d60a', '5a541f51-d9e6-48d5-b015-db18e9a2a168', null);
INSERT INTO `comment` VALUES ('64362a60-e00f-40b9-9b5d-fa0e11fc3a8f', '13514561', '2018-02-13 17:38:19', 'c5700eb4-3556-4761-bd45-6f607d30c89e', '5a541f51-d9e6-48d5-b015-db18e9a2a168', null);
INSERT INTO `comment` VALUES ('aa108905-0f03-4d38-b9d2-3023084ba3ee', 'fghfgjkhkjlhjkkl;', '2018-02-13 17:38:24', 'c5700eb4-3556-4761-bd45-6f607d30c89e', '5a541f51-d9e6-48d5-b015-db18e9a2a168', null);
INSERT INTO `comment` VALUES ('b6805831-0115-4aa8-99fa-21813221a437', 'afdasdfsdfaf', '2018-02-13 00:00:00', '3', '5a541f51-d9e6-48d5-b015-db18e9a2a168', null);
INSERT INTO `comment` VALUES ('d335dae3-cae9-4464-b3c2-9a657a4102b8', '111111111111', '2018-02-13 00:00:00', '3', '5a541f51-d9e6-48d5-b015-db18e9a2a168', null);
INSERT INTO `comment` VALUES ('d9f09e4a-d0cd-4fb5-acc9-b0e81638aae3', '2222222222222222222', '2018-02-13 16:17:21', '3', '5a541f51-d9e6-48d5-b015-db18e9a2a168', null);
INSERT INTO `praise` VALUES ('0a10db1e-15ba-4f40-b7df-25699a56f0c7', '5a541f51-d9e6-48d5-b015-db18e9a2a168', '58de1603-811c-4cc0-a5b1-dd385be5ba97');
INSERT INTO `praise` VALUES ('ab5d3ac6-36ac-48c6-93b9-fe49569b487b', '5a541f51-d9e6-48d5-b015-db18e9a2a168', 'c5700eb4-3556-4761-bd45-6f607d30c89e');
INSERT INTO `praise` VALUES ('bc301446-c14e-44d6-87ea-25b05768728c', '5a541f51-d9e6-48d5-b015-db18e9a2a168', '5a25a507-9eff-406a-a404-9d6712ae7bf9');
INSERT INTO `praise` VALUES ('c3f324ed-559b-49bb-ba59-2334a5d258df', '5a541f51-d9e6-48d5-b015-db18e9a2a168', '8a19643b-4e03-456d-8488-ad297aa3ae0c');
INSERT INTO `twitter` VALUES ('1', '今天3个1', '2018-02-06 00:00:00', '11月1日', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `twitter` VALUES ('2', '誓约胜利之剑', '2018-02-01 00:00:00', '宝具', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `twitter` VALUES ('3', 'sadasdsadsadasdasdasdjasdaskldjaslkdjsalkdjlskadjlaskdjaslkdjsalkdjasdlkjasdlkajsd', '2018-02-10 00:00:00', '不懂', '177331f4-cacc-4ffc-8b0b-414fb973f1f2');
INSERT INTO `twitter` VALUES ('4', 'affdasf', '2018-02-09 00:00:00', '11:20', '1');
INSERT INTO `twitter` VALUES ('58de1603-811c-4cc0-a5b1-dd385be5ba97', 'addfafdasfafasfdsa', '2018-02-12 00:00:00', 'afdsfaf', '1');
INSERT INTO `twitter` VALUES ('5a25a507-9eff-406a-a404-9d6712ae7bf9', 'afdwqftdgtdhg', '2018-02-26 14:38:14', 'afasfaefsdf', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `twitter` VALUES ('81621f03-3812-4d3b-b29a-6cc27feb16cd', 'asdfaefqwefasdfasdf', '2018-02-26 16:55:50', 'asdfasdfasdf', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `twitter` VALUES ('8a19643b-4e03-456d-8488-ad297aa3ae0c', 'adfetfafdsvgaqwetfrsd', '2018-02-26 14:44:20', 'fadfswerftsdf', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `twitter` VALUES ('8b2c17c1-60e6-4837-94ba-3f895b3d9f24', 'fasfwpospdjglkdasjfk', '2018-02-07 00:00:00', 'heheflsajlfgsad', 'ce460b55-5012-4d8d-b6ab-4e7ab4b690dc');
INSERT INTO `twitter` VALUES ('ad2edc91-f5cf-4936-a0d6-e5c07c51d60a', 'adfadsfdafasfaf545654651', '2018-02-13 00:00:00', 'afafadfsadf', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `twitter` VALUES ('c5700eb4-3556-4761-bd45-6f607d30c89e', 'sfaasfdffsadf', '2018-02-19 00:00:00', 'afsfafads', '1');
INSERT INTO `twitter` VALUES ('cab274f1-c909-4332-802b-027be99fb6d3', 'afsafdsfafs', '2018-02-10 00:00:00', 'afdfsa', '1');
INSERT INTO `twitter` VALUES ('dcbd8c9a-a0a8-439e-9ea1-80ce5436de0d', '46546546510320', '2018-02-13 00:00:00', 'asdfasfd4165465465', '5a541f51-d9e6-48d5-b015-db18e9a2a168');
INSERT INTO `user` VALUES ('1', 'fanhualuoyejin@gmail.com', '17854258220', 'admin', '955300', '1', '1995-06-25', '65163547_p14_master1200.jpg', '谭洋', '1');
INSERT INTO `user` VALUES ('177331f4-cacc-4ffc-8b0b-414fb973f1f2', '413449400@qq.com', '17600737859', 'moyuwei', '123456', '1', '1994-12-03', '30a2e503738da977c8c93b08b251f8198718e3e6.jpg', '莫钰玮', '1');
INSERT INTO `user` VALUES ('5a541f51-d9e6-48d5-b015-db18e9a2a168', '1240288959@qq.com', '17854561616', 'ty', '955300', '1', '2017-11-01', 'v2-fdc95ea5aaef48bbe03c3a2fdd514c54_r.jpg', 'sf', '1');
INSERT INTO `user` VALUES ('ce460b55-5012-4d8d-b6ab-4e7ab4b690dc', '1003444715@qq.com', '17624140810', 'zhaoyanbing', '12345678', '1', '1995-08-10', '46b83e26f6a87005226d8310cc7b4e3f.jpg', 'zhaoyanbing', '1');
