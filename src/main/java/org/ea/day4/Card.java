package org.ea.day4;

import java.util.ArrayList;
import java.util.List;

public class Card {

    int cardNumber;
    List<Integer> winning = new ArrayList<>();

    // Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11

    int points = 0;
    int copies = 1;
    int wins = 0;

    public Card(String str) {
        String[] arr = str.split(":");
        cardNumber = Integer.parseInt(arr[0].replace("Card", "").trim());
        String[] arr2 = arr[1].split("\\|");
        for (String c : arr2[0].split(" ")) {
            if (c.isBlank()) continue;
            winning.add(Integer.parseInt(c));
        }
        for (String c : arr2[1].split(" ")) {
            if (c.isBlank()) continue;
            if (winning.contains(Integer.parseInt(c))) {
                wins++;
                if (points == 0) {
                    points++;
                } else {
                    points *= 2;
                }
            }
        }
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public void addCopies(int copies) {
        this.copies += copies;
    }

    public int getCopies() {
        return copies;
    }
}
