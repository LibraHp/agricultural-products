package com.agricultural.iframe;

import com.agricultural.bean.Category;
import com.agricultural.bean.Product;
import com.agricultural.dbchange.CategoryDB;
import com.agricultural.service.ProductService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShopList {
    private JPanel main;
    private JPanel body;
    private JLabel title;
    private JTable tableList;
    private JScrollPane tableBody;
    private JPanel shopping;
    private JButton buy;
    private JLabel shopName;
    private JLabel outShopName;
    private JLabel shopPrice;
    private JLabel outShopPrice;
    private JLabel shopDescription;
    private JLabel outShopDescription;
    private JPanel searchView;
    private JComboBox selectModel;
    private JTextField searchText;
    private JButton search;
    private JComboBox categoryList;
    private Product selectProduct;
    private JFrame frame;
    private int userId = Login.user.getId();

    public ShopList() {
        initializeComponents();
        categoryList.setVisible(false);
        initializeTableList(ProductService.getProductList());
        tableList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // 获取选中的行索引
                    int selectedRow = tableList.getSelectedRow();
                    if (selectedRow >= 0) {
                        int productId = (int) tableList.getValueAt(selectedRow, 0);
                        selectProduct = ProductService.getProduct(productId);
                        outShopName.setText(selectProduct.getName());
                        outShopDescription.setText(selectProduct.getDescription());
                        outShopPrice.setText(Double.toString(selectProduct.getPrice()));
                        // 显示选中行的数据
                        Object[] rowData = new Object[tableList.getColumnCount()];
                        for (int col = 0; col < tableList.getColumnCount(); col++) {
                            rowData[col] = tableList.getValueAt(selectedRow, col);
                        }
                    }
                }
            }
        });
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectProduct != null && ProductService.buyProduct(userId, selectProduct.getId()) == 1) {
                    JOptionPane.showMessageDialog(null, "购买成功，谢谢惠顾！");
                } else {
                    JOptionPane.showMessageDialog(null, "请选择商品后再购买");
                }
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = selectModel.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "请选择查询的内容");
                        break;
                    case 1:
                        List<Product> withCategory = ProductService.searchByCategory(CategoryDB.fromCategoryGetId((String) categoryList.getSelectedItem()));
                        initializeTableList(withCategory);
                        break;
                    case 2:
                        if (!searchText.getText().isEmpty()) {
                            List<Product> withPrice = ProductService.searchByPrice(Double.parseDouble(searchText.getText()));
                            initializeTableList(withPrice);

                        } else {
                            JOptionPane.showMessageDialog(null, "请输入内容！");
                        }
                        break;
                    case 3:
                        if (!searchText.getText().isEmpty()) {
                            List<Product> withName = ProductService.searchByName(searchText.getText());
                            initializeTableList(withName);
                        } else {
                            JOptionPane.showMessageDialog(null, "请输入内容！");
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "查询出错！");
                }
            }
        });
        selectModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = selectModel.getSelectedIndex();
                switch (selectedIndex) {
                    case 1:
                        searchText.setVisible(false);
                        categoryList.setVisible(true);
                        categoryList.removeAllItems();
                        List<Category> categoryItems = CategoryDB.getCategoryList();
                        for (Category cg : categoryItems) {
                            categoryList.addItem(cg.getName());
                        }
                        frame.revalidate();
                        break;
                    case 2:
                        searchText.setVisible(true);
                        categoryList.setVisible(false);
                        frame.revalidate();
                        break;
                    default:
                        searchText.setVisible(true);
                        categoryList.setVisible(false);
                        frame.revalidate();
                }
            }
        });
    }

    /**
     * 初始化表格数据
     */
    private void initializeTableList(List<Product> productList) {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        // 设置表头
        model.setColumnIdentifiers(new Object[]{"ID", "名称", "分类", "价格", "描述"});
        for (Product product : productList) {
            Object[] rowData = new Object[5];
            rowData[0] = product.getId();
            rowData[1] = product.getName();
            rowData[2] = CategoryDB.getCategoryListByID(product.getCategoryId()).getName();
            rowData[3] = product.getPrice();
            rowData[4] = product.getDescription();
            model.addRow(rowData);
        }
        // 设置表格的数据模型
        tableList.setModel(model);
        // 设置表格选择模式为单选
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void initializeComponents() {
        frame = new JFrame("ShopList");
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
