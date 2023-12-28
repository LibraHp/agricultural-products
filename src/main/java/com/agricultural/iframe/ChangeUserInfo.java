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
    private JCheckBox inIsAdmin;
    private JLabel isAdmin;
    private JTextField inUserNameTextField;
    private User user;
    private JFrame frame;

    public ChangeUserInfo(User changeUser) {
        checkAdmin(changeUser);
        this.inUsername.setText(user.getUsername());
        this.inUserNameTextField.setText(user.getUsername());
        this.inPassword.setText(user.getPassword());
        this.inMail.setText(user.getEmail());
        this.inLocal.setText(user.getAddress());
        this.inIsAdmin.setSelected(user.getIs_admin().equals("1"));
        initializeComponents();

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setUsername(inUserNameTextField.getText());
                user.setPassword(inPassword.getText());
                user.setEmail(inMail.getText());
                user.setAddress(inLocal.getText());
                user.setIs_admin(inIsAdmin.isSelected() ? "1" : "0");
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
        frame = new JFrame("initializeComponents");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    private void checkAdmin(User changeUser){
        user = Login.user;
        if (user.getIs_admin().equals("1") && changeUser != null){
            user = changeUser;
            inUserNameTextField.setVisible(true);
            inUsername.setVisible(false);
            isAdmin.setVisible(true);
            inIsAdmin.setVisible(true);
        }else{
            inUserNameTextField.setVisible(false);
            inUsername.setVisible(true);
            isAdmin.setVisible(false);
            inIsAdmin.setVisible(false);
        }
    }
}
