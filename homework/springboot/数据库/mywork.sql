/*
 Navicat Premium Data Transfer

 Source Server         : 枫梓
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : mywork

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001

 Date: 31/12/2025 10:06:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fromUserId` bigint NULL DEFAULT NULL,
  `toUserId` bigint NULL DEFAULT NULL,
  `context` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 1, 1, '你好呀');
INSERT INTO `message` VALUES (2, 2, 0, '新年快乐！');
INSERT INTO `message` VALUES (3, 2, 0, '你好呀');
INSERT INTO `message` VALUES (4, 1, 1, '2026，你好呀');
INSERT INTO `message` VALUES (5, 3, 1, '新年快来');
INSERT INTO `message` VALUES (6, 1, 0, '大家新年快乐');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `account` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `password` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `avatar` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `email` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '枫梓', '2006173416167706624', '$2a$10$SMn7LEMeN.vaV4XhRPt5c.vg5w2/wbwB/4bnbFn7fZZyRiow3PuKa', '2003346691566211072.jpg', '3165575650@qq.com', '2025-12-31 09:19:50');
INSERT INTO `user` VALUES (2, 'bkj', '2006182537705164800', '$2a$10$PtSsfYAq3w7WYV5y9uRB5e305eecOUEp4vbIU0tkwFv287drfClHa', '2003346691566211072.jpg', '123456789@qq.com', '2025-12-31 09:56:05');
INSERT INTO `user` VALUES (3, 'fz', '2006184779489349632', '$2a$10$dLd2DRDEzwN4qTMQuE2AauLkx3MVCQgokGRuuLIH7iid9w8RpJ.y2', '2003346691566211072.jpg', 'zxcvbnm@qq.com', '2025-12-31 10:04:59');

SET FOREIGN_KEY_CHECKS = 1;
