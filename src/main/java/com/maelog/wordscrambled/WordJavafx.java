package com.maelog.wordscrambled;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class WordJavafx {

    private GameManager game;
    private Integer compteur;
    private Timeline timeline;
    public HBox menuStart;
    public VBox menuGame;
    public Label word;
    public Label message;
    public TextField fieldWord;
    public String wordToFind;
    public Label timerLabel;
    public SplitPane footerGame;
    public Button buttonSend;



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
        buttonSend.setDisable(false);
        System.out.println(game.scrambledWords);
        NextWord();
        game.InitTimer();
        SetTimer();
    }

    public void ChangeLayout(){
        if(menuStart.isDisable() && !menuGame.isDisable()){
            menuStart.setDisable(false);
            menuStart.setOpacity(1);
            menuGame.setDisable(true);
            menuGame.setOpacity(0);
            footerGame.setDisable(true);
            footerGame.setOpacity(0);

        }
        else{
            menuGame.setDisable(false);
            menuGame.setOpacity(1);
            menuStart.setDisable(true);
            menuStart.setOpacity(0);
            footerGame.setDisable(false);
            footerGame.setOpacity(1);
        }
    }

    public void TryWord(){
        String word = fieldWord.getText();
        message.setText("");
        if(word.equalsIgnoreCase(wordToFind)) {
            NextWord();
            fieldWord.setText("");
        }
        else{
            message.setTextFill(Color.color(1,0,0));
            message.setText("Not the good word !");

        }

    }

    public void NextWord(){
        if(compteur +1 > game.scrambledWords.size()){
            word.setTextFill(Color.color(0,1,0));
            word.setText("GOOD JOB you finish in "+ timerLabel.getText() + " minutes");
            StopTimer();
            buttonSend.setDisable(true);

            //MenuGame.setDisable(true);


            word.setDisable(true);
        }
        else {
            String nextWord = game.scrambledWords.get(compteur);
            word.setText(game.MixWord(nextWord));
            wordToFind = nextWord;
            compteur++;
        }
    }

    public void SetTimer(){
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
        message.setText("");
        StopTimer();
    }

    public void QuitGame(ActionEvent actionEvent) {
        Platform.exit();
    }
}
