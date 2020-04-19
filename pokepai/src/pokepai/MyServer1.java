
package pokepai;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer1 {
    public static void main(String[] args) {
        try {
            ServerSocket Server = new ServerSocket(4567);
            System.out.println("服务器的IP地址是：");
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println("正在等待连接！");
            Socket Client = Server.accept();
            System.out.println("连接成功！");
            DataInputStream in = new DataInputStream(Client.getInputStream());
            String s =in.readUTF();
            System.out.println(s);
            in.close();
            Client.close();
        } catch (IOException ex) {
            System.out.println("连接失败");
        }
    }
}
