package com.maelog.wordscrambled;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Scrambler Word");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       if(Objects.equals(args[0], "0")){
           WordSConsole console = new WordSConsole();
       }
       if(args[0].equals("1")) {
           launch();
       }
    }
}