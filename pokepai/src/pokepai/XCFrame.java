package pokepai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XCFrame extends JFrame implements ActionListener, Runnable {

    JButton[] buttons = new JButton[13];
    StartupPoke pk = new StartupPoke();
    JLayeredPane jp = this.getLayeredPane();
    String[] cards = null;
    JButton stop = new JButton("暂停");
    JButton go = new JButton("开始");
    int flag = 1;
    int tiaojian = 0;
    public static Thread t;
    public static Thread mainThread;
    int sent = 0;
    int globalI = 0;

    public XCFrame() {

        this.setLayout(null);
        Container c = this.getContentPane();

        this.setSize(800, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel pic = new JLabel(new ImageIcon("img/牌桌图片.jpg"));
        pic.setBounds(0, 0, 800, 600);
        c.add(pic);
        initCards(pk.puke1);
        go.addActionListener(this);
           
        go.setSize(80, 60);
            go.setLocation(10, 10);
            go.setVisible(true);
            jp.add(go);

            stop.setSize(80, 60);
            stop.setLocation(100, 10);
            stop.setVisible(true);
            stop.addActionListener(
                    (ActionEvent e) -> {
                        if (mainThread != null) {
                            mainThread.interrupt();
                        }
                    }
            );
            jp.add(stop);

            this.setVisible(true);

        }

        void getPai
        
            () {
        //StartupPoke pk = new StartupPoke();

        pk.生成牌();
            pk.打乱牌();
            pk.发三人牌();
            pk.排序牌(pk.puke1);
            pk.排序牌(pk.puke2);
            pk.排序牌(pk.puke3);
            pk.排序牌(pk.puke4);
            pk.显示牌(pk.puke1);
            System.out.println("---");
            pk.显示牌(pk.puke2);
            System.out.println("---");
            pk.显示牌(pk.puke3);
            System.out.println("---");
            pk.显示牌(pk.puke4);

        }

    

    public void initCards(String[] c) {
        cards = c;
        for (int i = 0; i < cards.length; i++) {
            ImageIcon icon = new ImageIcon("img/" + cards[i] + ".jpg"); // icon类
            buttons[i] = new JButton(icon);// new 按钮
            buttons[i].setSize(134, 201); // 大小
            buttons[i].setLocation(10 + i * 30, 280); // 位置
            buttons[i].addActionListener(this); //添加ActionListener
            jp.add(buttons[i]); // 往容器添加按钮
            jp.setLayer(buttons[i], i);
            buttons[i].setVisible(false);

        }
    }

    public void run() {
        while (flag == 1) {
            int i = globalI;
            for (; i < 13; i++) {
                buttons[i].setVisible(true);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(XCFrame.class.getName()).log(Level.SEVERE, null, ex);
                    flag = 0;
                    globalI = i;
                    break;
                }
                if (i == 12) {
                    for (int j = 0; j < 13; j++) {
                        buttons[j].setVisible(false);
                    }
                    globalI = 0;
                }
            }
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        XCFrame xcframe = new XCFrame();
        Thread t = new Thread(xcframe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == go) {
            flag = 1;
            mainThread = new Thread(this);
            mainThread.start();
        }
    }
}
