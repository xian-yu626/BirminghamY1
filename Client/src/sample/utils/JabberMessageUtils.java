package sample.utils;

import com.bham.fsd.assignments.jabberserver.JabberMessage;
import java.io.IOException;
import java.util.ArrayList;


public class JabberMessageUtils {
    public static String sendMessage(String message) throws IOException, ClassNotFoundException {
        //Create an object to send data
        JabberMessage jm = new JabberMessage(message);
        //send data
        SocketUtils.oos.writeObject(jm);
        //Refresh cache
        SocketUtils.oos.flush();
        //Get the return value of the server
        JabberMessage jabberMessage = (JabberMessage) SocketUtils.ois.readObject();
        return jabberMessage.getMessage();
    }
    public static  void sendMessageForVoid(String message) throws IOException {
        JabberMessage jm = new JabberMessage(message);
        SocketUtils.oos.writeObject(jm);
        SocketUtils.oos.flush();
    }
    public static JabberMessage sendMessageForObject(String message) throws IOException, ClassNotFoundException {
        JabberMessage jm = new JabberMessage(message);
        SocketUtils.oos.writeObject(jm);
        SocketUtils.oos.flush();
        return  (JabberMessage) SocketUtils.ois.readObject();
    }
    public static ArrayList<ArrayList<String>> sendMessageForData(String message) throws IOException, ClassNotFoundException {
        JabberMessage jm = new JabberMessage(message);
        SocketUtils.oos.writeObject(jm);
        SocketUtils.oos.flush();
        JabberMessage jabberMessage = (JabberMessage) SocketUtils.ois.readObject();
        return  jabberMessage.getData();
    }
}
