import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    public static void main(String args[]) throws IOException {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        Socket clientSocket = new Socket(serverName, port);

        // Get client input and pass to server socket
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        OutputStream s1out = clientSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream (s1out);
        dos.writeUTF(inputString);

        // Get an input file handle from the socket and read the input
        InputStream s1In = clientSocket.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = new String (dis.readUTF());
        System.out.println(st);

        // Close the connection and exit
        dis.close();
        s1In.close();
        clientSocket.close();
    }
}
