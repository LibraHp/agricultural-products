package com.agricultural.iframe;

import com.agricultural.bean.Product;
import com.agricultural.bean.Sales;
import com.agricultural.dbchange.SalesDB;
import com.agricultural.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.List;

public class SalesIframe {
    private JPanel main;
    private JPanel body;
    private JLabel title;
    private JScrollPane tableBody;
    private JTable tableList;
    private JLabel total;
    private JLabel outTotal;
    private JLabel money;
    private JLabel outMoney;

    public SalesIframe() {
        initializeComponents();
        initializeTableList();
    }

    private void initializeTableList() {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        // 设置表头
        model.setColumnIdentifiers(new Object[]{"产品名称", "价格", "销量"});
        // 获取销量数据
        List<Sales> salesList = SalesDB.getSalesList();
        int totalSum = 0;
        double moneySum = 0.0;
        for(Sales sales : salesList){
            Product product = ProductService.getProduct(sales.getProductId());
            Object[] rowData = new Object[4];
            rowData[0] = product.getName();
            rowData[1] = product.getPrice();
            rowData[2] = sales.getTotalSales();
            totalSum += sales.getTotalSales();
            moneySum += product.getPrice() * sales.getTotalSales();
            model.addRow(rowData);
        }
        outTotal.setText(totalSum + " 件");
        outMoney.setText(String.format("%.2f", moneySum) + " 元");
        tableList.setModel(model);
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("SalesIframe");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
