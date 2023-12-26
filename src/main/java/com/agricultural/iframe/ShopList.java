package com.agricultural.iframe;

import com.agricultural.bean.Product;
import com.agricultural.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ShopList {
    private JPanel main;
    private JPanel body;
    private JLabel title;
    private JTable tableList;
    private JScrollPane tableBody;

    public ShopList() {
        initializeComponents();
        initializeTableList();
    }

    /**
     * 初始化表格数据
     */
    private void initializeTableList() {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        // 设置表头
        model.setColumnIdentifiers(new Object[]{"ID", "名称", "分类", "价格", "描述"});
        // 获取产品数据
        List<Product> productList = ProductService.getProductList();
        for (Product product : productList) {
            Object[] rowData = new Object[5];
            rowData[0] = product.getId();
            rowData[1] = product.getName();
            rowData[2] = product.getCategoryId();
            rowData[3] = product.getPrice();
            rowData[4] = product.getDescription();
            model.addRow(rowData);
        }
        // 添加表格列头
        tableList.setModel(model); // 设置表格的数据模型
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("ShopList");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
