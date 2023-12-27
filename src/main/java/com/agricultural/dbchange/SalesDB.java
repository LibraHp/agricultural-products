package com.agricultural.dbchange;

import com.agricultural.bean.Sales;
import com.agricultural.util.DatabaseUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalesDB {
    /**
     * 获取销售统计数据
     *
     * @return
     */
    public static List<Sales> getSalesList() {
        String sql = "SELECT * FROM sales";
        try (ResultSet resultSet = DatabaseUtil.singleSentenceSql(sql)) {
            List<Sales> salesList = new ArrayList<>();
            while (resultSet.next()) {
                Sales sales = new Sales();
                sales.setProductId(resultSet.getInt(1));
                sales.setTotalSales(resultSet.getInt(2));
                salesList.add(sales);
            }
            return salesList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
