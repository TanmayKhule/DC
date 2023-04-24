import java.net.*;
import java.io.*;

public class gr2comm2 {
    public static void main(String[] p) {
        try {
            byte b[] = new byte[100];
            char a[] = new char[100];
            Socket s1 = new Socket("186.168.1.160", 6000);
            System.out.print("\n Second Group \n");
            InputStream in = s1.getInputStream();
            in.read(b, 0, 99);
            System.out.print("\n Message Received ");
            for (int i = 0; i < b.length; i++)
                System.out.print((char) b[i]);
            s1.close();
        } catch (Exception e) {
            System.out.print("\n Sorry ! Connection is Closed!");
        }
    }
}