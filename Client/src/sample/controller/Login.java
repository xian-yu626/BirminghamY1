package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.utils.JabberMessageUtils;
import sample.utils.ModelView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

//perform all server communications on behalf of the client
public class Login implements ModelView {
    @FXML
    private TextField T1;  //username
    @FXML
    private Button B1;  //Sign In
    @FXML
    private Button B2;  //Register
    @FXML
    private Button B3;  //Sign Out
    @FXML
    private Label label;
    @FXML
    private Label errorLabel;

    @Override
    public void prepare() throws Exception {
    }

    @Override
    public void refreshView() throws Exception {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            prepare();
            refreshView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signIn(ActionEvent event) throws IOException, ClassNotFoundException //RQ1 & RQ2
    {
        String username = T1.getText();
        String request = "signin " + username;
        String r = JabberMessageUtils.sendMessage(request);
        System.out.println("login message " + r);
        //RQ1
        if (r.equalsIgnoreCase("signedin")) {
            label.setText("Sign In Successful");
            new Main().gotoIndex();
        }
        //RQ2
        else if (r.equalsIgnoreCase("unknown-user")) {
            label.setText(r);
        }
    }

    public void register(ActionEvent event) throws IOException, ClassNotFoundException //RQ3
    {
        String username = T1.getText();
        String request = "register " + username;

        String r = JabberMessageUtils.sendMessage(request);
        if (r.equalsIgnoreCase("signedin")) {
            label.setText("Registration Successful");
        } else {
            label.setText(r);
        }
        new Main().gotoIndex();
    }
    
}

