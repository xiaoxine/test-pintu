package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;


public class MyJFrame3 extends JFrame implements KeyListener{

    //按钮1,对象
    JButton jButton = new JButton("键盘-按钮");

    //构造函数
    public MyJFrame3() {
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
        //组件添加事件，way3，传递本类的对象，窗口，给当前界面监听键盘
        jButton.addKeyListener(this);
        //获取隐藏容器
        this.getContentPane().add(jButton);

        //设置可见
        this.setVisible(true);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下键盘");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开键盘");
        //获取类型
        int code = e.getKeyCode();
        //System.out.println(code);

        if (code == 65) {
            System.out.println("A");
        }else if (code == 83) {
            System.out.println("S");
        }


    }
}
