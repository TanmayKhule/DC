// GROUP SERVER
import java.net.*;
import java.io.*;
public class grcommser1
{
 static ServerSocket ss1;
 static ServerSocket ss2;
 static ServerSocket ss3;
 static ServerSocket ss4;
 static Socket s1;
 static Socket s2;
 static Socket s3;
 static Socket s4;

 grcommser1()
 {
  try
  {
   ss1=new ServerSocket(9000);
   ss2=new ServerSocket(8000);
   ss3=new ServerSocket(7000);
   ss4=new ServerSocket(6000);
   s1=ss1.accept();
   s2=ss2.accept();
   s3=ss3.accept();
   s4=ss4.accept();
   System.out.print("\n Server is started ");
   System.out.print("\n");
   System.out.print("\n\t\t First Group \t\t"+ss1.getInetAddress());
   System.out.print("\n\t\t Second Group \t\t"+ss2.getInetAddress());
   System.out.print("\n\t\t Third Group \t\t"+ss3.getInetAddress());
   System.out.print("\n\t\t Fourth Group \t\t"+ss4.getInetAddress());
  }
  catch(Exception e)
  {
   System.out.print(e.toString());
  }
 }	
 
 public static void main(String []p) throws Exception
 {
  grcommser1 t = new grcommser1();
  byte b[]=new byte[100];
  InputStream in=s1.getInputStream();
  in.read(b,0,99);
  OutputStream os=s3.getOutputStream();
  OutputStream os1=s4.getOutputStream();
  os.write(b,0,99);
  os1.write(b,0,99);
  InputStream in1=s3.getInputStream();
  in1.read(b,0,99);
  OutputStream os2=s1.getOutputStream();
  OutputStream os3=s2.getOutputStream();
  os2.write(b,0,99);
  os3.write(b,0,99);
 }
}
