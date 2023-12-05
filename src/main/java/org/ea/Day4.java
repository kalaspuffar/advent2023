package org.ea;

import org.ea.day3.NumberPos;
import org.ea.day4.Card;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day4 {
    public static int MAP_SIZE = 200;

    public static void main(String[] args) {

        String text = """
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                """;


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day4.txt"));
            int val = 0;

            List<Card> cards = new ArrayList<>();

            for (String line : br.lines().toList()) { // text.split("\n")) {//
                cards.add(new Card(line));
            }

            for (int i = 0; i < cards.size(); i++) {
                Card c = cards.get(i);
                int copies = c.getCopies();
                int wins = c.getWins();
                val += copies;
                for (int v = i + 1; v < i + 1 + wins && v < cards.size(); v++) {
                    cards.get(v).addCopies(copies);
                }
            }
/*
            for (Card c : cards) {
                val += c.getPoints();
            }
*/
            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}