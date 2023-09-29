package com.maelog.wordscrambled;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameManager {
    int minSizeWord;
    int maxSizeWord;
    long startTime;
    public List<String> ScrambledWords;
    public GameManager(int difficulty) {
        List<String> listWord;
        List<Integer> arrayNumber;
        if(difficulty == 1){
            minSizeWord = 3;
            maxSizeWord = 4;
        }
        else if(difficulty == 2){
            minSizeWord = 4;
            maxSizeWord = 6;
        }
        else if(difficulty == 3){
            minSizeWord = 6;
            maxSizeWord = 8;
        }
        else if(difficulty == 4){
            minSizeWord = 12;
            maxSizeWord = 20;
        }
        else {
            System.out.println("Error you don't choose the good difficulty");
        }
        listWord = GetAllWords();
        arrayNumber = ArrayNumber(10, 1, listWord.size());
        ScrambledWords = ScrambledWords(listWord, arrayNumber);
    }

    public List<String> GetAllWords() {
        List<String> lines = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\MaeLog\\IdeaProjects\\MasterIA\\WordScrambled\\src\\main\\java\\com\\maelog\\wordscrambled\\words.txt"));
            String line = reader.readLine();
            int i = 0;
            while (line != null ) {
                if(line.length() >= minSizeWord && line.length() <= maxSizeWord) {
                    lines.add(line);
                }
                line = reader.readLine();
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public ArrayList<Integer> ArrayNumber(int sizeArray, int minNbr, int maxNbr) {
        ArrayList<Integer> arrayInteger = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < sizeArray; i++) {
            arrayInteger.add(random.nextInt(maxNbr + minNbr));
        }
        return arrayInteger;
    }


    public List<String> ScrambledWords(List<String> listWord, List<Integer>randomWord){
        List<String> ScrambledWord = new ArrayList<>();
        for (Integer rand: randomWord) {
            ScrambledWord.add(listWord.get(rand));
        }
        return ScrambledWord;
    }

    public void InitTimer(){
        startTime = System.currentTimeMillis();
    }

    public String GetTime(){
       /* long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0");
        return formatter.format((end - startTime) / 1000d);

        */
        long end = System.currentTimeMillis();
        long elapsedTimeInSeconds = (end - startTime) / 1000; // Get elapsed time in seconds

// Calculate minutes and seconds
        long minutes = elapsedTimeInSeconds / 60;
        long seconds = elapsedTimeInSeconds % 60;

// Format the minutes and seconds as mm:ss
        String formattedTime = String.format("%02d:%02d", minutes, seconds);

        return formattedTime;

    }

    public String MixWord(String word){
        StringBuilder mixWord = new StringBuilder();
        List<Character> letters = new ArrayList<Character>();
        for (char letter: word.toCharArray()) {
            letters.add(letter);
        }
        if (letters.size() > 2) {
            List<Character> buffer = new ArrayList<>(letters);
            while(true){
                Collections.shuffle(letters);
                if(!buffer.equals(letters)){
                    break;
                }
            }
        }
        for (char letter : letters) {
            mixWord.append(letter);
        }
        return mixWord.toString();
    }

}






