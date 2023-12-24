package com.agricultural.dbchange;

import com.agricultural.bean.User;
import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;

public class UserDB {
    /**
     * 新增用户
     * @param usr
     */
    public static int addUser(User usr) {
        String sql = "insert into user (username, password, email, address, is_admin) values (?, ?, ?, ?, ?)";
        return DatabaseUtil.executeUpdate(sql, usr.getUsername(), usr.getPassword(), usr.getEmail(), usr.getAddress(), usr.getIs_admin());
    }
    /**
     * 删除用户
     * @param id
     */
    public static int deleteUser(int id) {
        String sql = "delete from user where id = ?";
        return DatabaseUtil.executeUpdate(sql, id);
    }

    /**
     * 修改用户
     * @param usr
     */
    public static int updateUser(User usr) {
        String sql = "update user set username = ?, password = ?, email = ?, address = ?, is_admin = ? where id = ?";
        return DatabaseUtil.executeUpdate(sql, usr.getUsername(), usr.getPassword(), usr.getEmail(), usr.getAddress(), usr.getIs_admin(), usr.getId());
    }

    /**
     * 查询用户
     * @param id
     */
    public static User getUser(int id) {
        String sql = "select * from user where id = ?";
        ResultSet resultSet = DatabaseUtil.executeQuery(sql, id);
        try {
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            } else {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户列表
     */
    public static ResultSet getUserList() {
        String sql = "select * from user";
        return DatabaseUtil.executeQuery(sql);
    }
}
