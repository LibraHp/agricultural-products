package com.agricultural.service;

import com.agricultural.dbchange.UserDB;

public class UserService {
    public static boolean login(String username,String password){
        return UserDB.login(username, password) != null;
    }
}
