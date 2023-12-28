package com.agricultural.iframe;

import com.agricultural.bean.User;
import com.agricultural.service.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserManagementIframe {
    private JPanel main;
    private JPanel body;
    private JLabel title;
    private JScrollPane tableBody;
    private JTable tableList;
    private JPanel management;
    private JButton addUser;
    private JButton delUser;

    public UserManagementIframe() {
        initializeComponents();
        initializeTableList();
        tableList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // 获取选中的行索引
                    int selectedRow = tableList.getSelectedRow();
                    int userId = (int) tableList.getValueAt(selectedRow, 0);
                    User user = UserService.getUser(userId);
                    ChangeUserInfo changeUserInfo = new ChangeUserInfo(user);
                }
            }
        });
    }

    private void initializeTableList() {
        // 初始化表格组件
        DefaultTableModel model = new DefaultTableModel();
        // 设置表头
        model.setColumnIdentifiers(new Object[]{"ID", "用户名", "邮箱", "地址", "是否管理员"});
        // 获取销量数据
        List<User> userList = UserService.getUserList();
        for (User user : userList) {
            Object[] rowData = new Object[5];
            rowData[0] = user.getId();
            rowData[1] = user.getUsername();
            rowData[2] = user.getEmail();
            rowData[3] = user.getAddress();
            rowData[4] = user.getIs_admin().equals("1") ? "是" : "否";
            model.addRow(rowData);
        }
        tableList.setModel(model);
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("UserManagementIframe");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
