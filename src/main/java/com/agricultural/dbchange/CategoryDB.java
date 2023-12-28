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

    /**
     * 判断是否有相同名称的分类
     * @param name 分类名称
     * @return boolean 是否有相同名称的分类
     */
    public static boolean checkSameName(String name){
        String sql = "SELECT * FROM product_category WHERE name = ?";
        try(ResultSet resultSet = DatabaseUtil.executeQuery(sql, name)){
            if (resultSet != null && resultSet.next()) {
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查所在分类的商品数量
     * @param id 分类id
     * @return int 商品数量
     */
    public static int checkCategoryHaveProduct(int id){
        String sql = "SELECT * FROM product WHERE category_id = ?";
        try(ResultSet resultSet = DatabaseUtil.executeQuery(sql, id)){
            if (resultSet != null) {
                int count = 0;
                while (resultSet.next()){
                    count++;
                }
                return count;
            }else {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 传入分类id删除分类
     * @param id 分类id
     * @return int 影响行数
     */
    public static int deleteCategory(int id){
        String sql = "DELETE FROM product_category WHERE id = ?";
        return DatabaseUtil.executeUpdate(sql, id);
    }

    /**
     * 添加分类
     * @param name 分类名称
     * @return int 影响行数
     */
    public static int addCategory(String name){
        String sql = "INSERT INTO product_category(name) VALUES(?)";
        return DatabaseUtil.executeUpdate(sql, name);
    }

    /**
     * 修改分类
     * @param id 分类id
     * @param name 分类名称
     * @return int 影响行数
     */
    public static int updateCategory(int id, String name){
        String sql = "UPDATE product_category SET name = ? WHERE id = ?";
        return DatabaseUtil.executeUpdate(sql, name, id);
    }
}
