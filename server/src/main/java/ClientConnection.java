import server.JabberMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection implements Runnable {
    private Socket client;

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String  username;
    private WorkService workService = new WorkService();

    public ClientConnection(Socket client) {
        this.client = client;
        try {
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("client ip is " + client.getInetAddress());
                JabberMessage jabberMessage = (JabberMessage) in.readObject();
                System.out.println("server get message:  " + jabberMessage.getMessage());
                execute(jabberMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Judge the execution method based on the received data
    private void execute(JabberMessage jabberMessage) throws IOException {
        System.out.println("receive request is: "+jabberMessage.getMessage());
        
        if (jabberMessage.getMessage().startsWith("register")) {
            //Execute registration method
            String message = workService.register(jabberMessage.getMessage());
            //Instantiate the returned JabberMessage
            JabberMessage jabberMessageBack = new JabberMessage(message);
            //Return data to the client
            out.writeObject(jabberMessageBack);
        }

        if (jabberMessage.getMessage().startsWith("signin")) {
            System.out.println(jabberMessage.getMessage());
            String[] split = jabberMessage.getMessage().split("\\s+");
            this.username = split[1];
            String message = workService.singin(jabberMessage.getMessage());
            JabberMessage jabberMessageBack = new JabberMessage(message);
            out.writeObject(jabberMessageBack);
        }

        if (jabberMessage.getMessage().startsWith("timeline")) {
            ArrayList<ArrayList<String>> timeline = workService.timeline(username);
            JabberMessage jabberMessageBack = new JabberMessage("",timeline);
            out.writeObject(jabberMessageBack);
        }

        if (jabberMessage.getMessage().startsWith("users")) {
            ArrayList<ArrayList<String>> users = workService.getUsersNotFollowed(username);
            JabberMessage jabberMessageBack = new JabberMessage("users",users);
            out.writeObject(jabberMessageBack);
        }

        if (jabberMessage.getMessage().startsWith("like")) {
            String[] split = jabberMessage.getMessage().split("\\s+");
            Integer jabId  = Integer.valueOf(split[1]);
            workService.addLike(username,jabId);
            JabberMessage jabberMessageBack = new JabberMessage("posted");
            out.writeObject(jabberMessageBack);
        }

        if (jabberMessage.getMessage().startsWith("follow")) {
            String[] split = jabberMessage.getMessage().split("\\s+");
            String beFollowedName  = split[1];
            workService.addFollower(username,beFollowedName);
            JabberMessage jabberMessageBack = new JabberMessage("posted");
            out.writeObject(jabberMessageBack);
        }

        if (jabberMessage.getMessage().startsWith("post")) {
            String jabMessage = jabberMessage.getMessage().replace("post ","");
            workService.addJab(username,jabMessage);
            JabberMessage jabberMessageBack = new JabberMessage("posted");
            out.writeObject(jabberMessageBack);
        }

        if (jabberMessage.getMessage().startsWith("singout")) {
            in.close();
            out.close();
        }








    }
}
