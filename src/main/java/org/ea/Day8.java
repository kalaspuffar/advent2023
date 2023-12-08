package org.ea;

import org.ea.day8.Branch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {
    public static void main(String[] args) {
        String text = """
LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)
""";


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day8.txt"));
            long val = 0;

            Map<String, Branch> map = new HashMap<>();

            String command = null;
            for (String line : br.lines().toList()) { // text.split("\n")) {//
                if (line.isBlank()) continue;
                if (command == null) {
                    command = line;
                    continue;
                }
                Branch branch = new Branch(line);
                map.put(branch.getName(), branch);
            }

            List<Branch> currentList = new ArrayList<>();
            for (Branch b : map.values()) {
                b.updateMap(map);
                if (b.getName().endsWith("A")) {
                    currentList.add(b);
                }
            }

            for (Branch b : map.values()) {
                Branch work = b;
                for (String c : command.split("")) {
                    switch (c) {
                        case "R":
                            work = work.getRight();
                            break;
                        case "L":
                            work = work.getLeft();
                            break;
                    }
                }
                b.setNext(map.get(work.getName()));
            }

            boolean working = true;

            //Branch current = map.get("AAA");

            List<Branch> newList = currentList;
            long iterations = 0;
            val += 1_000_000_000;

            while(working) {
                int found = 0;

                currentList = newList;
                newList = new ArrayList<>();

                for (Branch current : currentList) {
                    Branch next = current.getNext();
                    if (next.isEndsWithZ()) {
                        found++;
                    }
                    newList.add(next);
                }

                iterations += command.length();

                if (iterations > val) {
                    System.out.println(iterations);
                    val += 1_000_000_000;
                }

                if (found == currentList.size()) {
                    working = false;
                }
            }

            System.out.println(iterations);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
