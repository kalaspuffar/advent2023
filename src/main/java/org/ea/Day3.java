package org.ea;

import org.ea.day3.NumberPos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static int MAP_SIZE = 200;

    public static void main(String[] args) {

        String text = """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
""";


        Map<String, Integer> characters = new HashMap<>();
        characters.put("*", 1);
        characters.put("#", 2);
        characters.put("+", 3);
        characters.put("$", 4);
        characters.put("&", 5);
        characters.put("-", 6);
        characters.put("%", 7);
        characters.put("=", 8);
        characters.put("/", 9);
        characters.put("@", 10);

        int[] map = new int[MAP_SIZE * MAP_SIZE];

        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day3.txt"));
            int val = 0;
            int x = 0;
            int y = 0;
            List<NumberPos> list = new ArrayList<>();
            NumberPos np = null;

            Arrays.fill(map, 0);

            for (String line : br.lines().toList()) { // text.split("\n")) {//
                for (String c :  line.split("")) {
                    if (Character.isDigit(c.charAt(0))) {
                        if (np == null) {
                            np = new NumberPos();
                        }
                        np.addNumber(x, y, c);
                    } else {
                        if (np != null) {
                            list.add(np);
                        }
                        np = null;
                    }

                    if (characters.containsKey(c)) {
                        map[y * MAP_SIZE + x] = characters.get(c);
                    } else if (!c.equals(".") && !Character.isDigit(c.charAt(0))) {
                        System.out.println(c);
                    }
                    x++;
                }
                x = 0;
                y++;
            }

            printMap(map);

            for (NumberPos np2 : list) {
                np2.findGears(map);
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    val += list.get(i).getGear(list.get(j));
                }
            }

            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printMap(int[] map) {
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                if (map[y * MAP_SIZE + x] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(map[y * MAP_SIZE + x]);
                }
            }
            System.out.println();
        }
    }
}