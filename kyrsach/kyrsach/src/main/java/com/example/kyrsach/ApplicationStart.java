package com.example.kyrsach;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *Создание интерфейса пользователя
 */
public class ApplicationStart extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStart.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 709, 364);
        stage.setResizable(false);
        stage.setTitle("Курсовая работа. Морозова К.В.");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}