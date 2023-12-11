package org.ea;

import org.ea.day8.Branch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class Day9 {
    public static void main(String[] args) {
        String text = """
10 13 16 21 30 45
0 3 6 9 12 15
1 3 6 10 15 21
""";


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day9.txt"));
            BigInteger val = BigInteger.ZERO;

            for (String line : br.lines().toList()) { //text.split("\n")) {//
                if (line.isBlank()) continue;
                val = val.add(calcNextValue(line));
            }

            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static BigInteger calcNextValue(String line) {
        Deque<BigInteger[]> series = new ArrayDeque<>();
        String[] lineArr = line.split(" ");
        BigInteger[] longArr = new BigInteger[lineArr.length];
        int i = 0;
        for (String c : lineArr) {
            longArr[i] = new BigInteger(c);
            i++;
        }

        boolean working = true;
        while (working) {
            series.push(longArr);
            BigInteger[] newArr = new BigInteger[longArr.length - 1];
            for (int j = 0; j < newArr.length; j++) {
                newArr[j] = longArr[j + 1].subtract(longArr[j]);
            }
            longArr = newArr;
            working = false;
            for (int k = 0; k < longArr.length; k++) {
                if (!longArr[k].equals(BigInteger.ZERO)) {
                    working = true;
                }
            }
        }

        BigInteger diff = BigInteger.ZERO;
        while(!series.isEmpty()) {
            BigInteger[] arr = series.pop();
            diff = arr[0].subtract(diff);
        }

        return diff;
    }
}

// 1993300041 correct
// 1967891195 high
// 1993474967 high
// 2007041649 high
// 2018412667 high