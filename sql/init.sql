-- 创建数据库
DROP DATABASE IF EXISTS agricultural;
CREATE DATABASE agricultural;
-- 选择数据库
USE agricultural;
-- 创建表：农副产品品类
CREATE TABLE product_category
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- 创建表：农副产品信息
CREATE TABLE product
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    category_id INT,
    price       DECIMAL(10, 2),
    description TEXT
);

-- 创建表：用户
CREATE TABLE user
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    address  VARCHAR(200),
    is_admin BOOLEAN DEFAULT false
);

-- 创建表：购买记录
CREATE TABLE purchase
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    user_id       INT,
    product_id    INT,
    purchase_date DATE
);

-- 创建表：销量统计
CREATE TABLE sales
(
    product_id  INT PRIMARY KEY AUTO_INCREMENT,
    total_sales INT
);

-- 插入示例数据：农副产品品类
INSERT INTO product_category (name)
VALUES ('水果'),
       ('蔬菜'),
       ('畜禽产品');

-- 插入示例数据：农副产品信息
INSERT INTO product (name, category_id, price, description)
VALUES ('苹果', 1, 2.50, '新鲜红富士苹果'),
       ('香蕉', 1, 1.80, '香甜可口的香蕉'),
       ('西红柿', 2, 1.20, '新鲜番茄'),
       ('黄瓜', 2, 0.80, '绿色有机黄瓜'),
       ('鸡蛋', 3, 0.30, '农家散养鸡蛋'),
       ('牛肉', 3, 8.50, '优质牛肉');

-- 插入示例数据：用户
INSERT INTO user (username, password, email, address, is_admin)
VALUES ('user1', 'password1', 'user1@example.com', '地址1', false),
       ('user2', 'password2', 'user2@example.com', '地址2', false),
       ('admin', 'adminpassword', 'admin@example.com', '管理员地址', true);

-- 插入示例数据：购买记录
INSERT INTO purchase (user_id, product_id, purchase_date)
VALUES (1, 1, '2023-12-01'),
       (1, 2, '2023-12-02'),
       (2, 3, '2023-12-03');

-- 插入示例数据：销量统计
INSERT INTO sales (total_sales)
VALUES (10),
       (15),
       (5);
