package com.maelog.wordscrambled;

import java.util.Scanner;

public class Menu {
    StringBuilder menuInfo;
    public Menu() {
        menuInfo = new StringBuilder("Select your option :\n");
        int i = 0;
        for (Difficulty dif: Difficulty.values()) {
            menuInfo.append(dif.toString()).append("\n");
        }
    }

    public Difficulty display() {
        System.out.println(menuInfo.toString());
        Scanner scan = new Scanner(System.in);
        int returnedValue = scan.nextInt();
        return Difficulty.valueOf(returnedValue);
    }
}
