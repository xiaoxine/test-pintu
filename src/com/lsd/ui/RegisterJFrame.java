package com.lsd.ui;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

//import static com.lsd.ui.LoginJFrame.list;

public class RegisterJFrame extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();
        if(source ==jButton2 )
        {
            String tempUser = userNameField.getText();
            char[] tempPass = passWordField.getPassword();

            if (Objects.equals(tempUser, "") || tempPass.length == 0) {
                DialogUtils.showJDialog(this,"请输入用户名或密码");
                return;
            }
            boolean userOld = false;
            for (int i = 0; i < LoginJFrame.list.size(); i++) {
                if (tempUser.equals(LoginJFrame.list.get(i).getUserName())) {
                    userOld = true;
                    break; // 找到匹配用户后可以退出循环
                }
            }
            if (userOld) {
                DialogUtils.showJDialog(this,"用户名已存在");
                return;
            }
            LoginJFrame.list.add(new User(tempUser, tempPass));
            this.setVisible(false);
            new LoginJFrame().setVisible(true);

        }
    }

    JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
    JButton jButton2 = new JButton(new ImageIcon("image\\login\\注册按钮.png"));
    JTextField userNameField = new JTextField();
    JPasswordField passWordField = new JPasswordField();
    public RegisterJFrame() {
        this.setTitle("注册界面");
        //取消默认的组件居中方式，细节
        this.setLayout(null);
        this.setSize(480, 430);
        background.setBounds(0, 0, 470, 400);
        //隐藏容器加JLabel对象
        this.getContentPane().add(background);
        JLabel userName = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        userName.setBounds(150, 150, 47, 17);
        JLabel passWord = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passWord.setBounds(150, 190, 32, 16);

        background.add(userName);
        background.add(passWord);
        userNameField.setBounds(210, 150, 200, 20);
        passWordField.setBounds(210, 190, 200, 20);
        jButton2.setBounds(200, 270, 128, 47);

        //去除按钮边框和背景

        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(this);

        background.add(jButton2);
        background.add(userNameField);
        background.add(passWordField);

        //居中
        this.setLocationRelativeTo(null);
        //默认关闭，0,1,2什么也不做，暴露,关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        //shift+ enter 中途换行
    }

}
