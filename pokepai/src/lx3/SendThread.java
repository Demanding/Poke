package lx3;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SendThread extends Thread{
    Socket sk;
    int n;
    public SendThread(Socket sk, int n) {
        this.sk = sk;
        this.n = n;
    }
    public void run() {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(sk.getOutputStream());
            for (int i = 0; i <= 50; i++) {
                out.writeUTF("服务器给" + n + "客户发送的" + i);
                out.flush();
                    Thread.sleep(200);
            }  
        } catch (IOException ex) {
            Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
                sk.close();
            } catch (IOException ex) {
                Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

