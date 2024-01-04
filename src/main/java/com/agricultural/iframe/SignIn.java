package com.agricultural.iframe;

import com.agricultural.bean.User;
import com.agricultural.dbchange.UserDB;
import com.agricultural.service.UserService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

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
                    JOptionPane.showMessageDialog(null, "用户名已存在或不符合要求,请重新输入");
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        main = new JPanel();
        main.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$(null, -1, 22, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("添加账号");
        CellConstraints cc = new CellConstraints();
        main.add(title, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(6, 2, new Insets(10, 10, 10, 10), -1, -1));
        main.add(panel1, cc.xy(1, 3));
        username = new JLabel();
        username.setText("用户名");
        panel1.add(username, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inUsername = new JTextField();
        inUsername.setText("");
        panel1.add(inUsername, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(150, -1), null, 0, false));
        password = new JLabel();
        password.setText("密码");
        panel1.add(password, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inPassword = new JTextField();
        panel1.add(inPassword, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(150, -1), null, 0, false));
        mail = new JLabel();
        mail.setText("邮箱");
        panel1.add(mail, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inMail = new JTextField();
        panel1.add(inMail, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(150, -1), null, 0, false));
        local = new JLabel();
        local.setText("邮寄地址");
        panel1.add(local, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inLocal = new JTextField();
        panel1.add(inLocal, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(150, -1), null, 0, false));
        submit = new JButton();
        submit.setText("提交");
        panel1.add(submit, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), null, null, 0, false));
        repeatPassword = new JLabel();
        repeatPassword.setText("确认密码");
        panel1.add(repeatPassword, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inRepeatPassword = new JTextField();
        panel1.add(inRepeatPassword, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return main;
    }

}
