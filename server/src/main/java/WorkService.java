import java.util.ArrayList;

    //Business processing layer
public class WorkService {
    //Instantiate the database connection class
    private JabberDatabase jabberDatabase = new JabberDatabase();

    public String register(String message) {
        System.out.println("server receive " + message);
        String[] arr = message.split("\\s+");
        boolean userNotExit = jabberDatabase.getUserID(arr[1]) == -1;
        if (arr.length > 1 && userNotExit) {
            jabberDatabase.addUser(arr[1], arr[1] + "@" + arr[1] + ".com");
            return "signedin";
        }
        return "user already exist";
    }
    //User login method
    public String singin(String message) {
        System.out.println("server receive " + message);
        //Extract string to array separated by spaces
        String[] arr = message.split("\\s+");
        //Determine if there is this user
        if (jabberDatabase.getUserID(arr[1]) != -1) {
            return "signedin";
        }
        return "unknown-user";
    }

    //get timeline
    public ArrayList<ArrayList<String>> timeline(String username) {
        System.out.println("server receive timeline username" + username);
        int userID = jabberDatabase.getUserID(username);
        ArrayList<ArrayList<String>> timelineOfUserEx = jabberDatabase.getTimelineOfUserEx(userID);
        System.out.println("userId is "+userID);
        return timelineOfUserEx;
    }
    
    public ArrayList<ArrayList<String>> getUsersNotFollowed(String username) {
        int userID = jabberDatabase.getUserID(username);
        ArrayList<ArrayList<String>> timelineOfUserEx = jabberDatabase.getUsersNotFollowed(userID);
        return timelineOfUserEx;
    }

    public void addLike(String username,Integer jabId) {
        int userID = jabberDatabase.getUserID(username);
        jabberDatabase.addLike(userID,jabId);

    }

    public void addFollower(String username, String beFollowedName) {
        int userida = jabberDatabase.getUserID(username);
        int useridb = jabberDatabase.getUserID(beFollowedName);
        jabberDatabase.addFollower(userida,useridb);
    }

    public void addJab(String username, String jabMessage) {
        jabberDatabase.addJab(username,jabMessage);
    }
}
