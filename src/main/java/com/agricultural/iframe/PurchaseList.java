package com.agricultural.iframe;

import com.agricultural.dbchange.ProductDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class PurchaseList {
    private JPanel main;
    private JPanel body;
    private JLabel title;
    private JScrollPane tableBody;
    private JTable tableList;
    private int userId;
    public PurchaseList(int userId){
        this.userId = userId;
        initializeComponents();
        initializeTableList();
    }
    private void initializeTableList() {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        // 设置表头
        model.setColumnIdentifiers(new Object[]{"ID", "购买时间", "产品名称", "价格"});
        // 获取产品数据
        try(ResultSet resultSet = ProductDB.getPurchaseListByUserId(userId)){
            while (resultSet.next()){
                Object[] rowData = new Object[4];
                rowData[0] = resultSet.getInt(1);
                rowData[1] = resultSet.getString(2);
                rowData[2] = resultSet.getString(3);
                rowData[3] = resultSet.getDouble(4);
                model.addRow(rowData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 添加表格列头
        tableList.setModel(model); // 设置表格的数据模型
        tableList.getColumnModel().getColumn(0).setPreferredWidth(10);
        tableList.getColumnModel().getColumn(2).setPreferredWidth(10);
        tableList.getColumnModel().getColumn(3).setPreferredWidth(10);
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("PurchaseList");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
