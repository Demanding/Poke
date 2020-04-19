package pokepai;

import java.util.logging.Level;
import java.util.logging.Logger;

public class fapaixc2 {

    StartupPoke pk = new StartupPoke();
    int n = 0;

    public fapaixc2() {

        CardBuffer cb = new CardBuffer();
        new SendCardThread(cb).start();
        new sc1(cb,1);
        new sc2(cb,2);
        new sc3(cb,3);
        new sc4(cb,4);
    }

    class CardBuffer {

        private String pai;
        private boolean isEmpty = true;
        private int order = 0;

        public CardBuffer() {
        }

        synchronized void put(String b) {
            while (!this.isEmpty) {
                try {
                    this.wait();
                } catch (InterruptedException ex) {

                }
            }
            this.pai = b;
            this.isEmpty = false;
            this.notifyAll();
        }

        synchronized String get(int order) {
            while (this.isEmpty || (this.order != order)) {
                try {
                    this.wait();
                } catch (InterruptedException ex) {

                }
            }
            this.isEmpty = true;
            this.notifyAll();
            this.order = (this.order + 1) % 4;
            return this.pai;
        }
    }

    class SendCardThread extends Thread {

        private CardBuffer cb;

        public SendCardThread(CardBuffer cb) {
            this.cb = cb;
            this.setPriority(10);           
        }

        public void run() {
            for (int i = 0; i < 53; i++) {
                this.cb.put(pk.pk2[n]);
                n++;
            }
        }
    }

    class sc1 extends Thread {

        private CardBuffer cb;
        String a;

        public sc1(CardBuffer cb,int order) {

            this.cb = cb;
            this.cb.order = order;
            this.start();
        }

        public void run() {
            while (true) {
                a = this.cb.get(this.cb.order);
                System.out.println("sc1拿到第"+n+"张牌，是" + a);              
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(fapaixc2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class sc2 extends Thread {

        private CardBuffer cb;
        String a;

        public sc2(CardBuffer cb,int order) {

            this.cb = cb;
            this.cb.order = order;
            this.start();
        }

        public void run() {
            while (true) {
                a = this.cb.get(this.cb.order);
                System.out.println("sc2拿到第"+n+"张牌，是" + a);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(fapaixc2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class sc3 extends Thread {

        private CardBuffer cb;
        String a;

        public sc3(CardBuffer cb,int order) {

            this.cb = cb;
            this.cb.order = order;
            this.start();
        }

        public void run() {
            while (true) {
                a = this.cb.get(this.cb.order);
                System.out.println("sc3拿到第"+n+"张牌，是" + a);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(fapaixc2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class sc4 extends Thread {

        private CardBuffer cb;
        String a;

        public sc4(CardBuffer cb,int order) {

            this.cb = cb;
            this.cb.order = order;
            this.start();
        }

        public void run() {
            while (true) {
                a = this.cb.get(this.cb.order);
                System.out.println("sc4拿到第"+n+"张牌，是" + a);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(fapaixc2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

public static void main(String[] args) {

        fapaixc2 fx2 = new fapaixc2();

    }
}
