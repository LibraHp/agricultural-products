use agricultural;
-- 创建表：农副产品品类
CREATE TABLE productcategory
(
    id   INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- 创建表：农副产品信息
CREATE TABLE product
(
    id          INT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    category_id INT,
    price       DECIMAL(10, 2),
    description TEXT
);

-- 创建表：用户
CREATE TABLE user
(
    id       INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    address  VARCHAR(200),
    is_admin BOOLEAN DEFAULT false
);

-- 创建表：购买记录
CREATE TABLE purchase
(
    id            INT PRIMARY KEY,
    user_id       INT,
    product_id    INT,
    purchase_date DATE
);

-- 创建表：销量统计
CREATE TABLE sales
(
    product_id  INT PRIMARY KEY,
    total_sales INT
);

-- 插入示例数据：农副产品品类
INSERT INTO productcategory (id, name)
VALUES (1, '水果'),
       (2, '蔬菜'),
       (3, '畜禽产品');

-- 插入示例数据：农副产品信息
INSERT INTO product (id, name, category_id, price, description)
VALUES (1, '苹果', 1, 2.50, '新鲜红富士苹果'),
       (2, '香蕉', 1, 1.80, '香甜可口的香蕉'),
       (3, '西红柿', 2, 1.20, '新鲜番茄'),
       (4, '黄瓜', 2, 0.80, '绿色有机黄瓜'),
       (5, '鸡蛋', 3, 0.30, '农家散养鸡蛋'),
       (6, '牛肉', 3, 8.50, '优质牛肉');

-- 插入示例数据：用户
INSERT INTO user (id, username, password, email, address, is_admin)
VALUES (1, 'user1', 'password1', 'user1@example.com', '地址1', false),
       (2, 'user2', 'password2', 'user2@example.com', '地址2', false),
       (3, 'admin', 'adminpassword', 'admin@example.com', '管理员地址', true);

-- 插入示例数据：购买记录
INSERT INTO purchase (id, user_id, product_id, purchase_date)
VALUES (1, 1, 1, '2023-12-01'),
       (2, 1, 2, '2023-12-02'),
       (3, 2, 3, '2023-12-03');

-- 插入示例数据：销量统计
INSERT INTO sales (product_id, total_sales)
VALUES (1, 10),
       (2, 15),
       (3, 5);
