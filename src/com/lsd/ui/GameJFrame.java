package com.lsd.ui;

import javax.swing.*;

public class GameJFrame extends JFrame {

    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //图片
        initImage();
        //可见
        this.setVisible(true);
        //shift+ enter 中途换行
    }

    private void initImage() {
//        //成就ImageIcon对象
//        //ImageIcon icon = new ImageIcon("D:\\java-project\\test-pintu\\image\\animal\\animal3\\1.jpg");
//
//        //创建一个label对象，管理容器
//        JLabel Jlabel = new JLabel(new ImageIcon("D:\\java-project\\test-pintu\\image\\animal\\animal3\\1.jpg"));
//        Jlabel.setBounds(0, 0, 105, 105);//105,105
//        //this.add(Jlabel);
//        //获取隐藏容器panel再添加
//        this.getContentPane().add(Jlabel);
        int num =1;
        //外循环，把内循环重复4次
        for (int i = 0; i < 4; i++) {
            //内循环，添加4张图片
            for (int j = 0; j < 4; j++) {
                //创建一个label对象，管理容器加图片
                JLabel Jlabel = new JLabel(new ImageIcon("D:\\java-project\\test-pintu\\image\\animal\\animal3\\"+num+".jpg"));
                Jlabel.setBounds(105*j, 105*i, 105, 105);//105,105
                //获取隐藏容器panel再添加
                this.getContentPane().add(Jlabel);
                //加载下一张
                num++;
            }
        }

    }

    private void initJFrame() {
        //标题
        this.setTitle("拼图单机版v1.0.0");
        //大小
        this.setSize(488,430);
        //多个窗口，在顶
        this.setAlwaysOnTop(true);
        //居中,窗口显示居中
        this.setLocationRelativeTo(null);
        //默认关闭，0,1,2什么也不做，暴露,关闭，，JFrame 实现了 WindowConstants 接口
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//仅销毁当前窗口，但程序继续运行（常用于子窗口）

        //取消默认的组件居中方式，细节
        this.setLayout(null);
    }
    private void initJMenuBar() {
        //初始化菜单栏
        JMenuBar menuBar = new JMenuBar();
        //菜单
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutItem = new JMenu("关于我们");
        //条目
        JMenuItem exitItem = new JMenuItem("退出游戏");
        JMenuItem replayItem = new JMenuItem("重玩");
        JMenuItem reLoginItem = new JMenuItem("登录");
        //
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(exitItem);
        //
        JMenuItem wxItem= new JMenuItem("公众号");
        //条目加到选项
        aboutItem.add(wxItem);
        //菜单加到菜单栏
        menuBar.add(functionJMenu);
        menuBar.add(aboutItem);
        //给整个界面设置菜单栏
        this.setJMenuBar(menuBar);

        //test2

    }
}
