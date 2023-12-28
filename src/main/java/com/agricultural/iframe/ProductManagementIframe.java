package com.agricultural.iframe;

import com.agricultural.bean.Product;
import com.agricultural.dbchange.CategoryDB;
import com.agricultural.dbchange.ProductDB;
import com.agricultural.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductManagementIframe {
    private JPanel main;
    private JPanel body;
    private JLabel title;
    private JScrollPane tableBody;
    private JTable tableList;
    private JPanel management;
    private JButton addProduct;
    private JButton delProdect;
    private JButton edit;
    private JButton reFresh;
    private JButton categoryManagement;

    public ProductManagementIframe() {
        initializeComponents();
        initializeTableList();
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeProduct changeProduct = new ChangeProduct(null);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableList.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择产品");
                    return;
                }
                int productId = (int) tableList.getValueAt(selectedRow, 0);
                Product product = ProductService.getProduct(productId);
                ChangeProduct changeProduct = new ChangeProduct(product);
            }
        });
        reFresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeTableList();
            }
        });
        delProdect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableList.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择产品");
                    return;
                }
                // 询问是否删除
                int confirm = JOptionPane.showConfirmDialog(null, "是否删除该商品", "提示", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int productId = (int) tableList.getValueAt(selectedRow, 0);
                    int res = ProductDB.deleteProduct(productId);
                    if (res == 1) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                    }
                }
                initializeTableList();
            }
        });
        categoryManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryManagementIframe categoryManagementIframe = new CategoryManagementIframe();
            }
        });
    }

    private void initializeTableList() {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        // 设置表头
        model.setColumnIdentifiers(new Object[]{"ID", "名称", "分类", "价格", "描述"});
        List<Product> productList = ProductService.getProductList();
        for (Product product : productList) {
            Object[] rowData = new Object[5];
            rowData[0] = product.getId();
            rowData[1] = product.getName();
            rowData[2] = CategoryDB.getCategoryListByID(product.getCategoryId()).getName();
            rowData[3] = product.getPrice();
            rowData[4] = product.getDescription();
            model.addRow(rowData);
        }
        tableList.setModel(model);
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("ProductManagementIframe");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
