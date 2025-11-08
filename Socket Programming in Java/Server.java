//Example Program: TCP Client–Server Communication
//Server Program

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        // Create input and output streams
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // Read message from client
        String message = input.readLine();
        System.out.println("Client says: " + message);

        // Send response
        output.println("Hello Client, message received!");

        // Close connections
        socket.close();
        serverSocket.close();
    }
}
