package com.lsd.ui;

import com.lsd.ui.utils.JsonUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
//import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import static com.lsd.ui.LoginJFrame.list;
import static com.lsd.ui.LoginJFrame.nowName;
//游戏界面
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    LoginJFrame loginJFrame =null;//loginFrame对象，唯一
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == replayItem){ //重玩
            rePlay();
        }else if(source == exitItem){ //退出
            System.exit(0);
        } else if (source ==reLoginItem) {        //跳转到登录页面
            System.out.println("重写登录");
            this.setVisible(false);
            //new LoginJFrame();
            if(loginJFrame == null){
                try {
                    loginJFrame = new LoginJFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                loginJFrame.setVisible(true);
            }
        } else if (source ==wxItem) { //弹出对话框
            System.out.println("公众号");

            JLabel jLabel = new JLabel(new ImageIcon("image\\damie.jpg"));
            jLabel.setBounds(0, 0, 300, 300);
            showDialog1(jLabel,100,100,true,"wx公众号");


        } else if (source == howToPlayItem) {
            String str = """
        数字华容道，或者叫做15 puzzle、数字滑块拼图等，是比较常见的一款经典拼图游戏。
        1. 记住细节：按住a键看全图，从上到下，从左到右，分为1,2,3,...14,15，每个图片代表一个数字
        2. 按行还原：先还原第1行，再第2行，最后两行分块处理。
        3. 固定块顺序：不要破坏已排好位置的块；
           图片1,2,5,6随便排。
        4. 横着玩法：将图片3移动到4的位置，同时把图片4放到图片3后面，转一圈即可归位，7,8同理。
        5. 竖着玩法：9和13同理，10和14同理。
        6. 最后3张图转一圈。
        """;

            JTextArea textArea = new JTextArea(str);

            textArea.setLineWrap(true); // 自动换行
            textArea.setWrapStyleWord(true); // 单词边界换行
            textArea.setEditable(false); // 只读
            textArea.setOpaque(false); // 背景透明（可选）

            textArea.setFont(new Font("微软雅黑", Font.BOLD, 16));

            showDialog1(textArea,750,300,false,"提示");

        } else if (source == girlItem) { //加载其他图片
            Random r= new Random();//随机path
             path ="image\\girl\\girl"+r.nextInt(13)+"\\";
            //重新加载
            rePlay();
        } else if (source == animalItem) {
            Random r= new Random();//随机path
            path ="image\\animal\\animal"+r.nextInt(8)+"\\";
            rePlay();
        } else if (source== sportItem) {
            Random r= new Random();
            //path这里有路径错误的情况bug，空白
            path ="image\\sport\\sport"+r.nextInt(10)+"\\";
            rePlay();
        }
    }
    //重新加载
    private void rePlay()
    {
        count =0;
        initData();
        initImage();
    }
    private void showDialog1(JComponent comp,int x,int y,boolean flag,String str1)
    {
        JDialog jd = new JDialog();
        jd.getContentPane().add(comp);
        jd.setSize(x, y);

        jd.setAlwaysOnTop(true);
        jd.setLocationRelativeTo(null);
        if(flag){
            jd.setModal(true);//不关闭不能操作
            }
        //jd.setBackground(Color.yellow);//错误
        jd.getContentPane().setBackground(Color.gray);

        jd.setTitle(str1);
        jd.setVisible(true);
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
    JMenuItem wxItem= new JMenuItem("公众号");
    JMenuItem howToPlayItem = new JMenuItem("诀窍");

    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImage();
        //可见
        this.setVisible(true);
        //shift+ enter 中途换行
    }
    //模拟手动人工打乱data，确保有解
    private void initData() {
        // 初始顺序拼图：1 ~ 15，0 代表空格
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (num <= 15) {
                    data[i][j] = num++;
                } else {
                    data[i][j] = 0; // 空格
                    x = i;
                    y = j;
                }
            }
        }

        // 通过合法滑动打乱拼图
        Random random = new Random();
        for (int i = 0; i < 1000; i++) { // 随机滑动1000步
            int dir = random.nextInt(4);
            int newX = x, newY = y;

            switch (dir) {
                case 0: newX = x - 1; break; // 上
                case 1: newX = x + 1; break; // 下
                case 2: newY = y - 1; break; // 左
                case 3: newY = y + 1; break; // 右
            }

            // 判断是否越界
            if (newX >= 0 && newX < 4 && newY >= 0 && newY < 4) {
                // 交换空格和目标位置，模拟打乱data，确保有解
                data[x][y] = data[newX][newY];
                data[newX][newY] = 0;
                x = newX;
                y = newY;
            }
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
        stepCount.setFont(new Font("微软雅黑", Font.BOLD, 16));
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        JLabel jName = new JLabel("当前玩家:"+nowName);//LoginJFrame.nowName
        jName.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jName.setBounds(50,50,100,20);
        this.getContentPane().add(jName);
        if(LoginJFrame.currentUser != null){
            int nowBestScore = LoginJFrame.currentUser.getScore();
            //max
            JLabel bestScores = new JLabel("最佳得分:"+(Math.max(nowBestScore, count)));
            bestScores.setFont(new Font("微软雅黑", Font.BOLD, 16));
            bestScores.setBounds(150,50,100,20);
            this.getContentPane().add(bestScores);
            //json,成功才show
            for (User user : list) {
                if (nowName.equals(user.getUserName())) {
                    user.setScore(nowBestScore);
                }
            }
            JsonUtils.saveUsersToJson(list,"users.json");
        }


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
        this.setTitle("拼图单机版v2.0.0");
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
        JMenu showPlayItem = new JMenu("玩法介绍");
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

        showPlayItem.add(howToPlayItem);
        howToPlayItem.addActionListener(this);

        //菜单加到菜单栏
        menuBar.add(functionJMenu);
        menuBar.add(showPlayItem);
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
        if(victory())
            return;//结束
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
