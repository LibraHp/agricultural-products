package com.agricultural.iframe;

import com.agricultural.bean.Category;
import com.agricultural.dbchange.CategoryDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryManagementIframe {
    private JPanel main;
    private JPanel body;
    private JLabel title;
    private JScrollPane tableBody;
    private JTable tableList;
    private JPanel management;
    private JButton addCategory;
    private JButton delCategory;
    private JButton edit;
    private JButton reFresh;
    public CategoryManagementIframe() {
        initializeComponents();
        initializeTableList();
        reFresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeTableList();
            }
        });
        addCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, "请输入分类名");
                if(name == null){
                    return;
                }
                // 输入判空
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(null, "请输入内容");
                    return;
                }
                if(CategoryDB.checkSameName(name)){
                    JOptionPane.showMessageDialog(null, "该分类已存在");
                }else{
                    CategoryDB.addCategory(name);
                }
                initializeTableList();
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableList.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(null, "请选择分类");
                    return;
                }
                String name = JOptionPane.showInputDialog(null, "请输入修改后的分类名", tableList.getValueAt(selectedRow, 1));
                // 点击弹窗的否则无事件
                if(name == null){
                    return;
                }
                if(CategoryDB.checkSameName(name)){
                    JOptionPane.showMessageDialog(null, "该分类已存在");
                }else{
                    int categoryId = (int) tableList.getValueAt(selectedRow, 0);
                    int res = CategoryDB.updateCategory(categoryId, name);
                    if (res == 1) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                    }
                }
                initializeTableList();
            }
        });
        delCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableList.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(null, "请选择分类");
                    return;
                }
                if (CategoryDB.checkCategoryHaveProduct((int) tableList.getValueAt(selectedRow, 0)) != 0) {
                    JOptionPane.showMessageDialog(null, "该分类已有商品，无法删除");
                    return;
                }
                // 询问是否删除
                int confirm = JOptionPane.showConfirmDialog(null, "是否删除该分类", "提示", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    int categoryId = (int) tableList.getValueAt(selectedRow, 0);
                    int res = CategoryDB.deleteCategory(categoryId);
                    if(res == 1){
                        JOptionPane.showMessageDialog(null, "删除成功");
                    }
                    initializeTableList();
                }
            }
        });
    }
    private void initializeTableList() {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        // 设置表头
        model.setColumnIdentifiers(new Object[]{"ID", "分类", "分类含商品数量"});
        // 获取销量数据
        List<Category> categoryList = CategoryDB.getCategoryList();
        for (Category category : categoryList) {
            Object[] rowData = new Object[3];
            rowData[0] = category.getId();
            rowData[1] = category.getName();
            rowData[2] = CategoryDB.checkCategoryHaveProduct(category.getId());
            model.addRow(rowData);
        }
        tableList.setModel(model);
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("CategoryManagementIframe");
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
