package pokepai;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.TreeMap;

public class InFrame extends JInternalFrame implements ActionListener {

    UIManager.LookAndFeelInfo looks[];
    JLayeredPane layeredPane = null;
    JButton cardButton = new JButton("出牌");
    StartupPoke pk = new StartupPoke();
    String[] cards = null;
    JButton[] buttons = new JButton[13];

    int flag = 0;
    JButton choose = null;
    int paishu = 13;
    int i = 0;
    int k = 0;
    int cengshu = 0;
    JButton stop = new JButton("暂停");
    TreeMap<Integer, String> treemap1 = new TreeMap<Integer, String>();
    String value = null;
    Iterator iter = null;
    int count = 0;

    public InFrame() {
        super("InFrame ");

        /*this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        Container con = this.getContentPane();
        layeredPane = this.getLayeredPane();
        
        ImageIcon background = new ImageIcon("img/牌桌图片.jpg");
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.setLayout(null);

        cardButton = new JButton("出牌");
        cardButton.setSize(100, 100);
        cardButton.setLocation(200, 150);
        con.add(cardButton);
        cardButton.addActionListener(this);
        cardButton.setVisible(false);

        stop = new JButton("暂停");
        stop.setSize(100, 100);
        cardButton.setLocation(200, 190);
        con.add(stop);
        stop.addActionListener(this);
        stop.setVisible(true);

        this.setVisible(true);

    }

    public void initCards(String c) {

//cards = c;
        ImageIcon icon = new ImageIcon("img/" + c + ".jpg"); // icon类
        buttons[k] = new JButton(icon);// new 按钮
        buttons[k].setSize(134, 201); // 大小
        buttons[k].setLocation(10 + k * 30, 280); // 位置
        buttons[k].addActionListener(this); //添加ActionListener
        layeredPane.add(buttons[k]); // 往容器添加按钮
        layeredPane.setLayer(buttons[k], k);
        k = k + 1;
        /*try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                System.out.println("!!");
                Logger.getLogger(InFrame.class.getName()).log(Level.SEVERE, null, ex);
            }*/

    }

    public void SortCards(String[] c) {
        cards = c;
        //TreeMap<Integer, String> treemap1 = new TreeMap<Integer, String>();
        for (int i = 0; i < 13; i++) {
            treemap1.put((Integer) pk.hashmap.get(cards[i]), cards[i]);
        }
        iter = treemap1.keySet().iterator();

        while (iter.hasNext()) {
            //it.next()得到的是key，tm.get(key)得到obj  
            cards[count] = (treemap1.get(iter.next()));
           count++;
       }
        
        for (int i = 0; i < 13; i++) {
            if (buttons[i] != null) {               
                layeredPane.remove(buttons[i]);
            }
            ImageIcon icon = new ImageIcon("img/" + cards[i] + ".jpg"); // icon类
            buttons[i] = new JButton(icon);// new 按钮
            buttons[i].setSize(134, 201); // 大小
            buttons[i].setLocation(10 + i * 30, 280); // 位置
            buttons[i].addActionListener(this); //添加ActionListener
            layeredPane.add(buttons[i]); // 往容器添加按钮
            layeredPane.setLayer(buttons[i], i);
        }
    }


    /*void getPai() {
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

    }*/
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

    public static void main(String[] args) {

        InFrame myframe = new InFrame();

    }
}
