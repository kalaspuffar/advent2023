package org.ea;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day1 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day1.txt"));
            for (String line : br.lines().toList()) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}