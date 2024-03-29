package com.agricultural.iframe;

import com.agricultural.bean.Product;
import com.agricultural.bean.Sales;
import com.agricultural.dbchange.SalesDB;
import com.agricultural.service.ProductService;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.sql.ResultSet;
import java.util.List;
import java.util.Locale;

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
        for (Sales sales : salesList) {
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
        main.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(20, 20, 20, 20), -1, -1));
        body = new JPanel();
        body.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        main.add(body, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$(null, -1, 18, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("销售统计");
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        body.add(panel1, cc.xy(1, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        total = new JLabel();
        Font totalFont = this.$$$getFont$$$(null, -1, 20, total.getFont());
        if (totalFont != null) total.setFont(totalFont);
        total.setText("总销量");
        panel1.add(total, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outTotal = new JLabel();
        Font outTotalFont = this.$$$getFont$$$(null, -1, 20, outTotal.getFont());
        if (outTotalFont != null) outTotal.setFont(outTotalFont);
        outTotal.setText("0");
        panel1.add(outTotal, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        money = new JLabel();
        Font moneyFont = this.$$$getFont$$$(null, -1, 20, money.getFont());
        if (moneyFont != null) money.setFont(moneyFont);
        money.setText("总销售金额");
        panel1.add(money, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outMoney = new JLabel();
        Font outMoneyFont = this.$$$getFont$$$(null, -1, 20, outMoney.getFont());
        if (outMoneyFont != null) outMoney.setFont(outMoneyFont);
        outMoney.setText("0");
        panel1.add(outMoney, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
