package com.agricultural.iframe;

import com.agricultural.bean.Product;
import com.agricultural.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListIframe {
    private JPanel main;
    private JTable tableList;
    private JLabel title;

    public ListIframe() {
        initializeTableList();
    }

    /**
     * 初始化表格数据
     */
    private void initializeTableList() {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("名称");
        model.addColumn("分类");
        model.addColumn("价格");
        model.addColumn("描述");

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

        // 设置按钮列的渲染器和编辑器
        TableColumn buttonColumn = tableList.getColumnModel().getColumn(4); // 获取操作列
        buttonColumn.setCellRenderer(new ButtonRenderer()); // 设置渲染器
        buttonColumn.setCellEditor(new ButtonEditor()); // 设置编辑器
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ListIframe");

        frame.setSize(500, 300); // 设置窗口的大小
        frame.setLocationRelativeTo(null); // 设置窗口居中

        frame.setResizable(false);
        frame.setContentPane(new ListIframe().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // ButtonEditor 类的定义
    static class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;

        public ButtonEditor() {
            button = new JButton("购买");
            // 在按钮上添加点击事件的逻辑
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(e.getID());
                    // 处理购买操作
                    System.out.println("点击购买按钮");
                }
            });
            // ...
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }

    // ButtonRenderer 类的定义
    static class ButtonRenderer extends DefaultTableCellRenderer {
        private JButton button;

        public ButtonRenderer() {
            button = new JButton("购买");
            // 在按钮上添加点击事件的逻辑
            // ...
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }
}
