package org.ea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Day1 {
    public static void main(String[] args) {

        String text = """
two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
""";

        Map<String, String> numbers = new HashMap<>();
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");

        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day1.txt"));
            int val = 0;
            for (String line : br.lines().toList()) { // text.split("\n")) {//
                System.out.println(line);


                int index = 10000;
                while (index == 10000) {
                    String first = "";
                    for (Map.Entry<String, String> entry : numbers.entrySet()) {
                        int pos = line.indexOf(entry.getKey());
                        if (pos != -1 && pos < index) {
                            index = line.indexOf(entry.getKey());
                            first = entry.getKey();
                        }
                    }
                    index = -1;
                    if (numbers.containsKey(first)) {
                        line = line.replaceFirst(first, numbers.get(first));
                        index = 10000;
                    }
                }
                System.out.println(line);

                int first = -1;
                int last = -1;
                for (String c : line.split("")) {
                    if (Character.isDigit(c.charAt(0))) {
                        if (first == -1) {
                            first = Integer.parseInt(c);
                        }
                        last = Integer.parseInt(c);
                    }
                }
                first *= 10;
                val += first + last;
            }
            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}