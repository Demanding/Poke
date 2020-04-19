package pokepai;

import lx3.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SendThread extends Thread{
    Socket sk;
    int n;
    String[] poke = new String[52];
    public SendThread(Socket sk, int n,String[] poke) {
        this.sk = sk;
        this.n = n;
        this.poke=poke;
        String c;
    }
    public void run() {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(sk.getOutputStream());
            for (int i = 0; i <= 12; i++) {
               
                out.writeUTF(poke[n*13+i]);    
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

