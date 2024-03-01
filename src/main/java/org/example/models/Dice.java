package org.example.models;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class Dice {
    private final int SIZE = 6;

    public int roll() {
        int offset = (int) floor(random() * SIZE);
        return 1 + offset;
    }
}
