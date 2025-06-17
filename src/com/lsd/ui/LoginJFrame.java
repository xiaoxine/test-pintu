package com.lsd.ui;

import com.lsd.ui.utils.DialogUtils;
import com.lsd.ui.utils.JsonUtils;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class LoginJFrame extends JFrame implements MouseListener {
    JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
    JButton jButton1 = new JButton(new ImageIcon("image\\login\\登录按钮.png"));
    JButton jButton2 = new JButton(new ImageIcon("image\\login\\注册按钮.png"));
    JButton jButton3 = new JButton(new ImageIcon("image\\login\\登录按下.png"));
    JButton jButton4 = new JButton(new ImageIcon("image\\login\\注册按下.png"));
    JTextField userNameField = new JTextField();
    JPasswordField passWordField = new JPasswordField();
    JTextField testCodeField = new JTextField();
    //只创建一个子窗口实例,防止多次创建
    GameJFrame gameJFrame = null;
    RegisterJFrame registerJFrame = null;
    //当前玩家
    static String nowName ="" ;
    String codeStr = "1234";
/*    //创建一个集合存储正确的内容
    static ArrayList<User> list = new ArrayList<>();
    // 静态代码块,只执行一次，随着类的加载而加载，做数据初始化,静态里面只能用静态
    static {
        list.add(new User("bb", "bb".toCharArray()));
        list.add(new User("aa", "aa".toCharArray()));
    }*/
    static ArrayList<User> list = new ArrayList<>();
    //static {list.add(new User("bb", "bb"));}


    //构造方法，加载配置
    public LoginJFrame() throws IOException {
        File file = new File("users.json");
        if(!file.exists()){
            try {//在文件系统上执行操作有很多可能会失败的情况,所以要抛出异常
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JsonUtils.saveUsersToJson(new ArrayList<>(),"users.json");
        }
        list = JsonUtils.loadUsersFromJson("users.json");
        //取消默认的组件居中方式，细节
        this.setLayout(null);
        this.setTitle("登录界面");
        this.setSize(480, 430);

        background.setBounds(0, 0, 470, 400);
        //隐藏容器加JLabel对象
        this.getContentPane().add(background);
        JLabel userName = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        userName.setBounds(150, 150, 47, 17);
        JLabel passWord = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passWord.setBounds(150, 190, 32, 16);
        JLabel testCode = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        testCode.setBounds(150, 230, 56, 21);
/*        this.getContentPane().add(userName);
        this.getContentPane().add(passWord);
        this.getContentPane().add(testCode);*/
        //错误??

        background.add(userName);
        background.add(passWord);
        background.add(testCode);


        userNameField.setBounds(210, 150, 200, 20);
        passWordField.setBounds(210, 190, 200, 20);
        testCodeField.setBounds(210, 230, 100, 20);
        Random random = new Random();
        //codeStr =random.nextInt(10,99)+"ab";
        // 生成一个 4 位随机验证码（字母+数字）
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(chars.length());
            codeBuilder.append(chars.charAt(index));
        }
        codeStr = codeBuilder.toString();
        JLabel rightCode = new JLabel();
        rightCode.setText(codeStr);
        rightCode.setBounds(310, 230, 50, 20);

        jButton1.setBounds(150, 270, 128, 47);
        jButton2.setBounds(300, 270, 128, 47);
        jButton3.setBounds(150, 270, 128, 47);
        jButton4.setBounds(300, 270, 128, 47);
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
        list.clear();
        list = JsonUtils.loadUsersFromJson("users.json");

        Object source = e.getSource();//事件获取
        if (source == jButton3 || source == jButton1) {//登录
            System.out.println(userNameField.getText());
            System.out.println(passWordField.getPassword());
            String tempUser = userNameField.getText();
            String tempPass =  new String(passWordField.getPassword());//char[] 转
            if (Objects.equals(testCodeField.getText(), "")) {
                DialogUtils.showJDialog(this,"请输入验证码");
                return;
            }
            if (!testCodeField.getText().equals(codeStr)) {  //next 工具类提供验证
                DialogUtils.showJDialog(this,"验证码错误");
                return;
            }
            if (Objects.equals(tempUser, "") || tempPass.length() == 0) {
                //System.out.println("请输入用户名或密码");
                DialogUtils.showJDialog(this,"请输入用户名或密码");
                return;
            }
            boolean loginSuccess = false;
            for (User user : list) {
                if (tempUser.equals(user.getUserName())) {
                    if (tempPass.equals(user.getPassWord())) {
                        System.out.println("登录成功");
                        loginSuccess = true;
                        this.setVisible(false);
                        nowName = tempUser;
                        //new GameJFrame();
                        if(gameJFrame == null){
                            gameJFrame = new GameJFrame();
                        }else
                        {
                            gameJFrame.setVisible(true);
                        }
                        //游戏页面
                        break; // 找到匹配用户后可以退出循环
                    }
                }
            }
            if (!loginSuccess) {
                System.out.println("用户名或密码错误");
                DialogUtils.showJDialog(this,"用户名或密码错误");
            }

        } else if (source == jButton4 || source == jButton2) {//注册
            this.setVisible(false);
            //new RegisterJFrame();
            if(registerJFrame == null){
                registerJFrame = new RegisterJFrame();
            }else
            {
                registerJFrame.setVisible(true);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();//事件获取
        if (source == jButton1) {
            jButton1.setVisible(false);
            jButton3.setVisible(true);
        } else if (source == jButton2) {
            jButton2.setVisible(false);
            jButton4.setVisible(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Object source = e.getSource();//事件获取
        if (source == jButton3) {//登录
            jButton1.setVisible(true);
            jButton3.setVisible(false);


        } else if (source == jButton4) {//注册
            jButton2.setVisible(true);
            jButton4.setVisible(false);
        }

    }

/*    //显示对话框old改为工具类
    public void showJDialog(String title) {
        JDialog jd = new JDialog(this, "提示", true); // 设置标题 & 模态
        jd.setSize(300, 150);
        jd.setAlwaysOnTop(true);
        jd.setLocationRelativeTo(this); // 相对主窗口居中

        // 创建内容面板并使用布局
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 添加内边距

        // 设置字体和居中
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

        panel.add(titleLabel, BorderLayout.CENTER);

        // 添加到对话框中
        jd.setContentPane(panel);
        jd.setVisible(true);
    }*/
}
