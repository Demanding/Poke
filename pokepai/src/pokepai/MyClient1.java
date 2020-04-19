
package pokepai;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyClient1 {
    public static void main(String[] args) {
        try {
            Socket Client = new Socket("169.254.172.243", 4567);
            String s = "我爱你！";        
            DataOutputStream out = new DataOutputStream(Client.getOutputStream());
            out.writeUTF(s);
            out.close();
            Client.close();
        } catch (IOException ex) {
            System.out.println("连接失败！");
        }
    }
}
