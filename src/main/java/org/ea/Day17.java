package org.ea;

import org.ea.day17.Crucible;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day17 {
    public static boolean[] doneMap;

    public static void main(String[] args) {

        String text = """
2413432311323
3215453535623
3255245654254
3446585845452
4546657867536
1438598798454
4457876987766
3637877979653
4654967986887
4564679986453
1224686865563
2546548887735
4322674655533
                """;


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day17.txt"));
            long val = 0;

            List<String> lines = new ArrayList<>();

            for (String line : br.lines().toList()) { //text.split("\n")) {//
                if (line.isBlank()) continue;

                lines.add(line);
            }

            List<Crucible>[] map = new List[lines.size() * lines.size()];
            doneMap = new boolean[lines.size() * lines.size()];
            for (int i = 0; i < map.length; i++) {
                map[i] = new ArrayList<>();
            }
            map[0].add(new Crucible(0, 0, Crucible.EAST, 0, 0));
            map[0].add(new Crucible(0, 0, Crucible.SOUTH, 0, 0));

            boolean allProcessed = false;
            while (!allProcessed) {
                allProcessed = true;
                outerloop:
                for (int i = 0; i < map.length; i++) {
                    if (doneMap[i]) continue;
                    List<Crucible> list = map[i];
                    for (Crucible c : list) {
                        if (!c.isProcessed()) {
                            c.calculateNeighbours(lines, map, lines.size());
                            allProcessed = false;
                            break outerloop;
                        }
                    }
                    if (!list.isEmpty()) doneMap[i] = true;
                }
            }

            long smallest = Long.MAX_VALUE;
            for (Crucible c : map[map.length - 1]) {
                if(c.getMoves() < smallest) {
                    smallest = c.getMoves();
                }
            }

            System.out.println(smallest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
