-- ----------------------------
-- create database
-- ----------------------------
CREATE DATABASE IF NOT EXISTS micro_mall DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- create user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id`      int                                                           NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名',
    `password`     varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码（密文串）',
    `phone`        char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '手机号',
    `card_no`      char(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NULL DEFAULT NULL COMMENT '身份卡号',
    `email`        char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NULL DEFAULT NULL COMMENT '电子邮箱',
    `gender`       char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL DEFAULT NULL COMMENT '性别：M（男）、F（女）',
    `age`          int                                                           NULL DEFAULT 0 COMMENT '年龄',
    `avatar`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像（资源地址）',
    `birthday`     datetime(0)                                                   NULL DEFAULT NULL COMMENT '出生年月',
    `gmt_create`   datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `gmt_modified` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- create user_addr
-- ----------------------------
DROP TABLE IF EXISTS `user_addr`;
CREATE TABLE `user_addr`
(
    `addr_id`      int                                                           NOT NULL AUTO_INCREMENT COMMENT '地址ID',
    `country`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '国家',
    `province`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '省份',
    `city`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '城市',
    `district`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '区（县）',
    `address`      varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
    `post_code`    char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL COMMENT '邮编',
    `user_id`      mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL COMMENT '用户ID',
    `gmt_create`   datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `gmt_modified` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `is_del`       bit(1)                                                        NULL DEFAULT b'0' COMMENT '删除与否',
    PRIMARY KEY (`addr_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户地址（物流）'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- create product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `product_id`   int                                                           NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `product_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '商品名称',
    `price`        decimal(10, 0)                                                NOT NULL COMMENT '价格',
    `quantity`     int                                                           NULL DEFAULT 0 COMMENT '剩余数量',
    `picture`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
    `product_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述',
    `shop_id`      int                                                           NULL DEFAULT NULL COMMENT '店铺ID',
    `state`        tinyint                                                       NULL DEFAULT 1 COMMENT '商品状态',
    `gmt_create`   datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `gmt_modified` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '商品表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- create shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`
(
    `shop_id`    int                                                           NOT NULL AUTO_INCREMENT COMMENT '商铺ID',
    `shop_name`  char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '商铺名称',
    `shop_desc`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商铺简介',
    `state`      tinyint                                                       NULL DEFAULT 1 COMMENT '商铺状态',
    `gmt_create` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    PRIMARY KEY (`shop_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '商铺表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- create order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form`
(
    `order_id`     bigint         NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `user_id`      int            NOT NULL COMMENT '用户ID',
    `addr_id`      int            NULL DEFAULT NULL COMMENT '收件地址ID',
    `total_price`  decimal(10, 0) NULL DEFAULT 0 COMMENT '订单价格',
    `state`        tinyint        NULL DEFAULT NULL COMMENT '订单状态',
    `gmt_create`   datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `gmt_modified` datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '商品订单'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- create order_pro
-- ----------------------------
DROP TABLE IF EXISTS `order_pro`;
CREATE TABLE `order_pro`
(
    `order_pro_id` bigint      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id`     bigint      NOT NULL COMMENT '订单ID',
    `product_id`   int         NOT NULL COMMENT '商品ID',
    `quantity`     int         NULL DEFAULT 0 COMMENT '数量',
    `unit_price`   datetime(0) NULL DEFAULT NULL COMMENT '单价（购买时商品价格）',
    PRIMARY KEY (`order_pro_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '订单商品表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
