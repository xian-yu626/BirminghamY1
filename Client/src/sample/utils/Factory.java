package sample.utils;


import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;


public class Factory {
    public Stage createStage(String viewname,String title, int width, int height, String file) throws IOException {
        URL url = Factory.class.getClassLoader().getResource(file);
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        //Set the operations that need to be performed when the stage is closed
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        Context.stageManager.addStage(viewname, stage);
        return stage;
    }
}
