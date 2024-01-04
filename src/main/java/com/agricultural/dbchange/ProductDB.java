package com.agricultural.dbchange;

import com.agricultural.bean.Product;
import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;

public class ProductDB {
    /**
     * 新增产品信息
     *
     * @param product 产品对象
     * @return int 查询影响的行数
     */
    public static int addProduct(Product product) {
        String sql = "INSERT INTO product (name, category_id, price, description) VALUES (?, ?, ?, ?)";
        SalesDB.initSales(product.getId());
        return DatabaseUtil.executeUpdate(sql, product.getName(), product.getCategoryId(), product.getPrice(), product.getDescription());
    }
    /**
     * 删除产品信息
     *
     * @param id 产品id
     * @return int 查询影响的行数
     */
    public static int deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        SalesDB.deleteSales(id);
        return DatabaseUtil.executeUpdate(sql, id);
    }

    /**
     * 修改产品信息
     *
     * @param product 产品对象
     * @return int 查询影响的行数
     */
    public static int updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, category_id = ?, price = ?, description = ? WHERE id = ?";
        return DatabaseUtil.executeUpdate(sql, product.getName(), product.getCategoryId(), product.getPrice(), product.getDescription(), product.getId());
    }

    /**
     * 查询产品信息
     *
     * @param id 产品id
     * @return ResultSet 查询结果，包含产品信息、分类信息等多个表
     */
    public static ResultSet getProduct(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return DatabaseUtil.executeQuery(sql, id);
    }

    /**
     * 查询产品列表
     *
     * @return ResultSet 查询结果
     */
    public static ResultSet getProductList() {
        String sql = "SELECT * FROM product";
        return DatabaseUtil.executeQuery(sql);
    }

    /**
     * 通过用户id和产品id购买商品，添加商品记录
     * @param userId 用户id
     * @param productId 产品id
     * @return int 影响的行数
     */
    public static int buyProduct(int userId,int productId) {
        String sql = "INSERT INTO purchase (user_id, product_id, purchase_date) VALUES (?, ?, CURRENT_TIMESTAMP)";
        upSale(productId);
        return DatabaseUtil.executeUpdate(sql, userId, productId);
    }

    /**
     * 传入产品id使产品销量+1
     * @param productId 产品id
     */
    public static void upSale(int productId){
        String sql = "UPDATE sales SET total_sales = total_sales + 1 WHERE product_id = ?";
        DatabaseUtil.executeUpdate(sql,productId);
    }

    /**
     * 查询某个用户的购买记录
     * @param userId 用户id
     * @return ResultSet 查询结果，包含用户id，购买日期，产品id，产品name
     */
    public static ResultSet getPurchaseListByUserId(int userId) {
        String sql = "SELECT p.id, p.purchase_date, pr.name, pr.price FROM purchase p JOIN product pr ON p.product_id = pr.id WHERE p.user_id = ?";
        return DatabaseUtil.executeQuery(sql, userId);
    }

    /**
     * 通过分类查id询产品
     * @param categoryId 分类id
     * @return ResultSet 查询结果，包含产品id，产品name
     */
    public static ResultSet searchByCategory(int categoryId){
        String sql = "SELECT * FROM product WHERE category_id = ?";
        return DatabaseUtil.executeQuery(sql,categoryId);
    }

    /**
     * 通过名称查询产品
     * @param name 需要查询的名称
     * @return ResultSet 查询结果，包含产品id，产品name
     */
    public static ResultSet searchByName(String name){
        name = "%" + name + "%";
        String sql = "SELECT * FROM product WHERE name LIKE ?";
        return DatabaseUtil.executeQuery(sql,name);
    }

    /**
     * 通过价格查询产品，查询低于传入价格的产品
     * @param price 产品价格
     * @return ResultSet 查询结果，包含产品id，产品name
     */
    public static ResultSet searchByPrice(double price){
        String sql = "SELECT * FROM product WHERE price <= ?";
        return DatabaseUtil.executeQuery(sql,price);
    }
}
