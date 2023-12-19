package org.ea;

import org.ea.day16.Beam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day16 {
    private static List<Beam> beams = new ArrayList<>();
    private static List<Beam> doneBeams = new ArrayList<>();


    public static void main(String[] args) {

        String text = """
.|...\\....
|.-.\\.....
.....|-...
........|.
..........
.........\\
..../.\\\\..
.-.-/..|..
.|....-|.\\
..//.|....                
                """;


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day16.txt"));
            long val = 0;

            List<String> lines = new ArrayList<>();

            for (String line : br.lines().toList()) { //text.split("\n")) {//
                if (line.isBlank()) continue;

                lines.add(line);
            }

            long largest = 0;

            // 51
            // 49
            // 48
            // 48

            // 7269
            // 7418
            // 7293
            // 7530

            for (int i = 0; i < Beam.SIZE; i++) {
                beams = new ArrayList<>();
                doneBeams = new ArrayList<>();
                beams.add(new Beam(i, 0, Beam.SOUTH));

                long current = 0;

                while (!beams.isEmpty()) {
                    Iterator<Beam> it = beams.iterator();
                    while (it.hasNext()) {
                        Beam b = it.next();
                        if (handleSpot(b, lines)) {
                            break;
                        }
                        if (b.isDone()) {
                            beams.remove(b);
                            doneBeams.add(b);
                            break;
                        }
                    }
                }

                for (int y = 0; y < Beam.SIZE; y++) {
                    for (int x = 0; x < Beam.SIZE; x++) {
                        for (Beam b : doneBeams) {
                            if (b.hasVisited(x, y)) {
                                current++;
                                break;
                            }
                        }
                    }
                }
                if (current > largest) {
                    largest = current;
                }
            }

            /*
            for (int y = 0; y < Beam.SIZE; y++) {
                for (int x = 0; x < Beam.SIZE; x++) {
                    boolean found = false;
                    for (Beam b : doneBeams) {
                        if (b.hasVisited(x, y)) {
                            found = true;
                            break;
                        }
                    }
                    System.out.print(found ? "#" : ".");
                }
                System.out.println("");
            }
            */

            System.out.println(largest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean handleSpot(Beam b, List<String> lines) {
        String c = lines.get(b.getY()).substring(b.getX(), b.getX() + 1);
        if (c.equals(".")) {
            b.move(b.getDir());
        } else if (c.equals("-")) {
            if (b.getDir() == Beam.EAST || b.getDir() == Beam.WEST) {
                b.move(b.getDir());
            } else {
                beams.remove(b);
                if (doneBeams.contains(b)) {
                    doneBeams.add(b);
                    return true;
                }
                doneBeams.add(b);
                beams.add(new Beam(b.getX(), b.getY(), Beam.EAST));
                beams.add(new Beam(b.getX(), b.getY(), Beam.WEST));
                return true;
            }
        } else if (c.equals("|")) {
            if (b.getDir() == Beam.NORTH || b.getDir() == Beam.SOUTH) {
                b.move(b.getDir());
            } else {
                beams.remove(b);
                if (doneBeams.contains(b)) {
                    doneBeams.add(b);
                    return true;
                }
                doneBeams.add(b);
                beams.add(new Beam(b.getX(), b.getY(), Beam.NORTH));
                beams.add(new Beam(b.getX(), b.getY(), Beam.SOUTH));
                return true;
            }
        } else if (c.equals("/")) {
            switch (b.getDir()) {
                case Beam.NORTH: b.move(Beam.EAST); break;
                case Beam.SOUTH: b.move(Beam.WEST); break;
                case Beam.EAST: b.move(Beam.NORTH); break;
                case Beam.WEST: b.move(Beam.SOUTH); break;
            }
        } else if (c.equals("\\")) {
            switch (b.getDir()) {
                case Beam.NORTH: b.move(Beam.WEST); break;
                case Beam.SOUTH: b.move(Beam.EAST); break;
                case Beam.EAST: b.move(Beam.SOUTH); break;
                case Beam.WEST: b.move(Beam.NORTH); break;
            }
        }
        return false;
    }

}
