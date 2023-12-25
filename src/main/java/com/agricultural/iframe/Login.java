package com.agricultural.iframe;

import com.agricultural.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login{
    private JPanel main;
    private JTextField inUsername;
    private JTextField inPassword;
    private JButton loginButton;
    private JLabel password;
    private JLabel username;
    private JButton resetButton;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = inUsername.getText();
                String password = inPassword.getText();
                if (UserService.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "success");
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
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //使窗口居中
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
