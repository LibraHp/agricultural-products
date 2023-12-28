package com.agricultural.iframe;

import com.agricultural.bean.Category;
import com.agricultural.bean.Product;
import com.agricultural.dbchange.CategoryDB;
import com.agricultural.dbchange.ProductDB;
import com.agricultural.service.ProductService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChangeProduct {
    private JPanel main;
    private JLabel title;
    private JPanel body;
    private JLabel name;
    private JLabel category;
    private JLabel price;
    private JTextField inPrice;
    private JLabel description;
    private JTextField inDescription;
    private JButton submit;
    private JTextField inName;
    private JComboBox inCategory;
    private JFrame frame;
    private Product product;
    private boolean isChange;

    public ChangeProduct(Product changeProduct) {
        this.product = changeProduct;
        initCategoryList();
        if (product != null) {
            isChange = true;
            inName.setText(product.getName());
            inPrice.setText(String.valueOf(product.getPrice()));
            inDescription.setText(product.getDescription());
            inCategory.setSelectedIndex(product.getCategoryId() - 1);
        } else {
            isChange = false;
            this.product = new Product();
        }
        initializeComponents();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 判断各个输入框是否为空
                if (inName.getText().isEmpty() || inPrice.getText().isEmpty() || inDescription.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请填写完整信息");
                    return;
                }
                product.setName(inName.getText());
                product.setPrice(Double.parseDouble(inPrice.getText()));
                product.setDescription(inDescription.getText());
                product.setCategoryId(CategoryDB.fromCategoryGetId((String) inCategory.getSelectedItem()));
                if(isChange) {
                    changeProduct();
                } else {
                    addProduct();
                }
            }
        });
    }

    private void initCategoryList() {
        List<Category> categoryList = CategoryDB.getCategoryList();
        for (Category category : categoryList) {
            inCategory.addItem(category.getName());
        }
    }
    private void addProduct() {
        int res = ProductDB.addProduct(product);
        if (res == 1) {
            JOptionPane.showMessageDialog(null, "添加成功");
        } else {
            JOptionPane.showMessageDialog(null, "添加失败");
        }
    }
    private void changeProduct() {
        int res = ProductDB.updateProduct(product);
        if (res == 1) {
            JOptionPane.showMessageDialog(null, "修改成功");
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }

    private void initializeComponents() {
        frame = new JFrame("ChangeProduct");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
