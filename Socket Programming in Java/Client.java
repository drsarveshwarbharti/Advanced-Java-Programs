//Example Program: TCP Client–Server Communication
//Client Program

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server.");

        // Create input and output streams
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send message to server
        System.out.print("Enter message: ");
        String message = input.readLine();
        output.println(message);

        // Receive server response
        System.out.println("Server says: " + response.readLine());

        // Close connection
        socket.close();
    }
}
