import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    Map<Socket, String> clientMap = new HashMap<>();

    public static void main(String[] args) {
        new Server().runServer();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            //System.out.println("Server started on port 7777.");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);

                // Start a new thread to handle client messages
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thread to handle messages from a single client
    // Thread to handle messages from a single client
class ClientHandler implements Runnable {
    Socket socket;
    BufferedReader in;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            // Ask user for username
            String username = in.readLine();
            clientMap.put(socket, username);
            System.out.println("New client connected: " + username);

            String message;
            while ((message = in.readLine()) != null) {
                //System.out.println("Message received from " + username + ": " + message);

                // Handle personal message
                if (message.startsWith("@")) {
                    int spaceIndex = message.indexOf(" ");
                    if (spaceIndex > 0) {
                        String recipient = message.substring(1, spaceIndex);
                        String personalMessage = message.substring(spaceIndex + 1);
                        for (Socket client : clientMap.keySet()) {
                            String clientUsername = clientMap.get(client);
                            if (clientUsername.equals(recipient)) {
                                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                                out.println("[Personal message from " + username + "]: " + personalMessage);
                            }
                        }
                    }
                } else { // Broadcast message to all clients except the sender
                    for (Socket client : clientMap.keySet()) {
                        if (client != socket) {
                            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                            out.println(username + ": " + message);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                clientMap.remove(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

}
