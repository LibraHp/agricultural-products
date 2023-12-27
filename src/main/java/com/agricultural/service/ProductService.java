package com.agricultural.service;

import com.agricultural.bean.Product;
import com.agricultural.dbchange.ProductDB;
import com.agricultural.util.DatabaseUtil;
import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    /**
     * 获取产品列表
     *
     * @return
     */
    public static List<Product> getProductList() {
        try (ResultSet resultSet = ProductDB.getProductList()) {
            return getProductsList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int buyProduct(int userId, int productId) {
        return ProductDB.buyProduct(userId, productId);
    }

    /**
     * 查询产品信息
     *
     * @param id
     * @return
     */
    public static Product getProduct(int id) {
        try (ResultSet resultSet = ProductDB.getProduct(id)) {
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
     * 通过分类查询产品
     *
     * @param categoryId
     * @return
     */
    public static List<Product> searchByCategory(int categoryId) {
        try (ResultSet resultSet = ProductDB.searchByCategory(categoryId);) {
            return getProductsList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过名称查询产品
     *
     * @param name
     * @return
     */
    public static List<Product> searchByName(String name) {
        try (ResultSet resultSet = ProductDB.searchByName(name);) {
            return getProductsList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 提取resultSet中的List
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static List<Product> getProductsList(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setCategoryId(resultSet.getInt(3));
            product.setPrice(resultSet.getDouble(4));
            product.setDescription(resultSet.getString(5));
            products.add(product);
        }
        return products;
    }

    /**
     * 通过价格查询产品
     *
     * @param price
     * @return
     */
    public static List<Product> searchByPrice(double price) {
        try (ResultSet resultSet = ProductDB.searchByPrice(price)) {
            return getProductsList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
