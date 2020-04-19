package pokepai;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokerFrame extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[13];
    JLayeredPane layeredPane = this.getLayeredPane();
    String cards = null;
    JButton cardButton = new JButton("出牌");
    int flag = 0;
    JButton choose = null;
    int paishu = 13;
    int k = 0;
    int cengshu = 0;
    JButton stop = new JButton("暂停");
    TreeMap<Integer, String> treemap1 = new TreeMap<Integer, String>();
    String value = null;
    Iterator iter = null;
    int count = 0;
    int w, h;
    int i = 0;

    public PokerFrame() {
        this.setLayout(null);
        Container con = this.getContentPane();
        layeredPane = this.getLayeredPane();
        w = (int) getToolkit().getScreenSize().getWidth() / 2;
        h = (int) getToolkit().getScreenSize().getHeight() / 2;
        this.setSize(w, h);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel pic = new JLabel(new ImageIcon("img/牌桌图片.jpg"));
        pic.setBounds(0, 0, 800, 600);
        con.add(pic);
        this.setVisible(true);
        this.setAlwaysOnTop(rootPaneCheckingEnabled);
        cardButton = new JButton("出牌");
        cardButton.setSize(100, 100);
        cardButton.setLocation(200, 150);
        con.add(cardButton);
        cardButton.addActionListener(this);
        cardButton.setVisible(false);
    }

    public static void main(String[] args) {
        PokerFrame pk = new PokerFrame();
        try {
            Socket st = new Socket("172.17.22.8", 7777);
            for (int i = 0; i < 13; i++) {
                DataInputStream in = new DataInputStream(st.getInputStream());
                String msg = in.readUTF();
                System.out.println("收到了服务器的" + msg);
                pk.initCards(msg);
            }

        } catch (IOException ex) {
            System.out.println("客户未能连接到服务器");
            //Logger.getLogger(MyClient1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initCards(String c) {
        // cards = c;
        ImageIcon icon = new ImageIcon("img/" + c + ".jpg"); // icon类
        buttons[k] = new JButton(icon);// new 按钮
        buttons[k].setSize(134, 201); // 大小
        buttons[k].setLocation(10 + k * 30, 100); // 位置
        buttons[k].addActionListener(this); //添加ActionListener
        layeredPane.add(buttons[k]); // 往容器添加按钮
        layeredPane.setLayer(buttons[k], k);
        buttons[k].setVisible(true);
        k++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (flag == 1) {
            if (choose == btn) {
                choose.setLocation(choose.getX(), choose.getY() + 30);//下移
                flag = 0;
                choose = null;
                cardButton.setVisible(false);
            } else if (btn == cardButton) {      //chupai

                choose.setLocation(480, 100);
                layeredPane.setLayer(choose, cengshu);
                choose.removeActionListener(this);
                cengshu++;
                choose = null;
                flag = 0;
                paishu--;
                for (; i < paishu; i++) {
                    buttons[i] = buttons[i + 1];
                    buttons[i + 1].setLocation(10 + i * 30, 280);
                    layeredPane.setLayer(buttons[i], i);
                };
            } else {
                choose.setLocation(choose.getX(), choose.getY() + 30);//下移
                choose = btn;
                choose.setLocation(choose.getX(), choose.getY() - 30);//上移
                flag = 1;
            }
        } else if (flag == 0) {
            for (i = 0; i < 13; i++) {
                if (btn == buttons[i]) {
                    choose = btn;
                    choose.setLocation(choose.getX(), choose.getY() - 30);
                    flag = 1;
                    cardButton.setVisible(true);
                    break;
                }
            }
        }
    }
}
