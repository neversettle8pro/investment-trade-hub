/*
 Navicat Premium Data Transfer

 Source Server         : 123456
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : wmex

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 08/09/2022 14:54:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_product
-- ----------------------------
DROP TABLE IF EXISTS `account_product`;
CREATE TABLE `account_product`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `aid` int(0) NOT NULL,
  `pid` int(0) NOT NULL,
  `amount` int(0) NOT NULL,
  `price` decimal(10, 3) NOT NULL,
  `createtime` date NOT NULL,
  `exist` int(0) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product^account_product-pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
