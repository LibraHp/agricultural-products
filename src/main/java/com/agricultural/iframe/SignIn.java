package com.agricultural.iframe;

import com.agricultural.bean.User;
import com.agricultural.dbchange.UserDB;
import com.agricultural.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn {
    private JPanel main;
    private JLabel title;
    private JLabel username;
    private JTextField inUsername;
    private JLabel password;
    private JTextField inPassword;
    private JLabel mail;
    private JTextField inMail;
    private JLabel local;
    private JTextField inLocal;
    private JButton submit;
    private JLabel repeatPassword;
    private JTextField inRepeatPassword;
    private JFrame frame;

    public SignIn() {
        initializeComponents();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inUsername.getText().isEmpty() || inPassword.getText().isEmpty() || inMail.getText().isEmpty() || inLocal.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请填写完整");
                } else if (!inPassword.getText().equals(inRepeatPassword.getText())) {
                    JOptionPane.showMessageDialog(null, "两次密码不一致");
                } else if (UserService.checkUser(inUsername.getText())) {
                    JOptionPane.showMessageDialog(null, "用户名已存在");
                } else {
                    User user = new User();
                    user.setUsername(inUsername.getText());
                    user.setPassword(inPassword.getText());
                    user.setEmail(inMail.getText());
                    user.setAddress(inLocal.getText());
                    user.setIs_admin("0");
                    int res = UserDB.addUser(user);
                    if (res == 1) {
                        JOptionPane.showMessageDialog(null, "注册成功");
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "注册失败");
                    }
                }
            }
        });
    }

    private void initializeComponents() {
        frame = new JFrame("SignIn");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
