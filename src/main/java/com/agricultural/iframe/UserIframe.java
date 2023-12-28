package com.agricultural.iframe;

import com.agricultural.bean.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserIframe {
    private JPanel main;
    private JLabel title;
    private JPanel userInfo;
    private JLabel userPermission;
    private JLabel userName;
    private JPanel userOptions;
    private JLabel changeInfo;
    private JLabel productList;
    private JLabel myShop;
    private JLabel goBack;
    private JPanel adminOptions;
    private JLabel userManagement;
    private JLabel productManagement;
    private JLabel sales;
    private JFrame frame;
    private User user;

    public UserIframe() {
        this.user = Login.user;
        initializeComponents();
        checkAdmin();
        changeInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChangeUserInfo changeUserInfo = new ChangeUserInfo(null);
            }
        });
        goBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Login login = new Login();
            }
        });
        productList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShopList shoplist = new ShopList();
            }
        });
        myShop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PurchaseList purchaseList = new PurchaseList();
            }
        });
        sales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SalesIframe salesIframe = new SalesIframe();
            }
        });
        userManagement.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserManagementIframe userManagementIframe = new UserManagementIframe();
            }
        });
        productManagement.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProductManagementIframe productManagementIframe = new ProductManagementIframe();
            }
        });
    }

    private void initializeComponents() {
        frame = new JFrame("UserIframe");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void checkAdmin(){
        boolean isAdmin = user.getIs_admin().equals("1");
        userName.setText("欢迎您：" + user.getUsername());
        userPermission.setText("您的身份为：" + (isAdmin ? "管理员" : "普通用户"));
        if (isAdmin){
            userOptions.setVisible(false);
            adminOptions.setVisible(true);
        }else{
            userOptions.setVisible(true);
            adminOptions.setVisible(false);
        }
        frame.revalidate();
    }
}
