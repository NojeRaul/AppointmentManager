package com.example.appointmentmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try
        {
            Parent root = loader.load();
            Controller control = loader.getController();
            control.initialize("Settings.properties");
            loader.setController(control);


            stage.setTitle("Application");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}