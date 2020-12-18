CREATE DATABASE funds;

USE funds;

create table m_dollar
(
    md_id   int auto_increment comment '主键'
        primary key,
    user_id int            not null comment '用户ID',
    balance decimal(10, 2) null comment '账户余额'
)
    comment '美元账户' collate = utf8mb4_general_ci;

create table m_freeze
(
    id     int auto_increment comment 'ID'
        primary key,
    dollar decimal(10, 2)    null comment '美元',
    rmb    decimal(10, 2)    null comment '人民币',
    status tinyint default 0 null
)
    comment '资产冻结表' collate = utf8mb4_general_ci;

create table m_rmb
(
    mr_id   int auto_increment comment '主键'
        primary key,
    user_id int            not null comment '用户ID',
    balance decimal(10, 2) null comment '账户余额'
)
    comment '人民币账户' collate = utf8mb4_general_ci;

create table user
(
    user_id  int         not null comment '用户ID'
        primary key,
    username varchar(32) null comment '用户名',
    gender   char        null comment '性别：F(女)，M(男)'
)
    comment '用户表' collate = utf8mb4_general_ci;

