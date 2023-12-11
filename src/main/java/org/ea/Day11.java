package org.ea;

import org.ea.day11.Galaxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {
    public static void main(String[] args) {

        String text = """
...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#.....
""";

        List<Galaxy> galaxyList = new ArrayList<>();

        int GALAXY_SIZE = 140;

        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day11.txt"));
            long val = 0;
            int x = 0;
            int y = 0;

            int[] ylist = new int[GALAXY_SIZE];
            int[] xlist = new int[GALAXY_SIZE];

            for (String line : br.lines().toList()) { // text.split("\n")) {//
                x = 0;
                for (String c : line.split("")) {
                    if (c.equals("#")) {
                        galaxyList.add(new Galaxy(x, y));
                        xlist[x]++;
                        ylist[y]++;
                    }
                    x++;
                }
                y++;
            }

            for (Galaxy g : galaxyList) {
                g.expand(xlist, ylist);
            }

            for (int i = 0; i < galaxyList.size(); i++) {
                for (int j = i + 1; j < galaxyList.size(); j++) {
                    val += galaxyList.get(i).lengthTo(galaxyList.get(j));
                }
            }

            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
