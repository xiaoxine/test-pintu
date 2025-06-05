package com.lsd.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        this.setTitle("注册界面");
        this.setSize(488,430);
        //多个窗口，在顶
        //this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //默认关闭，0,1,2什么也不做，暴露,关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //shift+ enter 中途换行
    }
}
