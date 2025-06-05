package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//way1
public class MyActionListener implements ActionListener {
    //重写了父类的内容，必执行actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("按下了");
    }
}
