package com.agricultural.util;

import java.sql.*;

public class DatabaseUtil {
    private static Connection cn = null;

    static {
        try {
            String url = "jdbc:mysql://localhost:3306/agricultural";
            String username = "root";
            String password = "root";
            cn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池
     *
     * @return 数据库连接
     */
    public static Connection classDbInit() {
        return cn;
    }

    /**
     * 执行查询sql语句
     *
     * @param sql 执行的sql语句
     * @param params 传入的参数
     * @return 执行结果
     */
    public static ResultSet executeQuery(String sql, Object... params) {
        PreparedStatement pstm = null;
        try {
            pstm = cn.prepareStatement(sql);
            // 设置占位符参数
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i + 1, params[i]);
            }
            pstm.executeQuery();
            // 执行查询
            return pstm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 或者根据需要抛出自定义异常
        }
    }

    /**
     * 执行更新sql语句
     *
     * @param sql 传入的sql语句
     * @param params 传入的参数
     * @return 执行结果
     */
    public static Integer executeUpdate(String sql, Object... params) {
        PreparedStatement pstm = null;
        try {
            pstm = cn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i + 1, params[i]);
            }
            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行单句sql
     *
     * @param sql 传入的sql语句
     * @return 执行结果
     */
    public static ResultSet singleSentenceSql(String sql) {
        Statement stm = null;
        try {
            stm = cn.createStatement();
            return stm.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭数据库链接
     */
    public static void closeDb() {
        try {
            cn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
