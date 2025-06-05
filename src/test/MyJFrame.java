package test;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//way3，MyJFrame实现事件监听，接口
public class MyJFrame extends JFrame implements ActionListener {

    //按钮1,对象
    JButton jButton = new JButton("qqq按钮");
    JButton jButton2 = new JButton("222按钮");
    //构造函数
    public MyJFrame() {
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
        //组件添加事件，way3，传递本类的对象，窗口，以有事件监听的方法
        jButton.addActionListener(this);

        //配置
        jButton2.setBounds(100,0,200,50);
        //组件添加事件，way3，传递本类的对象
        jButton2.addActionListener(this);
        //容器组件添加按钮?
        //this.add(jButton);

        //获取隐藏容器
        this.getContentPane().add(jButton);
        this.getContentPane().add(jButton2);

        //设置可见
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==jButton) {
            //this.setBounds(0,0,50,50 );//bug，重新设置了x，y，不是this，this此时是窗体
            jButton.setSize(200,200 );//变大
        }else if(source==jButton2) {
            Random rand = new Random();
            //this.setLocation(rand.nextInt(400),rand.nextInt(400) );//移动位置,this是窗体
            jButton2.setLocation(rand.nextInt(500),rand.nextInt(500) );//移动位置
        }
    }
}
