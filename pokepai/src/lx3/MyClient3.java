package lx3;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyClient3{
    public static void main(String[] args) {
        try {
            Socket st=new Socket("169.254.172.243",7777);
            for(int i=0;i<=50;i++){
            DataInputStream in=new DataInputStream(st.getInputStream());
            String msg=in.readUTF();
            System.out.println("客户收到服务器信息,"+msg);}
        } catch (IOException ex) {
            System.out.println("客户未能连接到服务器");
            //Logger.getLogger(MyClient1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

