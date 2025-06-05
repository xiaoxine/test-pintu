package com.lsd.ui;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        //1,主界面，游戏
        JFrame gameJframe = new JFrame();
        gameJframe.setSize(500, 500);
        gameJframe.setVisible(true);//false

        //2,登录界面，300行
        JFrame loginJframe = new JFrame();
        loginJframe.setSize(500, 500);

        loginJframe.setVisible(true);
        //3,注册界面，300行
        JFrame registerJframe = new JFrame();
        registerJframe.setSize(500, 500);
        registerJframe.setVisible(true);

        //共有属性，x,y,宽，高 JFrame
        //super();调用父类的东西
        //show();已过时，使用setVisible
        //main()不写业务逻辑

    }
}
