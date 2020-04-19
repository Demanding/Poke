package pokepai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadPicture extends JFrame implements Runnable {

    static InFrame[] frames = new InFrame[4];
    private static JDesktopPane desktopPane = null;
    private static JInternalFrame internalFrame = null;

    static StartupPoke pk = new StartupPoke();
    String[] AllPoke = new String[52];
    Boolean flag = true;
    int n = -1;
    int sort = -1;
    int exit = 0;

    public LoadPicture(String s) {
        super(s);
        this.setLayout(null);
        Container c1 = this.getContentPane();
        c1.setLayout(new GridLayout(2, 2));
        this.setSize(1600, 1200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AllPoke = pk.pk2;
        frames[0] = new InFrame();
        frames[1] = new InFrame();
        frames[2] = new InFrame();
        frames[3] = new InFrame();
        c1.add(frames[0]);
        c1.add(frames[1]);
        c1.add(frames[2]);
        c1.add(frames[3]);
        frames[0].setSize(this.getWidth() / 2, this.getHeight() / 2);
        frames[1].setSize(this.getWidth() / 2, this.getHeight() / 2);
        frames[2].setSize(this.getWidth() / 2, this.getHeight() / 2);
        frames[3].setSize(this.getWidth() / 2, this.getHeight() / 2);

    }

    public synchronized void fuwuqi() throws InterruptedException {

        for (int i = 0; i < 52; i++) {
            while (flag == false) {
                this.wait();
            }
            System.out.println("现在发第" + (n + 1) + "张牌,是" + AllPoke[n + 1]);
            n++;
            sort = (sort + 1) % 4;
            flag = false;
            Thread.sleep(100);
            this.notifyAll();
        }
        exit = 1;
    }

    public synchronized void sc1() throws InterruptedException {
        for (int i = 0; i < 13; i++) {
            while (sort != 0 || flag == true) {
                this.wait();
            }
            System.out.println("1");
            frames[0].initCards(AllPoke[n]);
            pk.puke1[i] = AllPoke[n];
            Thread.sleep(100);
            flag = true;

            this.notifyAll();

        }
    }

    public synchronized void sc2() throws InterruptedException {
        for (int i = 0; i < 13; i++) {
            while (sort != 1 || flag == true) {
                this.wait();
            }
            frames[1].initCards(AllPoke[n]);
            pk.puke2[i] = AllPoke[n];
            System.out.println("2");
            Thread.sleep(100);
            flag = true;

            this.notifyAll();

        }

    }

    public synchronized void sc3() throws InterruptedException {
        for (int i = 0; i < 13; i++) {
            while (sort != 2 || flag == true) {
                this.wait();
            }
            frames[2].initCards(AllPoke[n]);
            pk.puke3[i] = AllPoke[n];
            System.out.println("3");
            Thread.sleep(100);
            flag = true;

            this.notifyAll();

        }

    }

    public synchronized void sc4() throws InterruptedException {
        for (int i = 0; i < 13; i++) {
            while (sort != 3 || flag == true) {
                this.wait();
            }
            frames[3].initCards(AllPoke[n]);
            pk.puke4[i] = AllPoke[n];
            System.out.println("4");
            Thread.sleep(100);
            flag = true;

            this.notifyAll();

        }

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int flag = 0;
        LoadPicture p1 = new LoadPicture("扑克牌");
        Thread t0 = new Thread(p1, "fuwuqi");
        Thread t1 = new Thread(p1, "sc1");
        Thread t2 = new Thread(p1, "sc2");
        Thread t3 = new Thread(p1, "sc3");
        Thread t4 = new Thread(p1, "sc4");
        t0.setPriority(Thread.MAX_PRIORITY);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {  
            t0.join();  
            t1.join();
            t2.join(); 
            t3.join(); 
            t4.join(); 
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } 
  
        System.out.println("run next process.");  
        frames[0].SortCards(pk.puke1);
        frames[1].SortCards(pk.puke2);
        frames[2].SortCards(pk.puke3);
        frames[3].SortCards(pk.puke4);
        
    }

    @Override
    public void run() {
        try {
            if (Thread.currentThread().getName().equals("fuwuqi")) {
                fuwuqi();
            } else if (Thread.currentThread().getName().equals("sc1")) {
                sc1();
            } else if (Thread.currentThread().getName().equals("sc2")) {
                sc2();
            } else if (Thread.currentThread().getName().equals("sc3")) {
                sc3();
            } else if (Thread.currentThread().getName().equals("sc4")) {
                sc4();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

