package com.maelog.wordscrambled;

import java.util.Objects;
import java.util.Scanner;

public class WordSConsole {
    GameManager game;
    Scanner scan = new Scanner(System.in);
    Integer compteur;
    public WordSConsole() {
        System.out.println("  ____  ____ _____   ____   __  __ _____  _     ____  ____    __    __ ____ _____  ____   ____ \n" +
                " (_ (_`/ (__`| () ) / () \\ |  \\/  || () )| |__ | ===|| _) \\   \\ \\/\\/ // () \\| () )| _) \\ (_ (_`\n" +
                ".__)__)\\____)|_|\\_\\/__/\\__\\|_|\\/|_||_()_)|____||____||____/    \\_/\\_/ \\____/|_|\\_\\|____/.__)__)");

        Menu menu = new Menu();
        Difficulty dif = menu.display();
        switch (dif){
            case EASY -> {
                System.out.println("You selected easy\n\n");
                game = new GameManager(1);
            }
            case MEDIUM -> {
                System.out.println("You selected medium\n\n");
                game = new GameManager(2);
            }
            case HARD -> {
                System.out.println("You selected hard\n\n");
                game = new GameManager(3);
            }
            default -> {
                System.out.println("You don't select a difficulty, you go for INSANE\n\n");
                game = new GameManager(4);
            }
        }
        Game();

    }


    public void Game(){
        game.InitTimer();
        compteur = 1;
        for (String word: game.ScrambledWords) {
            String bufferWord = word;
            word = game.MixWord(word);
            System.out.println("Your word is : "+ word + "\n --------------------------- \n");
            if(TryWord(bufferWord)){
                System.out.println("Good Job you find " + compteur + "/10" );
                compteur++;
            }
        }
        System.out.println("Bravo tu as trouvé cette liste de mot en " + game.GetTime() + " second");
    }


    public boolean TryWord(String word){
        while(true) {
            String scanWord = scan.nextLine();
            if (scanWord.equalsIgnoreCase(word)) {
                return true;
            }
            else {
                System.out.println("Not the word !");
            }
        }
    }


}
