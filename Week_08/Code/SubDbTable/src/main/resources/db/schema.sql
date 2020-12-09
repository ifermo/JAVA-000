create schema micro_mall_0 collate utf8mb4_general_ci;

create table product
(
    product_id int auto_increment comment '商品ID'
        primary key,
    product_name varchar(32) not null comment '商品名称',
    price decimal not null comment '价格',
    quantity int default 0 null comment '剩余数量',
    picture varchar(255) null comment '商品图片',
    product_desc varchar(255) null comment '商品描述',
    shop_id int null comment '店铺ID',
    state tinyint default 1 null comment '商品状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '商品表';

create table shop
(
    shop_id int auto_increment comment '商铺ID'
        primary key,
    shop_name char(16) not null comment '商铺名称',
    shop_desc varchar(256) null comment '商铺简介',
    state tinyint default 1 null comment '商铺状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间'
)
    comment '商铺表';

create table t_order_0
(
    order_id bigint auto_increment comment '订单ID'
        primary key,
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '商品订单';

create table t_order_1
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_10
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_11
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_12
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_13
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_14
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_2
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_3
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_4
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_5
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_6
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_7
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_8
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_9
(
    order_id bigint default 0 not null comment '订单ID',
    user_id int not null comment '用户ID',
    addr_id int null comment '收件地址ID',
    total_price decimal default 0 null comment '订单价格',
    state tinyint null comment '订单状态',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
);

create table t_order_pro
(
    order_pro_id bigint auto_increment comment 'ID'
        primary key,
    order_id bigint not null comment '订单ID',
    product_id int not null comment '商品ID',
    quantity int default 0 null comment '数量',
    unit_price datetime null comment '单价（购买时商品价格）'
)
    comment '订单商品表';

create table user
(
    user_id int auto_increment comment '用户ID'
        primary key,
    username varchar(32) not null comment '用户名',
    password varchar(128) not null comment '密码（密文串）',
    phone char(16) not null comment '手机号',
    card_no char(18) null comment '身份卡号',
    email char(32) null comment '电子邮箱',
    gender char null comment '性别：M（男）、F（女）',
    age int default 0 null comment '年龄',
    avatar varchar(255) null comment '用户头像（资源地址）',
    birthday datetime null comment '出生年月',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户表';

create table user_addr
(
    addr_id int auto_increment comment '地址ID'
        primary key,
    country varchar(32) null comment '国家',
    province varchar(32) null comment '省份',
    city varchar(32) null comment '城市',
    district varchar(32) null comment '区（县）',
    address varchar(128) null comment '详细地址',
    post_code char(8) not null comment '邮编',
    user_id mediumtext null comment '用户ID',
    gmt_create datetime default CURRENT_TIMESTAMP null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_del bit default b'0' null comment '删除与否'
)
    comment '用户地址（物流）';

