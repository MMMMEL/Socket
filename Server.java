import java.net.*;
import java.io.*;

public class Server {
    public static String transform (String inputStr) {
        StringBuilder sb = new StringBuilder (inputStr);
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (Character.isLowerCase(ch)) {
                sb.setCharAt(i, Character.toUpperCase(ch));
            } else if (Character.isUpperCase(ch)) {
                sb.setCharAt(i, Character.toLowerCase(ch));
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String args[]) throws IOException {
        int port = Integer.parseInt(args[0]);
        ServerSocket s = new ServerSocket(port);
        Socket s1 = s.accept(); // Wait and accept a connection

        // Get string from client socket
        DataInputStream input = new DataInputStream(s1.getInputStream());
        String clientInputStr = input.readUTF();
        String converted = transform(clientInputStr);

        // Get a communication stream associated with the socket
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream (s1out);
        // Send a string!
        dos.writeUTF(converted);
        // Close the connection, but not the server socket
        dos.close();
        s1out.close();
        s1.close();
    }
}
