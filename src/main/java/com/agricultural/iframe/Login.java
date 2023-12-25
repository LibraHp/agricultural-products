package com.agricultural.iframe;

import com.agricultural.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login{
    private JPanel main;
    private JTextField inUsername;
    private JPasswordField inPassword;
    private JButton loginButton;
    private JLabel password;
    private JLabel username;
    private JButton resetButton;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = inUsername.getText();
                char[] pwd = inPassword.getPassword();
                String password = new String(pwd);
                if (UserService.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    ListIframe.main(null);
                }else{
                    JOptionPane.showMessageDialog(null, "登录失败,账号或密码错误");
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inUsername.setText("");
                inPassword.setText("");
            }
        });
    }

    public static void main(String[] args) {
        Login login = new Login(); // 创建Login对象
        JFrame frame = new JFrame("Login");

        frame.setSize(400, 300); // 设置窗口的大小
        frame.setLocationRelativeTo(null); // 设置窗口居中

        frame.setResizable(false);
        frame.setContentPane(login.main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
