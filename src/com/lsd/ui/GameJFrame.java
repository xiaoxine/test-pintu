package com.lsd.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static com.lsd.ui.LoginJFrame.nowName;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == replayItem){
            count =0;
             initData();
             initImage();

        }else if(source == exitItem){
            System.exit(0);
        } else if (source ==reLoginItem) {
            System.out.println("xx");
            this.setVisible(false);
            new LoginJFrame();
            //登录页面

        } else if (source ==wxItem) {
            System.out.println("wx");
            JDialog jd = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image\\damie.jpg"));
            jLabel.setBounds(0, 0, 300, 300);
            jd.getContentPane().add(jLabel);
            jd.setSize(300, 300);
            jd.setAlwaysOnTop(true);
            jd.setLocationRelativeTo(null);
            jd.setModal(true);//不关闭不能操作
            jd.setVisible(true);

        } else if (source == girlItem) {
            System.out.println("girl");
            //随机
            Random r= new Random();
            //path
             path ="image\\girl\\girl"+r.nextInt(13)+"\\";
            //重新加载
            count =0;
            initData();
            initImage();
        } else if (source == animalItem) {
            //随机
            Random r= new Random();
            //path
            path ="image\\animal\\animal"+r.nextInt(8)+"\\";
            //重新加载
            count =0;
            initData();
            initImage();
        } else if (source== sportItem) {
            //随机
            Random r= new Random();
            //path有路径错误的情况
            path ="image\\sport\\sport"+r.nextInt(10)+"\\";
            //重新加载
            count =0;
            initData();
            initImage();
        }
    }

    //二维数组,管理数据，加载图片需用到，扩大范围
    int[][] data =new int[4][4];
    //记录空白的二维数组
    int x =0;
    int y =0;
    //路径
    String path ="image\\animal\\animal3\\";

    int[][] win = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};//胜利，用于比较

    int count=0;//计数，步数

    //条目
    JMenuItem exitItem = new JMenuItem("退出游戏");
    JMenuItem replayItem = new JMenuItem("重玩");
    JMenuItem reLoginItem = new JMenuItem("登录");
    JMenuItem girlItem = new JMenuItem("女孩");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    //
    JMenuItem wxItem= new JMenuItem("公众号");

    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //图片
        initImage();
        //可见
        this.setVisible(true);
        //shift+ enter 中途换行
    }

    private void initData() {
        int[] tempArray ={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random random = new Random();
        //打乱
        for (int i = 0; i < tempArray.length; i++) {
            int index = random.nextInt(tempArray.length);
            //交换
            int temp = tempArray[i];
            tempArray[i] = tempArray[index];
            tempArray[index] = temp;
        }
//old
/*        int index =0;
        //赋值二维数组
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = tempArray[index];//或index=0，index++，，， tempArray[i*4+j]
                index++;
            }
        }*/
        for (int i = 0; i < tempArray.length; i++) {
            if(tempArray[i]==0){
                x=i/4;
                y=i%4;
            }
            data[i/4][i%4]=tempArray[i];

        }
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

        //清空已出现
        this.getContentPane().removeAll();
        if(victory()) {
            JLabel winpic = new JLabel(new ImageIcon("image\\win.png"));
            winpic.setBounds(203,283,197,73);
            this.getContentPane().add(winpic);

        }
        JLabel stepCount = new JLabel("步数:"+count);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        JLabel jName = new JLabel("当前玩家:"+nowName);//LoginJFrame.nowName
        jName.setBounds(50,50,100,20);
        this.getContentPane().add(jName);

/*        JLabel jNum = new JLabel("当前排名:");//LoginJFrame.nowName
        jNum.setBounds(50,50,100,20);
        this.getContentPane().add(jNum);*/

        //int num =1; //old
        //外循环，把内循环重复4次
        for (int i = 0; i < 4; i++) {
            //内循环，添加4张图片
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //创建一个label对象，管理容器加图片
                JLabel Jlabel = new JLabel(new ImageIcon(path+num+".jpg"));
                Jlabel.setBounds(105*j+83, 105*i+134, 105, 105);//偏移中间
                //设置图片的边框
                //Jlabel.setBorder(new LineBorder(Color.yellow));
                Jlabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //获取隐藏容器panel再添加
                this.getContentPane().add(Jlabel);
                //加载下一张
                //num++; //old
            }
        }
        //D:\java-project\test-pintu
        //System.out.println("当前工作目录是: " + System.getProperty("user.dir"));

        //背景，先图片，后背景图D:\java-project\test-pintu\image\background.png
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40,40,508,560);
        //隐藏容器添加JLabel 对象
        this.getContentPane().add(background);

        //刷新一下
        this.getContentPane().repaint();
    }

    private void initJFrame() {
        //标题
        this.setTitle("拼图单机版v1.0.0");
        //大小
        this.setSize(628,668);
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

        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }
    private void initJMenuBar() {
        //初始化菜单栏
        JMenuBar menuBar = new JMenuBar();
        //菜单
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutItem = new JMenu("关于我们");

        JMenu changeMenu = new JMenu("change");
        changeMenu.add(girlItem);
        changeMenu.add(animalItem);
        changeMenu.add(sportItem);
        //
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(exitItem);
        functionJMenu.add(changeMenu);//更换图片
        //绑定监听事件监听
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        exitItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);


        //条目加到选项
        aboutItem.add(wxItem);
        wxItem.addActionListener(this);

        //菜单加到菜单栏
        menuBar.add(functionJMenu);
        menuBar.add(aboutItem);
        //给整个界面设置菜单栏
        this.setJMenuBar(menuBar);

        //test2

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松开
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 65) {
            //清空已出现
            this.getContentPane().removeAll();

            JLabel allPic = new JLabel(new ImageIcon(path+"all.jpg"));
            allPic.setBounds(83,134,420,420);
            //allPic.setBounds(40,40,508,560);
            this.getContentPane().add(allPic);

            //背景，先图片，后背景图D:\java-project\test-pintu\image\background.png
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40,40,508,560);
            //隐藏容器添加JLabel 对象
            this.getContentPane().add(background);
            //刷新一下
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory())
            return;//结束

        //左37，上38，右39，下40
        int code = e.getKeyCode();
        System.out.println(code);
        if(code==39){
            if(y==0){
                return;
            }
            System.out.println("右移动");
            data[x][y] =data[x][y-1];
            data[x][y-1] =0;
            y--;
            //计数器+1
            count++;
            initImage();
        }else if(code==38){
            if(x==3){
                return;
            }
            System.out.println("图片向上移动");
            data[x][y] =data[x+1][y];
            data[x+1][y] =0;
            x++;
            //计数器+1
            count++;
            initImage();
        }else if(code==37){
            if(y==3){
                return;
            }
            System.out.println("左移动");
            data[x][y] =data[x][y+1];
            data[x][y+1] =0;
            y++;
            //计数器+1
            count++;
            initImage();
        }else if(code==40){
            if(x==0){
                return;
            }
            System.out.println("下移动");
            data[x][y] =data[x-1][y];
            data[x-1][y] =0;
            x--;
            //计数器+1
            count++;
            initImage();
        }else if(code==65){
            initImage();
        }else if(code==87){
            data = new int[][]
                    {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
            initImage();
            //bug,多个空格

        }

    }

    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j]!=win[i][j]){
                    return false;//只要有一个不一样
                }
            }
        }
        return true;//完毕
    }

}
