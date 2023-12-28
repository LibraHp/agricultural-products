package com.agricultural.iframe;

import com.agricultural.bean.User;
import com.agricultural.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login{
    private JPanel main;
    private JTextField inUsername;
    private JPasswordField inPassword;
    private JButton loginButton;
    private JLabel password;
    private JLabel username;
    private JButton resetButton;
    private JLabel signIn;
    private JFrame frame;
    public static User user;

    public Login() {
        initializeComponents();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的用户名和密码
                String username = inUsername.getText();
                char[] pwd = inPassword.getPassword();
                String password = new String(pwd);
                user = UserService.login(username, password);
                //判断登录逻辑
                if (user != null) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    // 销毁当前页面
                    frame.dispose();
                    // 跳转页面
                    UserIframe userIframe = new UserIframe();
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
        signIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignIn signIn1 = new SignIn();
            }
        });
    }

    private void initializeComponents() {
        frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
