package org.ea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {
    public static void main(String[] args) {

        String text = """
Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
""";

        try {

            Pattern pattern = Pattern.compile("(\\d+) (\\w+)");

            BufferedReader br = new BufferedReader(new FileReader("input/day2.txt"));
            int val = 0;
            for (String line : br.lines().toList()) { // text.split("\n")) {//
                String[] arr = line.split(":");
                int gameNum = Integer.parseInt(arr[0].replace("Game", "").trim());
                int blue = 0;
                int green = 0;
                int red = 0;
                for (String rounds : arr[1].split(";")) {
                    for (String kubes : rounds.split(",")) {
                        Matcher m = pattern.matcher(kubes);
                        if (m.find()) {
                            switch (m.group(2)) {
                                case "blue":
                                    int newblue = Integer.parseInt(m.group(1));
                                    if (newblue > blue) {
                                        blue = newblue;
                                    }
                                    break;
                                case "red":
                                    int newred = Integer.parseInt(m.group(1));
                                    if (newred > red) {
                                        red = newred;
                                    }
                                    break;
                                case "green":
                                    int newgreen = Integer.parseInt(m.group(1));
                                    if (newgreen > green) {
                                        green = newgreen;
                                    }
                                    break;
                            }

                       }
                    }
                }
                System.out.println(gameNum + " " + blue + " " + green + " " + red);
                //12 red cubes, 13 green cubes, and 14 blue
                /*
                if (red <= 12 && green <= 13 && blue <= 14) {
                    val += gameNum;
                }
                */
                System.out.println(gameNum + " " + (blue * green * red));
                val += (blue * green * red);
            }

            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}