package com.lsd.ui.utils;

import javax.swing.*;
import java.awt.*;

//工具类（静态方法）
public class DialogUtils {
    public static void showJDialog(JFrame frame, String title) {
        //显示对话框,提取为公共方法

            JDialog jd = new JDialog(frame, "提示", true); // 设置标题 & 模态
            jd.setSize(300, 150);
            jd.setAlwaysOnTop(true);
            jd.setLocationRelativeTo(frame); // 相对主窗口居中

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

    }
}
