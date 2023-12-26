package com.agricultural.iframe;

import com.agricultural.bean.User;
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
        initializeComponents();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的用户名和密码
                String username = inUsername.getText();
                char[] pwd = inPassword.getPassword();
                String password = new String(pwd);
                User user = UserService.login(username, password);
                //判断登录逻辑
                if (user != null) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    // 销毁当前页面
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(main);
                    frame.dispose();
                    // 跳转页面
                    UserIframe userIframe = new UserIframe(user);
                }else{
                    JOptionPane.showMessageDialog(null, "登录失败,账号或密码错误");
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            /**
             * 清除输入
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                inUsername.setText("");
                inPassword.setText("");
            }
        });
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
