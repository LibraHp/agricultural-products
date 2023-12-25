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
        String sql = "INSERT INTO Product (name, category_id, price, description) VALUES (?, ?, ?, ?)";
        return DatabaseUtil.executeUpdate(sql, product.getName(), product.getCategoryId(), product.getPrice(), product.getDescription());
    }

    /**
     * 删除产品信息
     *
     * @param id
     * @return
     */
    public static int deleteProduct(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        return DatabaseUtil.executeUpdate(sql, id);
    }

    /**
     * 修改产品信息
     *
     * @param product
     * @return
     */
    public static int updateProduct(Product product) {
        String sql = "UPDATE Product SET name = ?, category_id = ?, price = ?, description = ? WHERE id = ?";
        return DatabaseUtil.executeUpdate(sql, product.getName(), product.getCategoryId(), product.getPrice(), product.getDescription(), product.getId());
    }

    /**
     * 查询产品信息
     *
     * @param id
     * @return
     */
    public static Product getProduct(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
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
        String sql = "SELECT * FROM Product";
        return DatabaseUtil.executeQuery(sql);
    }
}
