/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : score_manage

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 11/02/2025 00:50:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_file_manage
-- ----------------------------
DROP TABLE IF EXISTS `tbl_file_manage`;
CREATE TABLE `tbl_file_manage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_remove` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否移除? 0存在 1删除',
  `serial` varchar(100) NOT NULL COMMENT '唯一编号',
  `sub_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `uploader` varchar(100) DEFAULT NULL COMMENT '上传人姓名',
  `uploader_serial` varchar(100) DEFAULT NULL COMMENT '上传人编号',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_file_manage
-- ----------------------------
BEGIN;
INSERT INTO `tbl_file_manage` VALUES (1, 0, '2025021d414cb85e7945a4ba55f177333a27f5', '2025-02-02 11:42:08', NULL, '1_三队报名表(1)1.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250202114205_1_三队报名表(1)1.xlsx');
INSERT INTO `tbl_file_manage` VALUES (4, 0, '2025021c7920aa8ed24b7db924c9de322ca589', '2025-02-10 10:08:20', NULL, '123.xlsx', '王二', '202502b009627c8d0447248cc17f13626c992f', 'http://127.0.0.1:8801/files/template/20250210100818_123.xlsx');
INSERT INTO `tbl_file_manage` VALUES (5, 0, '202502a9c24fec0acf4da5be230aba004230cb', '2025-02-10 14:08:57', NULL, '1.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250210140852_1.xlsx');
INSERT INTO `tbl_file_manage` VALUES (6, 0, '2025028a79b9cbcc0142fb95decaa156dc8a35', '2025-02-10 14:19:57', NULL, '1.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250210141953_1.xlsx');
INSERT INTO `tbl_file_manage` VALUES (7, 0, '202502ecc69bc7da424c64bb96c6965e215826', '2025-02-10 14:25:21', NULL, '123.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250210142518_123.xlsx');
INSERT INTO `tbl_file_manage` VALUES (8, 0, '202502faed794425114454ac69e917d214e0de', '2025-02-10 14:30:14', NULL, '123.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250210143008_123.xlsx');
INSERT INTO `tbl_file_manage` VALUES (9, 0, '202502e5e0202ccee74c57ba8b4b7347a32b00', '2025-02-10 14:35:24', NULL, '123.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250210143520_123.xlsx');
INSERT INTO `tbl_file_manage` VALUES (10, 0, '202502f839fee3e9c0431688a179e6826e9d7e', '2025-02-10 14:44:40', NULL, '123.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250210144438_123.xlsx');
INSERT INTO `tbl_file_manage` VALUES (11, 0, '202502b7370ff2f38748a4b70e746b3ed1f9b9', '2025-02-10 15:05:12', NULL, '123.xlsx', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 'http://127.0.0.1:8801/files/template/20250210150510_123.xlsx');
COMMIT;

-- ----------------------------
-- Table structure for tbl_table_datas
-- ----------------------------
DROP TABLE IF EXISTS `tbl_table_datas`;
CREATE TABLE `tbl_table_datas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_remove` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否移除? 0存在 1删除',
  `serial` varchar(100) NOT NULL COMMENT '唯一编号',
  `sub_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `datas` text COMMENT '表格数据',
  `user_name` varchar(20) DEFAULT NULL COMMENT '填写人',
  `user_serial` varchar(100) DEFAULT NULL COMMENT '填写人',
  `cols` int(2) DEFAULT NULL COMMENT '列数',
  `rows` int(2) DEFAULT NULL COMMENT '行数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_table_datas
-- ----------------------------
BEGIN;
INSERT INTO `tbl_table_datas` VALUES (4, 0, '202502b5d65839f0e7495585fbfccf7508fca1', '2025-02-03 17:03:18', NULL, '[[\"用户\",\"v1\",\"v2\",\"v3\"],[\"a1\",\"1\",\"123\",\"0\"],[\"a2\",\"2\",\"23\",\"1\"],[\"a3\",\"3\",\"12\",\"2\"],[\"a4\",\"44\",\"23\",\"42\"]]', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 0, 0);
INSERT INTO `tbl_table_datas` VALUES (3, 0, '202502a75d3b64a3074e14b87332d40dbff223', '2025-02-03 12:10:17', NULL, '[[\"用户\",\"v1\",\"v2\"],[\"a1\",\"1\",\"3\"],[\"a2\",\"2\",\"4\"]]', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 0, 0);
INSERT INTO `tbl_table_datas` VALUES (5, 0, '202502ac10e64f67734941b2cfa6324800c928', '2025-02-07 16:41:10', NULL, '[[\"Assessor\",\"v1\",\"v2\",\"v3\"],[\"a1\",\"1\",\"3.6\",\"5\"],[\"a2\",\"2\",\"3.3\",\"1.2\"],[\"a3\",\"3.3\",\"0\",\"0\"],[\"a4\",\"0\",\"0.8\",\"0.5\"],[\"a5\",\"2.4\",\"3\",\"1\"]]', '李四', '202502ebf61955b8aa4f03bd74db885df07625', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for tbl_user_account
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_account`;
CREATE TABLE `tbl_user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_remove` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否移除? 0存在 1删除',
  `serial` varchar(100) NOT NULL COMMENT '唯一编号',
  `sub_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `city` varchar(20) DEFAULT NULL COMMENT '民族',
  `country` varchar(30) DEFAULT NULL COMMENT '国家',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `first_name` varchar(50) DEFAULT NULL COMMENT '姓',
  `last_name` varchar(50) DEFAULT NULL COMMENT '名',
  `password` text COMMENT '密码',
  `post_code` varchar(100) DEFAULT NULL COMMENT '信用卡',
  `user_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `student_id` varchar(5) DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_account
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user_account` VALUES (1, 0, '202502ebf61955b8aa4f03bd74db885df07625', '2025-02-01 11:27:47', '2025-02-10 14:04:49', 'zhangsan', '北京市朝阳区', '汉族', '中国', '1032222@qq.com', '李', '四', '123456', '991', '', '1');
INSERT INTO `tbl_user_account` VALUES (3, 0, '202502b009627c8d0447248cc17f13626c992f', '2025-02-10 10:07:38', '2025-02-10 10:08:10', 'wanger', '北京市', '北京市', '中国', '123@qq.com', '王', '二', '123456', '12312873812', '', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
