package org.ea.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand implements Comparable<Hand> {
    static int FIVE_OF_A_KIND = 6;
    static int FOUR_OF_A_KIND = 5;
    static int FULL_HOUSE = 4;
    static int THREE_OF_A_KIND = 3;
    static int TWO_PAR = 2;
    static int ONE_PAR = 1;
    static int HIGH_CARD = 0;

    int type = HIGH_CARD;
    int[] hand = new int[5];
    long bid;

    int[] typeAmount = new int[15];
    private long rank;

    public Hand(String line) {
        int i = 0;

        Arrays.fill(typeAmount, 0);

        String[] lineArr = line.split(" ");

        bid = Long.parseLong(lineArr[1]);

        for (String c : lineArr[0].split("")) {
            switch(c) {
                case "A": hand[i] = 14; break;
                case "K": hand[i] = 13; break;
                case "Q": hand[i] = 12; break;
                case "J": hand[i] = 0; break;
                case "T": hand[i] = 10; break;
                case "9": hand[i] = 9; break;
                case "8": hand[i] = 8; break;
                case "7": hand[i] = 7; break;
                case "6": hand[i] = 6; break;
                case "5": hand[i] = 5; break;
                case "4": hand[i] = 4; break;
                case "3": hand[i] = 3; break;
                case "2": hand[i] = 2; break;
            }
            typeAmount[hand[i]]++;

            i++;
        }

        List<Integer> amounts = new ArrayList<>();
        for (int k = 1; k < typeAmount.length; k++) {
            int v = typeAmount[k];
            if (v == 0) continue;
            amounts.add(v);
        }

        if (typeAmount[0] == 5) {
            type = Hand.FIVE_OF_A_KIND;
            return;
        }

        Collections.sort(amounts, (a, b) -> b - a);

        boolean usedTypeAmount = false;
        for (int j : amounts) {
            if (!usedTypeAmount) {
                j += typeAmount[0];
            }

            if (j == 5) {
                type = Hand.FIVE_OF_A_KIND;
                break;
            }
            if (j == 4) {
                type = Hand.FOUR_OF_A_KIND;
                break;
            }
            if (j == 3) {
                if (type == Hand.ONE_PAR) {
                    type = Hand.FULL_HOUSE;
                } else {
                    type = Hand.THREE_OF_A_KIND;
                }
                usedTypeAmount = true;
            }
            if (j == 2) {
                if (type == Hand.ONE_PAR) {
                    type = Hand.TWO_PAR;
                } else if (type == Hand.THREE_OF_A_KIND) {
                    type = Hand.FULL_HOUSE;
                } else {
                    type = Hand.ONE_PAR;
                }
                usedTypeAmount = true;
            }
        }
        System.out.println();
    }

    @Override
    public int compareTo(Hand card) {
        if (card.type != this.type) {
            return Integer.compare(card.type, this.type);
        }
        for (int i = 0; i < 5; i++) {
            if (card.hand[i] != this.hand[i]) {
                return Integer.compare(card.hand[i], this.hand[i]);
            }
        }
        return 0;
    }
    
    public void setRank(long rank) {
        this.rank = rank;
    }
    
    public long getScore() {
        return bid * rank;
    }
}
