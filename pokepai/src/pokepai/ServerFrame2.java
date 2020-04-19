
package pokepai;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerFrame2 implements Runnable {

    JInternalFrame inf[] = new JInternalFrame[4];
    JLayeredPane jp[]=new JLayeredPane[4];
    String name[] = new String[52];
    String pm[][] = new String[4][13];
    JButton jb[][] = new JButton[4][13];
    String pName=null;
    Boolean fa = true;
    int zs = 0, xcbh = 0, pkbh = 0;

    public ServerFrame2() {
        Poke5 poke = new Poke5();
        name = poke.抽4人牌();
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 12; j++) {
                pm[i][j] = name[i * 13 + j];
            }
        }
        JFrame f = new JFrame("游戏牌");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Container c = f.getContentPane();
        c.setLayout(new GridLayout(4, 1));
        c.setVisible(true);
        for (int i = 0; i <= 3; i++) {
            inf[i] = new JInternalFrame("玩家" + (i + 1), true, true, true, true);
            inf[i].setBackground(Color.DARK_GRAY);
            inf[i].setVisible(true);
            inf[i].setLayout(null);
            jp[i] = inf[i].getLayeredPane();
            c.add(inf[i]);
        }
        f.setContentPane(c);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() {
        int index;
        try {
            if (Thread.currentThread().getName().equals("服务器")) {
                send();
            } else {
                index = Integer.parseInt(Thread.currentThread().getName());
                for (int i = 0; i <= 12; i++) {
                    receive(index);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void send() throws InterruptedException {
         for (int i = 0; i <= 12; i++) {
           for (int j = 0; j <= 3; j++) {
        while (!fa||pName!=null) {
            System.out.println("服务器进来了，fa为:"+fa+",pName为:"+pName+",服务器要等待");
            this.wait();
        }
         pName = pm[j][i];
        System.out.println("服务器发送了第" + (zs + 1) + "张牌" + pName);
        xcbh = zs % 4;
        pkbh = zs / 4;
        System.out.println("该"+xcbh+"接收"+(pkbh+1)+"张牌");
        zs++;
        Thread.sleep(100);
        fa = false;
        this.notifyAll();
    }
         }}
    public synchronized void receive(int n) throws InterruptedException {
        int j ;
        while (fa || n != xcbh) {
           System.out.println("线程"+n+"进来了,fa为:"+fa+",n为:"+n+",轮不到我");
            this.wait();
        }
        j=pkbh;
        System.out.println("玩家" + n + "收到第"+(j+1)+"张牌，"+pName);
        ImageIcon img = new ImageIcon("img/" + pName);
        jb[n][j] = new JButton(img);
        jb[n][j].setSize(134, 201);
        jb[n][j].setLocation(10 + 50 * j, 0);
        jp[n].add(jb[n][j]);
        jp[n].setLayer(jb[n][j], j+1);
        Thread.sleep(100);
        pName=null;
        fa = true;
        this.notifyAll();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerFrame2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServerFrame2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServerFrame2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ServerFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServerFrame2 sf = new ServerFrame2();
        Thread t1 = new Thread(sf, "服务器");
        Thread t2 = new Thread(sf, "0");
        Thread t3 = new Thread(sf, "1");
        Thread t4 = new Thread(sf, "2");
        Thread t5 = new Thread(sf, "3");
        t1.setPriority(1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
