import java.io.*;
import java.net.*;

class Server {
    public static void main(String argv[]) throws Exception {
        String s = null;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            // Read from Client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            int choice = Integer.parseInt(inFromClient.readLine());
            System.out.println("Received from Client: " + choice);

            // Execute chosen program
            String result = "";
            switch (choice) {
                case 1:
                    Process p1 = Runtime.getRuntime().exec("java ContentsOfFolder");
                    BufferedReader stdInput1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                    while ((s = stdInput1.readLine()) != null) {
                        result += s + "\n";
                    }
                    break;
                case 2:
                    Process p2 = Runtime.getRuntime().exec("java DiskSpace");
                    BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
                    while ((s = stdInput2.readLine()) != null) {
                        result += s + "\n";
                    }
                    break;
                case 3:
                    Process p3 = Runtime.getRuntime().exec("java DisplayFileSize");
                    BufferedReader stdInput3 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
                    while ((s = stdInput3.readLine()) != null) {
                        result += s + "\n";
                    }
                    break;
                default:
                    result = "Invalid choice";
                    break;
            }
            System.out.println("Result of program execution: " + result);

            // Send result to client
			
            DataOutputStream dataToClient = new DataOutputStream(connectionSocket.getOutputStream());
            dataToClient.writeBytes(result + '\n');

            // Close the socket
            connectionSocket.close();
        }
    }
}

