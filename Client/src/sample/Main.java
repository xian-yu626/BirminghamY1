package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.utils.Context;
import sample.utils.SocketUtils;

import java.io.IOException;
import java.io.InputStream;

//the GUI class
public class Main extends Application {
    public static int WIDTH = 600;
    public static int HEIGHT = 600;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        SocketUtils.init();
        Stage login = Context.factory.createStage("Login","Login", WIDTH, HEIGHT, "\\sample\\controller\\Login.fxml");
        login.show();
    }

    //Index interface
    public void gotoIndex() {
        try {
       
            Context.factory.createStage("Index","Index", WIDTH, HEIGHT, "\\sample\\controller\\Index.fxml");
            Context.stageManager.jump("Login", "Index");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Index interface refresh
    public void refreshFollow() {
        try {
            Context.factory.createStage("Follow1","Follow", WIDTH, HEIGHT, "\\sample\\controller\\Index.fxml");
            Context.stageManager.jump("Follow", "Follow1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Follow interface
    public void gotoFollow() {
        try {
 
            Context.factory.createStage("Follow","Follow", WIDTH, HEIGHT, "\\sample\\controller\\Follow.fxml");
            Context.stageManager.jump2("Index", "Follow");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Post interface
    public void gotoPost() {
        try {
            Context.factory.createStage("Post","Post", WIDTH, HEIGHT, "\\sample\\controller\\Post.fxml");
            Context.stageManager.jump2("Index", "Post");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Index interface
    public void gotoIndexFromPost() {
        try {
            Context.stageManager.close("Index","Post");
            Context.factory.createStage("Index","Index", WIDTH, HEIGHT, "\\sample\\controller\\Index.fxml");
            Context.stageManager.show("Index");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Index interface
    public void gotoIndexFromFollow() {
        try {
            Context.stageManager.close("Index","Follow");
            Context.factory.createStage("Index","Index", WIDTH, HEIGHT, "\\sample\\controller\\Index.fxml");
            Context.stageManager.show("Index");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Index interface refresh
    public void refreshIndex() {
        try {
            Context.factory.createStage("Index1","Index", WIDTH, HEIGHT, "\\sample\\controller\\Index.fxml");
            Context.stageManager.jump("Index", "Index1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
