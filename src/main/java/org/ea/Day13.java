package org.ea;

import org.ea.day11.Galaxy;
import org.ea.day13.Mirror;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public static void main(String[] args) {

        String text = """
#.##..##.
..#.##.#.
##......#
##......#
..#.##.#.
..##..##.
#.#.##.#.

#...##..#
#....#..#
..##..###
#####.##.
#####.##.
..##..###
#....#..#
""";


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day13.txt"));
            long val = 0;

            Mirror current = new Mirror();
            for (String line : br.lines().toList()) { //text.split("\n")) {//
                if (line.isBlank()) {
                    current.flip();

                    val += getVal(current);

                    current = new Mirror();
                    continue;
                }
                current.addLine(line);
            }
            current.flip();
            val += getVal(current);

            System.out.println(val);

            // 31871 low
            // 39097 low
            // 39236 low
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long getVal(Mirror current) {
        long currentVal = current.getSum(0);

        for (int y = 0; y < current.getHeight(); y++) {
            for (int x = 0; x < current.getWidth(); x++) {
                current.flipXY(x, y);
                long newVal = current.getSum(currentVal);
                if (newVal != currentVal && newVal != 0) {
                    return newVal;
                }
            }
        }

        System.out.println("ERR ");
        return 0;
    }
}
