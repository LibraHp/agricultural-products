package com.agricultural.iframe;

import com.agricultural.bean.Category;
import com.agricultural.bean.Product;
import com.agricultural.dbchange.CategoryDB;
import com.agricultural.service.ProductService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

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
    private final int userId = Login.user.getId();

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
        main.setLayout(new GridLayoutManager(2, 1, new Insets(20, 20, 20, 20), -1, -1));
        body = new JPanel();
        body.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:grow"));
        main.add(body, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$(null, -1, 18, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("商品列表");
        CellConstraints cc = new CellConstraints();
        body.add(title, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        tableBody = new JScrollPane();
        body.add(tableBody, cc.xy(1, 5, CellConstraints.FILL, CellConstraints.FILL));
        tableList = new JTable();
        tableList.setDropMode(DropMode.USE_SELECTION);
        tableList.setEnabled(true);
        tableList.setFillsViewportHeight(false);
        Font tableListFont = this.$$$getFont$$$(null, -1, -1, tableList.getFont());
        if (tableListFont != null) tableList.setFont(tableListFont);
        tableList.setShowVerticalLines(true);
        tableBody.setViewportView(tableList);
        searchView = new JPanel();
        searchView.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        body.add(searchView, cc.xy(1, 3));
        selectModel = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("请选择查询的条件");
        defaultComboBoxModel1.addElement("分类");
        defaultComboBoxModel1.addElement("价格");
        defaultComboBoxModel1.addElement("名称");
        selectModel.setModel(defaultComboBoxModel1);
        searchView.add(selectModel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchText = new JTextField();
        searchView.add(searchText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), new Dimension(-1, 30), 0, false));
        search = new JButton();
        search.setText("搜索");
        searchView.add(search, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryList = new JComboBox();
        searchView.add(categoryList, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        shopping = new JPanel();
        shopping.setLayout(new GridLayoutManager(4, 2, new Insets(0, 10, 0, 10), -1, -1));
        main.add(shopping, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        shopping.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "购买商品", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        shopName = new JLabel();
        shopName.setText("商品名");
        shopping.add(shopName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outShopName = new JLabel();
        outShopName.setText("请选择商品");
        shopping.add(outShopName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        shopPrice = new JLabel();
        shopPrice.setText("价格");
        shopping.add(shopPrice, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outShopPrice = new JLabel();
        outShopPrice.setText("请选择商品");
        shopping.add(outShopPrice, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        shopDescription = new JLabel();
        shopDescription.setText("描述");
        shopping.add(shopDescription, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outShopDescription = new JLabel();
        outShopDescription.setText("请选择商品");
        shopping.add(outShopDescription, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buy = new JButton();
        buy.setText("购买");
        shopping.add(buy, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        title.setLabelFor(tableBody);
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
