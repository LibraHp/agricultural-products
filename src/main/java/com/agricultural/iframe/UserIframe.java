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
    private JFrame frame;
    private User user;

    public UserIframe(User user) {
        this.user = user;
        this.userName.setText("欢迎您：" + user.getUsername());
        this.userPermission.setText("您的身份为：" + (user.getIs_admin().equals("1") ? "管理员" : "普通用户"));
        initializeComponents();

        changeInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChangeUserInfo changeUserInfo = new ChangeUserInfo(user);
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
                super.mouseClicked(e);
            }
        });
        myShop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PurchaseList purchaseList = new PurchaseList(user.getId());
            }
        });
    }

    private void initializeComponents() {
        frame = new JFrame("UserIframe");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
