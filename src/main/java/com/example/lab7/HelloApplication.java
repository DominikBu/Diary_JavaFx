package com.example.lab7;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController hc = fxmlLoader.getController();
        stage.setTitle("Dziennik");
        stage.setScene(scene);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            exitEvent(stage, hc);
        });
    }

    public void exitEvent( Stage stage, HelloController hc )
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Do you want to save before exiting?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            hc.exportCsv();
        }
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}