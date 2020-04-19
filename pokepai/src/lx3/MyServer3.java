package lx3;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyServer3{
    public static void main(String[] args) {
        int n=1;
        try {
            ServerSocket ser = new ServerSocket(7777);
            System.out.println("服务器在等待客户连接");
            System.out.println(InetAddress.getLocalHost().getHostAddress());
             while (true) {
                Socket sc = ser.accept();
                System.out.println("已经有第" + n + "个客户连接到服务器" + sc.getInetAddress().toString());
                SendThread xc=new SendThread(sc,n);
                xc.start();
                n++;
             }
        } catch (IOException ex) {
            Logger.getLogger(MyServer3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
