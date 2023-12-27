package com.agricultural.service;

import com.agricultural.bean.User;
import com.agricultural.dbchange.UserDB;
import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    /**
     * 用户登录方法
     *
     * @param username
     * @param password
     * @return
     */
    public static User login(String username, String password) {
        try (ResultSet resultSet = UserDB.login(username,password)) {
            if (resultSet != null && resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    public static List<User> getUserList() {
        try (ResultSet userList = UserDB.getUserList()) {
            List<User> users = new ArrayList<>();
            while (userList.next()) {
                User user = new User();
                user.setId(userList.getInt(1));
                user.setUsername(userList.getString(2));
                user.setPassword(userList.getString(3));
                user.setEmail(userList.getString(4));
                user.setAddress(userList.getString(5));
                user.setIs_admin(userList.getString(6));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    public static boolean checkUser(String username) {
        try (ResultSet resultSet = UserDB.checkUser(username)) {
            if (resultSet != null && resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询用户
     *
     * @param id
     */
    public static User getUser(int id) {
        try (ResultSet resultSet = UserDB.getUser(id)) {
            if (resultSet != null && resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
