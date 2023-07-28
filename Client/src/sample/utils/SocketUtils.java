package sample.utils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class SocketUtils {
    public static ObjectInputStream ois = null; // gets stuff from server
    public static ObjectOutputStream oos = null; //sends stuff to server
    public static  Socket socketConnection;
    public static int PORT = 44444;
    public static void init() throws Exception {
        System.out.println("client send message to localhost: "+PORT);
        //Connect to the server
        socketConnection = new Socket("localhost", PORT);
        oos=new ObjectOutputStream(socketConnection.getOutputStream());
        ois= new ObjectInputStream(socketConnection.getInputStream());
    }
}
