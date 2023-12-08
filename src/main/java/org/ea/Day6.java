package org.ea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

public class Day6 {

    public static void main(String[] args) {

        String text = """
Time:      7  15   30
Distance:  9  40  200
Time:        40     70     98     79
Distance:   215   1051   2147   1005
                """;



        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day6.txt"));
            long val = 0;
/*
            for (String line :  br.lines().toList()) { //text.split("\n")) {//
                System.out.println(line);
            }
 */
            val = numOfTimesToBeat(71530, new BigInteger("940200"));
            System.out.println(val);


            val = numOfTimesToBeat(40709879, new BigInteger("215105121471005"));
            System.out.println(val);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long numOfTimesToBeat(long time, BigInteger record) {
        long win = 0;
        for (int i = 0; i < time; i++) {
            BigInteger timeLeft = BigInteger.valueOf(time - i);
            if (timeLeft.multiply(BigInteger.valueOf(i)).compareTo(record) > 0) {
                win++;
            }
        }
        return win;
    }
}