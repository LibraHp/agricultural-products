package com.agricultural.iframe;

import com.agricultural.bean.User;
import com.agricultural.dbchange.UserDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeUserInfo {
    private JPanel main;
    private JLabel title;
    private JPanel body;
    private JLabel inUsername;
    private JLabel username;
    private JTextField inPassword;
    private JTextField inMail;
    private JTextField inLocal;
    private JLabel password;
    private JLabel mail;
    private JLabel local;
    private JButton submit;

    public ChangeUserInfo(User user) {
        this.inUsername.setText(user.getUsername());
        this.inPassword.setText(user.getPassword());
        this.inMail.setText(user.getEmail());
        this.inLocal.setText(user.getAddress());
        initializeComponents();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setUsername(inUsername.getText());
                user.setPassword(inPassword.getText());
                user.setEmail(inMail.getText());
                user.setAddress(inLocal.getText());
                int res = UserDB.updateUser(user);
                if(res == 1){
                    JOptionPane.showMessageDialog(null, "修改成功");
                }else {
                    JOptionPane.showMessageDialog(null, "修改失败");
                }
            }
        });
    }

    private void initializeComponents() {
        JFrame frame = new JFrame("initializeComponents");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
