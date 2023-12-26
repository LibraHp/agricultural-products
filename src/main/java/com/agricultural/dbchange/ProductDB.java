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
    public static Product getProduct(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (ResultSet resultSet = DatabaseUtil.executeQuery(sql, id);) {
            if (resultSet != null && resultSet.next()) {
                return new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4), resultSet.getString(5));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        String sql = "INSERT INTO purchase (user_id, product_id, purchase_date) VALUES (?, ?, CURRENT_DATE)";
        return DatabaseUtil.executeUpdate(sql, userId, productId);
    }
}
