package com.maelog.wordscrambled;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class WordJavafx {

    GameManager game;
    Integer compteur;
    public HBox MenuStart;
    public VBox MenuGame;
    public Label word;
    public Label Message;
    public TextField FieldWord;
    public String wordToFind;
    public Label timerLabel;
    public SplitPane footerGame;
    public Timeline timeline;



    public WordJavafx() {
        compteur = 0;


    }

    public void StartGame(ActionEvent actionEvent) {

        String source = ((Button)actionEvent.getSource()).getText();
        switch (source) {
            case "Easy" -> game = new GameManager(1);
            case "Medium" -> game = new GameManager(2);
            case "Hard" -> game = new GameManager(3);
            default -> System.out.println("error not a button");
        }
        ChangeLayout();
        System.out.println(game.ScrambledWords);
        NextWord();
        game.InitTimer();
        setTimer();
    }

    public void ChangeLayout(){
        if(MenuStart.isDisable() && !MenuGame.isDisable()){
            MenuStart.setDisable(false);
            MenuStart.setOpacity(1);
            MenuGame.setDisable(true);
            MenuGame.setOpacity(0);
            footerGame.setDisable(true);
            footerGame.setOpacity(0);

        }
        else{
            MenuGame.setDisable(false);
            MenuGame.setOpacity(1);
            MenuStart.setDisable(true);
            MenuStart.setOpacity(0);
            footerGame.setDisable(false);
            footerGame.setOpacity(1);
        }
    }

    public void TryWord(){
        String word = FieldWord.getText();
        System.out.println(word);
        Message.setText("");

        if(word.equalsIgnoreCase(wordToFind)) {
            NextWord();
            FieldWord.setText("");
        }
        else{
            Message.setTextFill(Color.color(1,0,0));
            Message.setText("Not the good word !");

        }

    }

    public void NextWord(){
        if(compteur +1 > game.ScrambledWords.size()){
            word.setTextFill(Color.color(0,1,0));
            word.setText("GOOD JOB you finish in "+ timerLabel.getText() + " minutes");
            StopTimer();
            MenuGame.setDisable(true);
            word.setDisable(true);
        }
        else {
            String nextWord = game.ScrambledWords.get(compteur);
            word.setText(game.MixWord(nextWord));
            wordToFind = nextWord;
            compteur++;
        }
    }

    public void setTimer(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            timerLabel.setText(game.GetTime());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void StopTimer(){
        timeline.stop();
    }

    public void RestartGame(ActionEvent actionEvent) {
        ChangeLayout();
        compteur = 0;
        word.setTextFill(Color.color(0,0,0));
        Message.setText("");
        StopTimer();
    }

    public void QuitGame(ActionEvent actionEvent) {
        Platform.exit();
    }
}
