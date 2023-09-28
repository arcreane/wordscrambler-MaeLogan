package com.maelog.wordscrambled;

import java.util.HashMap;
import java.util.Map;

public enum Difficulty {

    EASY(1),
    MEDIUM(2),
    HARD(3);


    private final int value;
    private static Map<Integer, Difficulty> map = new HashMap<>();

    private Difficulty(int value) {
        this.value = value;
    }

    static {
        for (Difficulty difficulty : Difficulty.values()) {
            map.put(difficulty.value, difficulty);
        }
    }

    public static Difficulty valueOf(int difficulty) {
        return (Difficulty) map.get(difficulty);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\t- Enter ").append(value).append(" for: ").append(super.toString());
        return builder.toString();
    }
}
