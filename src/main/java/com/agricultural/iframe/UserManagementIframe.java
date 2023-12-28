package com.agricultural.iframe;

import com.agricultural.bean.User;
import com.agricultural.dbchange.UserDB;
import com.agricultural.service.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton edit;
    private JButton reFresh;

    public UserManagementIframe() {
        initializeComponents();
        initializeTableList();
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableList.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(null, "请选择用户");
                    return;
                }
                int userId = (int) tableList.getValueAt(selectedRow, 0);
                User user = UserService.getUser(userId);
                ChangeUserInfo changeUserInfo = new ChangeUserInfo(user);
            }
        });
        reFresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeTableList();
            }
        });
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
            }
        });
        delUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableList.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(null, "请选择用户");
                    return;
                }
                // 询问是否删除
                int confirm = JOptionPane.showConfirmDialog(null, "是否删除该用户", "提示", JOptionPane.YES_NO_OPTION);
                int userId = (int) tableList.getValueAt(selectedRow, 0);
                if (confirm == 0) {
                    int res = UserDB.deleteUser(userId);
                    if (res == 1){
                        JOptionPane.showMessageDialog(null, "删除成功");
                        initializeTableList();
                    }else {
                        JOptionPane.showMessageDialog(null, "删除失败");
                    }
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
