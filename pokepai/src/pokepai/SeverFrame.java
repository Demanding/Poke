package pokepai;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class SeverFrame extends JFrame implements Runnable {

    static SendThread[] st = new SendThread[4];
    StartupPoke pk = new StartupPoke();

    public SeverFrame() {
        JFrame jf = new JFrame("服务器");
        Container c = jf.getContentPane();
        c.setLayout(new BorderLayout());
        jf.setSize(400, 300);
        jf.setLocation(400, 200);
        jf.setVisible(true);
        JPanel jp = new JPanel();
        JTextArea jta = new JTextArea(10, 15);
        jta.setTabSize(4);
        jta.setFont(new Font("标楷体", Font.BOLD, 16));
        jta.setLineWrap(true);// 激活自动换行功能
        jta.setWrapStyleWord(true);// 激活断行不断字功能
        jp.add(jta);
        c.add(jp);
        int n = 0;
         try {
         ServerSocket ser = new ServerSocket(7777);
            
         jta.append("服务器在等待客户连接\r\n");
         jta.append(InetAddress.getLocalHost().getHostAddress()+"\r\n");
         while (true) {
         Socket sc = ser.accept();
         jta.append("已经有第" + (n+1) + "个客户连接到服务器" + sc.getInetAddress().toString()+"\r\n");
         //String[] p;
          /*if(n==1){
         p=pk.puke1;
         }
         else if(n==2){
         p=pk.puke2;
         }
         else if(n==3){
         p=pk.puke3;
         }
         else{
         p=pk.puke4;
         }*/
         SendThread xc = new SendThread(sc,n,pk.pk2);
         st[n]=xc;
         n++;
         if (n > 3) {
         for(int i=0;i<4;i++)
         st[i].start();
         break;
         }
         }
         } catch (IOException ex) {

         }
        
        
    }

    public static void main(String[] args) {
        new SeverFrame();
        /*int n = 0;
         try {
         ServerSocket ser = new ServerSocket(8888);
            
         System.out.println("服务器在等待客户连接");
         System.out.println(InetAddress.getLocalHost().getHostAddress());
         while (true) {
         Socket sc = ser.accept();
         System.out.println("已经有第" + (n+1) + "个客户连接到服务器" + sc.getInetAddress().toString());
         //String[] p;
          if(n==1){
         p=pk.puke1;
         }
         else if(n==2){
         p=pk.puke2;
         }
         else if(n==3){
         p=pk.puke3;
         }
         else{
         p=pk.puke4;
         }
         SendThread xc = new SendThread(sc,n,pk.pk2);
         st[n]=xc;
         n++;
         if (n > 3) {
         for(int i=0;i<4;i++)
         st[i].start();
         break;
         }
         }
         } catch (IOException ex) {

         }*/
    }

    @Override
    public void run() {

    }
}
