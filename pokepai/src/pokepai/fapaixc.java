package pokepai;

/**
 *
 * @author Administrator
 */


public class fapaixc implements Runnable {

    StartupPoke pk = new StartupPoke();
    Boolean flag = true;
    int n = 1;
    String[] fuwuqi = new String[52];
    String[] xc1 = new String[13];
    String[] xc2 = new String[13];
    String[] xc3 = new String[13];
    int sort =-1;

    public fapaixc() {
        fuwuqi = pk.pk2;
    }

    public synchronized void fuwuqi() throws InterruptedException {

        for (int i = 0; i < 52; i++) {
            while (flag == false) {
                this.wait();
            }
            System.out.println("现在发第" + n + "张牌,是" + fuwuqi[n - 1]);
            n++;
            sort=(sort+1)%4;
            flag = false;
            Thread.sleep(500);
            this.notifyAll();
        }

    }

  

    public synchronized void sc1() throws InterruptedException {
        for (int i = 0; i < 13; i++) {
            while (sort != 0||flag == true) {
                this.wait();
            }
                System.out.println("sc1拿到第" + --n + "张牌,是" + fuwuqi[n - 1]);
                n++;
                Thread.sleep(500);
                flag = true;
                
                this.notifyAll();
          
                
            } 
        }


    public synchronized void sc2() throws InterruptedException {
       for (int i = 0; i < 13; i++) {
            while (sort != 1||flag == true) {
                this.wait();
            }
                System.out.println("sc2拿到第" + --n + "张牌,是" + fuwuqi[n - 1]);
                n++;
                Thread.sleep(500);
                flag = true;
   
                this.notifyAll();
                
            } 

    }

    public synchronized void sc3() throws InterruptedException {
        for (int i = 0; i < 13; i++) {
           while (sort != 2||flag == true) {
                this.wait();
            }
                System.out.println("sc3拿到第" + --n + "张牌,是" + fuwuqi[n - 1]);
                n++;
                Thread.sleep(500);
                flag = true;

                this.notifyAll();
                
            } 

    }

    public synchronized void sc4() throws InterruptedException {
        for (int i = 0; i < 13; i++) {
            while (sort != 3||flag == true) {
                this.wait();
            }
                System.out.println("sc4拿到第" + --n + "张牌,是" + fuwuqi[n - 1]);
                n++;
                Thread.sleep(500);
                flag = true;

                this.notifyAll();
                
            } 

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

    public static void main(String[] args) {
        fapaixc xc = new fapaixc();
        Thread t0 = new Thread(xc, "fuwuqi");
        Thread t1 = new Thread(xc, "sc1");
        Thread t2 = new Thread(xc, "sc2");
        Thread t3 = new Thread(xc, "sc3");
        Thread t4 = new Thread(xc, "sc4");
        t0.setPriority(Thread.MAX_PRIORITY);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
