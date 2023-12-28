package com.agricultural.dbchange;

import com.agricultural.bean.Category;
import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDB {
    /**
     * 获取分类列表
     * @return List 分类列表
     */
    public static List<Category> getCategoryList(){
        String sql = "SELECT * FROM product_category";
        try(ResultSet resultSet = DatabaseUtil.singleSentenceSql(sql)){
            List<Category> categoryList = new ArrayList<>();
            while (resultSet != null && resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                categoryList.add(category);
            }
            return categoryList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过id获取分类
     * @param id 分类id
     * @return Category 返回分类对象
     */
    public static Category getCategoryListByID(int id){
        String sql = "SELECT * FROM product_category WHERE id = ?";
        try(ResultSet resultSet = DatabaseUtil.executeQuery(sql, id)){
            if (resultSet != null && resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                return category;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
