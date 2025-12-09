/*
 Navicat Premium Data Transfer

 Source Server         : 枫梓
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : mybatisdemo

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001

 Date: 05/12/2025 10:44:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `petName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `species` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `option` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (1, '123', '123', '人类', 'boy', '好好学习天天向上', '好好学习天天向上', '2025-12-24 10:31:42');
INSERT INTO `pet` VALUES (2, 'fz2', '123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pet` VALUES (3, 'fz3', '123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pet` VALUES (8, '汪汪汪', '123', '中华黒犬', 'boy', '购买药物', '天天就知道汪汪汪', '2025-12-31 00:00:00');
INSERT INTO `pet` VALUES (9, '五六七', '123', '泰迪', 'boy', '打吊针', '五六七', '2025-12-31 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
