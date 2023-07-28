package server;

import java.io.Serializable;


//TimeLine entity class
public class Timeline implements Serializable {
    private String username;
    private String jabtext;
    private String jabid;
    private String jabcount;

    public Timeline() {
    }

    @Override
    public String toString() {
        return "Timeline{" +
                "username='" + username + '\'' +
                ", jabtext='" + jabtext + '\'' +
                ", jabid='" + jabid + '\'' +
                ", jabcount='" + jabcount + '\'' +
                '}';
    }

    public Timeline(String username, String jabtext, String jabid, String jabcount) {
        this.username = username;
        this.jabtext = jabtext;
        this.jabid = jabid;
        this.jabcount = jabcount;
    }

    public String getUsername() {
        return username;
    }

    public Timeline setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getJabtext() {
        return jabtext;
    }

    public Timeline setJabtext(String jabtext) {
        this.jabtext = jabtext;
        return this;
    }

    public String getJabid() {
        return jabid;
    }

    public Timeline setJabid(String jabid) {
        this.jabid = jabid;
        return this;
    }

    public String getJabcount() {
        return jabcount;
    }

    public Timeline setJabcount(String jabcount) {
        this.jabcount = jabcount;
        return this;
    }
}
