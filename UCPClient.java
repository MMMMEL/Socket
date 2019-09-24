import java.net.*;
import java.io.*;
import java.util.Date;

public class UCPClient {
    public static void main(String args[]){
        // args give message contents and server hostname
        DatagramSocket aSocket = null;
        if (args.length < 3) {
            System.out.println(
                    "Usage:java UDPClient <message > <Host name> <Port number>");
            System.exit(1);
        }
        try {
            aSocket = new DatagramSocket();
            byte [] m = args[0].getBytes();
            InetAddress aHost = InetAddress.getByName(args[1]);
            int serverPort = Integer.valueOf(args[2]).intValue();
            DatagramPacket request =
                    new DatagramPacket(m, args[0].length(), aHost, serverPort);

            int count = 0;
            while (count < 10) {
                long time = new Date().getTime();
                aSocket.send(request);
                aSocket.setSoTimeout(1000);
                try {
                    byte[] buffer = new byte[1000];
                    DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(reply);
                    long end = new Date().getTime();
                    System.out.println("RTT for request " + (count + 1) + " is " + (end - time) + " msec.");
                }
                catch (SocketTimeoutException te) {
                    System.out.println("Request " + (count + 1) + " time out!");
                }
                count++;
            }
        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}
