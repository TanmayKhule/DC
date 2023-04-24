/*package exp8.LoadBalancing;*/

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/** * RPCServer_Date */
public class MyServerTwo {
	static DatagramSocket serverDatagramSocket;
	static DatagramPacket clientDataPacket;
	static byte buf[];
	static int PORT = 5002;

	public static void main(String[] args) {
		try {
			System.out.println("Waiting for client packet...");
			buf = new byte[1024];
			serverDatagramSocket = new DatagramSocket(PORT);
			clientDataPacket = new DatagramPacket(buf, buf.length);
			while (true) {
				serverDatagramSocket.receive(clientDataPacket);
				String res = new String(clientDataPacket.getData(), 0, clientDataPacket.getLength());
				System.out.println("Received: " + res);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			serverDatagramSocket.close();
			PORT++;
		}
	}
}