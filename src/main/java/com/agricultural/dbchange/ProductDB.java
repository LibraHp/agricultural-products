package com.agricultural.dbchange;

import com.agricultural.bean.Product;
import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;

public class ProductDB {
    /**
     * 新增产品信息
     *
     * @param product
     * @return
     */
    public static int addProduct(Product product) {
        String sql = "INSERT INTO product (name, category_id, price, description) VALUES (?, ?, ?, ?)";
        return DatabaseUtil.executeUpdate(sql, product.getName(), product.getCategoryId(), product.getPrice(), product.getDescription());
    }

    /**
     * 删除产品信息
     *
     * @param id
     * @return
     */
    public static int deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        return DatabaseUtil.executeUpdate(sql, id);
    }

    /**
     * 修改产品信息
     *
     * @param product
     * @return
     */
    public static int updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, category_id = ?, price = ?, description = ? WHERE id = ?";
        return DatabaseUtil.executeUpdate(sql, product.getName(), product.getCategoryId(), product.getPrice(), product.getDescription(), product.getId());
    }

    /**
     * 查询产品信息
     *
     * @param id
     * @return
     */
    public static ResultSet getProduct(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return DatabaseUtil.executeQuery(sql, id);
    }

    /**
     * 查询产品列表
     *
     * @return
     */
    public static ResultSet getProductList() {
        String sql = "SELECT * FROM product";
        return DatabaseUtil.executeQuery(sql);
    }

    /**
     * 根据分类id获取某个分类的产品
     *
     * @param categoryId
     * @return
     */
    public static ResultSet getProductListByCategoryId(int categoryId) {
        String sql = "SELECT * FROM product WHERE category_id = ?";
        return DatabaseUtil.executeQuery(sql, categoryId);
    }

    /**
     * 购买商品，添加商品记录
     * @param userId
     * @param productId
     * @return
     */
    public static int buyProduct(int userId,int productId) {
        String sql = "INSERT INTO purchase (user_id, product_id, purchase_date) VALUES (?, ?, CURRENT_TIMESTAMP)";
        upSale(productId);
        return DatabaseUtil.executeUpdate(sql, userId, productId);
    }
    public static void upSale(int productId){
        String sql = "UPDATE sales SET total_sales = total_sales + 1 WHERE product_id = ?";
        DatabaseUtil.executeUpdate(sql,productId);
    }

    /**
     * 查询某个用户的购买记录
     * @param userId
     * @return
     */
    public static ResultSet getPurchaseListByUserId(int userId) {
        String sql = "SELECT p.id, p.purchase_date, pr.name, pr.price FROM purchase p JOIN product pr ON p.product_id = pr.id WHERE p.user_id = ?";
        return DatabaseUtil.executeQuery(sql, userId);
    }

    /**
     * 通过分类查询产品
     * @param categoryId
     * @return
     */
    public static ResultSet searchByCategory(int categoryId){
        String sql = "SELECT * FROM product WHERE category_id = ?";
        return DatabaseUtil.executeQuery(sql,categoryId);
    }

    /**
     * 通过名称查询产品
     * @param name
     * @return
     */
    public static ResultSet searchByName(String name){
        name = "%" + name + "%";
        String sql = "SELECT * FROM product WHERE name LIKE ?";
        return DatabaseUtil.executeQuery(sql,name);
    }

    /**
     * 通过价格查询产品
     * @param price
     * @return
     */
    public static ResultSet searchByPrice(double price){
        String sql = "SELECT * FROM product WHERE price <= ?";
        return DatabaseUtil.executeQuery(sql,price);
    }
}
