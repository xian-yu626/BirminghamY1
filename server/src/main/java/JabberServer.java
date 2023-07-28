import java.io.IOException;
import java.net.ServerSocket;

public class JabberServer {
    /*
     * The threading method is enabled to enable multiple users to connect to the socket server.
     */
    public static void main(String[] args) throws IOException {
        // Create a server, listen on port 44444
        ServerSocket server = new ServerSocket(44444);
        System.out.println("my server already in running in 44444 wait for connection.");
        // Use the server to keep listening
        while (true) {
            // Receive the connection of each client and return the socket instance
            ClientConnection clientConnection = new ClientConnection(server.accept());
            // Start a thread for each client to perform operations
            new Thread(clientConnection).start();
        }
    }
}
