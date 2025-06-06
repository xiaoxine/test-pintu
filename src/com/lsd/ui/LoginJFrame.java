package com.lsd.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class LoginJFrame extends JFrame implements MouseListener {
    JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
    JButton jButton1 = new JButton(new ImageIcon("image\\login\\登录按钮.png"));
    JButton jButton2 = new JButton(new ImageIcon("image\\login\\注册按钮.png"));
    JButton jButton3 = new JButton(new ImageIcon("image\\login\\登录按下.png"));
    JButton jButton4 = new JButton(new ImageIcon("image\\login\\注册按下.png"));
    JTextField userNameField = new JTextField();
    JPasswordField passWordField = new JPasswordField();
    JTextField testCodeField = new JTextField();

    //构造方法，加载配置
    public LoginJFrame() {

        //取消默认的组件居中方式，细节
        this.setLayout(null);
        this.setTitle("登录界面");
        this.setSize(480,430);

        background.setBounds(0,0,470,400);
        //隐藏容器加JLabel对象
        this.getContentPane().add(background);
        JLabel userName = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        userName.setBounds(150,150,47,17);
        JLabel passWord = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passWord.setBounds(150,190,32,16);
        JLabel testCode = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        testCode.setBounds(150,230,56,21);
/*        this.getContentPane().add(userName);
        this.getContentPane().add(passWord);
        this.getContentPane().add(testCode);*/
        //错误??

        background.add(userName);
        background.add(passWord);
        background.add(testCode);


        userNameField.setBounds(210,150,200,20);
        passWordField.setBounds(210,190,200,20);
        testCodeField.setBounds(210,230,100,20);
        String codeStr = "1234";
        JLabel rightCode = new JLabel();
        rightCode.setText(codeStr);
        rightCode.setBounds(310,230,50,20);

        jButton1.setBounds(150,270,128,47);
        jButton2.setBounds(300,270,128,47);
        jButton3.setBounds(150,270,128,47);
        jButton4.setBounds(300,270,128,47);
        //去除按钮边框和背景
        jButton1.setBorderPainted(false);
        jButton2.setBorderPainted(false);
        jButton3.setBorderPainted(false);
        jButton4.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton3.setContentAreaFilled(false);
        jButton4.setContentAreaFilled(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton1.addMouseListener(this);
        jButton2.addMouseListener(this);
        jButton3.addMouseListener(this);
        jButton4.addMouseListener(this);

        background.add(jButton1);
        background.add(jButton2);
        background.add(jButton3);
        background.add(jButton4);
        background.add(userNameField);
        background.add(passWordField);
        background.add(testCodeField);//验证
        background.add(rightCode);



        //居中
        this.setLocationRelativeTo(null);
        //默认关闭，0,1,2什么也不做，暴露,关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        //shift+ enter 中途换行
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //当点击登录按钮

        //获取验证码的输入，密码用户名

        //比较

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();//事件获取
        if(source == jButton1){
            jButton1.setVisible(false);
            jButton3.setVisible(true);
        } else if (source == jButton2) {
            jButton2.setVisible(false);
            jButton4.setVisible(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
/*        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(false);
        jButton4.setVisible(false);*/
        //

        Object source = e.getSource();//事件获取
        if(source == jButton3){
            jButton1.setVisible(true);
            jButton3.setVisible(false);
        } else if (source == jButton4) {
            jButton2.setVisible(true);
            jButton4.setVisible(false);
        }

    }
}
