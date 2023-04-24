import java.io.*;
import java.net.*;

class Client {
    public static void main(String argv[]) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Display menu and get user choice
            System.out.println("Choose a program to execute:\n1. Folder contents\n2. Free Disk Space\n3. File Size");
            int choice = Integer.parseInt(inFromUser.readLine());

            // Connect to server and send choice
            Socket clientSocket = new Socket("localhost", 6789);
            DataOutputStream dataToServer = new DataOutputStream(clientSocket.getOutputStream());
            dataToServer.writeBytes(choice + "\n");

            // Receive result from server
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String result;
            System.out.println("Received from Server:");
            while ((result = inFromServer.readLine()) != null) {
              System.out.println(result);
          }
            //System.out.println("Result received from server: \n" + result);

            // Close the socket
            clientSocket.close();
        }
    }
}
