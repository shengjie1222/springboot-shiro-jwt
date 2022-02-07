/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 172.20.19.73:3306
 Source Schema         : shiro_test

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 07/02/2022 15:17:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `password` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `salt` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `role_id` int(11) NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `FK_user_name`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `code` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                               `code` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for role_mid_per
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_mid_per`;
CREATE TABLE `sys_role_mid_per`  (
                                 `role_id` int(11) NOT NULL,
                                 `permission_id` int(11) NOT NULL,
                                 INDEX `role_id`(`role_id`) USING BTREE,
                                 INDEX `permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限中间表' ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;



