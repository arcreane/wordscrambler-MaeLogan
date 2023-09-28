package com.maelog.wordscrambled;

import java.util.Objects;
import java.util.Scanner;

public class WordSConsole {
    GameManager game;
    Scanner scan = new Scanner(System.in);
    public WordSConsole() {
        System.out.println("  ____  ____ _____   ____   __  __ _____  _     ____  ____    __    __ ____ _____  ____   ____ \n" +
                " (_ (_`/ (__`| () ) / () \\ |  \\/  || () )| |__ | ===|| _) \\   \\ \\/\\/ // () \\| () )| _) \\ (_ (_`\n" +
                ".__)__)\\____)|_|\\_\\/__/\\__\\|_|\\/|_||_()_)|____||____||____/    \\_/\\_/ \\____/|_|\\_\\|____/.__)__)");

        Menu menu = new Menu();
        Difficulty dif = menu.display();
        switch (dif){
            case EASY -> {
                System.out.println("You selected easy");
                game = new GameManager(1);
            }
            case MEDIUM -> {
                System.out.println("You selected medium");
                game = new GameManager(2);
            }
            case HARD -> {
                System.out.println("You selected hard");
                game = new GameManager(3);
            }
            default -> {
                System.out.println("You don't select a difficulty, you go for INSANE");
                game = new GameManager(4);
            }
        }
        Game();

    }


    public void Game(){
        game.InitTimer();
        for (String word: game.ScrambledWords) {
            String bufferWord = word;
            game.MixWord(word);
            System.out.println("Your word is : "+ bufferWord);
            if(TryWord(bufferWord)){

            }
        }
    }


    public boolean TryWord(String word){
        String scanWord = scan.nextLine();
        while(true) {
            if (Objects.equals(word, scanWord)) {
                return true;
            }
        }
    }


}
