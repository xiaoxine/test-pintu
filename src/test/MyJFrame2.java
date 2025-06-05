package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MyJFrame2 extends JFrame implements MouseListener {

    //按钮1,对象
    JButton jButton = new JButton("鼠标-按钮");
    JButton jButton2 = new JButton("222按钮");
    //构造函数
    public MyJFrame2() {
        //标题
        this.setTitle("事件演示");
        //大小
        this.setSize(683,690);
        //多个窗口，在顶
        this.setAlwaysOnTop(true);
        //居中,窗口显示居中
        this.setLocationRelativeTo(null);
        //默认关闭，
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的组件居中方式，细节
        this.setLayout(null);


        //配置
        jButton.setBounds(0,0,100,100);
        //组件添加事件，way3，传递本类的对象，窗口，以有鼠标监听的方法
        jButton.addMouseListener(this);

        //配置
        jButton2.setBounds(100,0,200,50);
        //获取隐藏容器
        this.getContentPane().add(jButton);
        this.getContentPane().add(jButton2);

        //设置可见
        this.setVisible(true);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("点击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按着不动");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("鼠标进入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("鼠标退出");
    }
}
