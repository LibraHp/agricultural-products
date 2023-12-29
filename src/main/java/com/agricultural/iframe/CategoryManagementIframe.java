package com.agricultural.iframe;

import com.agricultural.bean.Category;
import com.agricultural.dbchange.CategoryDB;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

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
                if (name == null) {
                    return;
                }
                // 输入判空
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入内容");
                    return;
                }
                if (CategoryDB.checkSameName(name)) {
                    JOptionPane.showMessageDialog(null, "该分类已存在");
                } else {
                    CategoryDB.addCategory(name);
                }
                initializeTableList();
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableList.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择分类");
                    return;
                }
                String name = JOptionPane.showInputDialog(null, "请输入修改后的分类名", tableList.getValueAt(selectedRow, 1));
                // 点击弹窗的否则无事件
                if (name == null) {
                    return;
                }
                if (CategoryDB.checkSameName(name)) {
                    JOptionPane.showMessageDialog(null, "该分类已存在");
                } else {
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
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择分类");
                    return;
                }
                if (CategoryDB.checkCategoryHaveProduct((int) tableList.getValueAt(selectedRow, 0)) != 0) {
                    JOptionPane.showMessageDialog(null, "该分类已有商品，无法删除");
                    return;
                }
                // 询问是否删除
                int confirm = JOptionPane.showConfirmDialog(null, "是否删除该分类", "提示", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int categoryId = (int) tableList.getValueAt(selectedRow, 0);
                    int res = CategoryDB.deleteCategory(categoryId);
                    if (res == 1) {
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        main = new JPanel();
        main.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(20, 20, 20, 20), -1, -1));
        body = new JPanel();
        body.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:d:grow"));
        main.add(body, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$(null, -1, 18, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("分类管理");
        CellConstraints cc = new CellConstraints();
        body.add(title, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        tableBody = new JScrollPane();
        body.add(tableBody, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tableList = new JTable();
        tableList.setDropMode(DropMode.USE_SELECTION);
        tableList.setEnabled(true);
        Font tableListFont = this.$$$getFont$$$(null, -1, -1, tableList.getFont());
        if (tableListFont != null) tableList.setFont(tableListFont);
        tableList.setShowVerticalLines(true);
        tableBody.setViewportView(tableList);
        management = new JPanel();
        management.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        main.add(management, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addCategory = new JButton();
        addCategory.setText("添加分类");
        management.add(addCategory, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delCategory = new JButton();
        delCategory.setText("删除分类");
        management.add(delCategory, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        edit = new JButton();
        edit.setText("编辑分类");
        management.add(edit, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reFresh = new JButton();
        reFresh.setText("刷新列表");
        management.add(reFresh, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return main;
    }
}
