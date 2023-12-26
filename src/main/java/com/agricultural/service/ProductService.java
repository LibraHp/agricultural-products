package com.agricultural.service;

import com.agricultural.bean.Product;
import com.agricultural.dbchange.ProductDB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    /**
     * 获取产品列表
     * @return
     */
    public static List<Product> getProductList() {
        try(ResultSet productList = ProductDB.getProductList()){
            List<Product> products = new ArrayList<>();
            while (productList.next()){
                Product product = new Product();
                product.setId(productList.getInt(1));
                product.setName(productList.getString(2));
                product.setCategoryId(productList.getInt(3));
                product.setPrice(productList.getDouble(4));
                product.setDescription(productList.getString(5));
                products.add(product);
            }
            return products;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static int buyProduct(int id){
        return 0;
    }
}
