package com.agricultural.dbchange;

import com.agricultural.bean.User;
import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;

public class UserDB {
    /**
     * 新增用户
     *
     * @param usr 用户
     * @return int 影响行数
     */
    public static int addUser(User usr) {
        String sql = "insert into user (username, password, email, address, is_admin) values (?, ?, ?, ?, ?)";
        return DatabaseUtil.executeUpdate(sql, usr.getUsername(), usr.getPassword(), usr.getEmail(), usr.getAddress(), usr.getIs_admin());
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return int 影响行数
     */
    public static int deleteUser(int id) {
        String sql = "delete from user where id = ?";
        return DatabaseUtil.executeUpdate(sql, id);
    }

    /**
     * 修改用户
     *
     * @param usr 用户
     * @return int 影响行数
     */
    public static int updateUser(User usr) {
        String sql = "update user set username = ?, password = ?, email = ?, address = ?, is_admin = ? where id = ?";
        return DatabaseUtil.executeUpdate(sql, usr.getUsername(), usr.getPassword(), usr.getEmail(), usr.getAddress(), usr.getIs_admin(), usr.getId());
    }

    /**
     * 查询用户
     *
     * @param id 用户id
     * @return ResultSet 查询结果
     */
    public static ResultSet getUser(int id) {
        String sql = "select * from user where id = ?";
        return DatabaseUtil.executeQuery(sql, id);
    }

    /**
     * 获取用户列表
     * @return ResultSet 查询结果
     */
    public static ResultSet getUserList() {
        String sql = "select * from user";
        return DatabaseUtil.executeQuery(sql);
    }

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 密码
     * @return ResultSet 查询结果，包括用户id，用户名，密码，邮箱，地址，是否为管理R
     */
    public static ResultSet login(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        return DatabaseUtil.executeQuery(sql, username, password);
    }

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return ResultSet 查询结果
     */
    public static ResultSet checkUser(String username){
        String sql = "select * from user where username = ?";
        return DatabaseUtil.executeQuery(sql,username);
    }
}
