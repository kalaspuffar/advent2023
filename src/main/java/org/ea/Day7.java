package org.ea;

import org.ea.day7.Hand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {

        String text = """
32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483                
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day7.txt"));
            long val = 0;

            List<Hand> cards = new ArrayList<>();

            for (String line : br.lines().toList()) { // text.split("\n")) {//
                cards.add(new Hand(line));
            }

            Collections.sort(cards);

            int rank = cards.size();
            for (Hand card : cards) {
                card.setRank(rank);
                val += card.getScore();
                rank--;
            }

            // 245576185
            // 246000630
            // 246239111
            // 246267562
            // 248527259
            // 249432810


            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}