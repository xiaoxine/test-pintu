package test;

import com.lsd.ui.RegisterJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//way1,way2
public class Test3 {
    public static void main(String[] args) {
        
        JFrame frame = new RegisterJFrame();
        //标题
        frame.setTitle("事件演示");
        //大小
        frame.setSize(683,690);
        //多个窗口，在顶
        frame.setAlwaysOnTop(true);
        //居中,窗口显示居中
        frame.setLocationRelativeTo(null);
        //默认关闭，0,1,2什么也不做，暴露,关闭，，JFrame 实现了 WindowConstants 接口
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //按钮,对象
        JButton jButton = new JButton("yanshi按钮");
        //配置
        jButton.setBounds(0,0,100,50);
        //组件添加事件，way1
        //jButton.addActionListener(new MyActionListener());

/*        public void addActionListener(ActionListener l) {//这里
            listenerList.add(ActionListener.class, l);
        }*/
        //匿名内部类，way2
        jButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("way2,匿名内部类");
            }
        });



        //容器组件添加按钮
        frame.add(jButton);
        //取消默认的组件居中方式，细节
        frame.setLayout(null);
    }
}
