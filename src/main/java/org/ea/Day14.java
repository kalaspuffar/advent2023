package org.ea;

import org.ea.day13.Mirror;
import org.ea.day14.Point;

import java.io.*;
import java.util.*;

public class Day14 {
    public static void main(String[] args) {

        String text = """
O....#....
O.OO#....#
.....##...
OO.#O....O
.O.....O#.
O.#..O.#.#
..O..#O..O
.......O..
#....###..
#OO..#....
""";


        try {
            File dir = new File("day14data");
            dir.mkdir();

            int largest = 0;
            for (String s : dir.list()) {
                String[] name = s.split("\\.");
                int newVal = Integer.parseInt(name[0]);
                if (largest < newVal) {
                    largest = newVal;
                }
            }

            File workfile = new File("input/day14.txt");
            if (largest > 0) {
                workfile = new File(dir, "" + largest + ".dat");
            }

            BufferedReader br = new BufferedReader(new FileReader(workfile));
            long val = 0;

            List<Point> movable = new ArrayList<>();
            Set<Point> immovable = new HashSet<>();

            int x = 0;
            int y = 0;
            for (String line : br.lines().toList()) { // text.split("\n")) {//
                if (line.isBlank()) continue;
                x = 0;
                for (String c : line.split("")) {
                    if (c.equals("#")) {
                        immovable.add(new Point(x, y));
                    } else if (c.equals("O")) {
                        Point newPoint = new Point(x, y);
                        immovable.add(newPoint);
                        movable.add(newPoint);
                    }
                    x++;
                }
                y++;
            }

            for (int i = largest; i < 1_000_000_000; i++) {
                if (i % 1_000_000 == 0) {
                    System.out.println(i);
                    File file = new File(dir,"" + i + ".dat");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    writeMap(bw, movable, immovable);
                    bw.flush();
                    bw.close();
                }

                Collections.sort(movable, Comparator.comparingInt(Point::getY));
                for (Point p : movable) {
                    p.moveNorth(immovable);
                }

                Collections.sort(movable, Comparator.comparingInt(Point::getX));
                for (Point p : movable) {
                    p.moveWest(immovable);
                }

                Collections.sort(movable, (a, b) -> b.getY() - a.getY());
                for (Point p : movable) {
                    p.moveSouth(immovable);
                }

                Collections.sort(movable, (a, b) -> b.getX() - a.getX());
                for (Point p : movable) {
                    p.moveEast(immovable);
                }
            }

            printMap(movable, immovable);

            for (Point p : movable) {
                val += p.getValue();
            }

            System.out.println(val);

/*
            List<Integer> initialList = new ArrayList<>();
            List<Integer> window = new ArrayList<>();
            List<Integer> smallWindow = new ArrayList<>();

            for (int i = 0; i < 1_000_000; i++) {
                tiltNorth(lines);
                lines = flipCounterClock(lines);
                int v = calculate(lines);
                if (i < 100_000) {
                    initialList.add(v);
                } else if (window.isEmpty()) {
                    window.add(v);
                    smallWindow.add(v);
                } else if (smallWindow.size() < 5) {
                    window.add(v);
                    smallWindow.add(v);
                } else {
                    if (
                        window.size() > 5 &&
                        window.get(0) == smallWindow.get(0) &&
                        window.get(1) == smallWindow.get(1) &&
                        window.get(2) == smallWindow.get(2) &&
                        window.get(3) == smallWindow.get(3) &&
                        window.get(4) == smallWindow.get(4) &&
                        window.get(5) == v
                    ) {
                        break;
                    }
                    window.add(v);
                    smallWindow.add(v);
                    smallWindow.remove(0);
                }
            }

            int index = initialList.size();

            System.out.println(window.size());

            while (index < 1_000_000_000) {
                index += (window.size() - 5);
            }
            index -= (window.size() - 5);

            System.out.println(window.get(999_999_998 - index));
            System.out.println(window.get(999_999_999 - index));
            System.out.println(window.get(1_000_000_000 - index));
            System.out.println(window.get(1_000_000_001 - index));
            System.out.println(window.get(1_000_000_002 - index));
            System.out.println(window.get(1_000_000_003 - index));
*/
            //91261 low
            //97115 low
            //102279 not right
            //102471 not right
            //103198 high

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeMap(BufferedWriter bw, List<Point> movable, Set<Point> immovable) throws Exception {
        for (int y = 0; y < Point.SIZE; y++) {
            for (int x = 0; x < Point.SIZE; x++) {
                if (movable.contains(new Point(x, y))) {
                    bw.write("O");
                } else if (immovable.contains(new Point(x, y))) {
                    bw.write("#");
                } else {
                    bw.write(".");
                }
            }
            bw.newLine();
        }
    }

    private static void printMap(List<Point> movable, Set<Point> immovable) {
        for (int y = 0; y < Point.SIZE; y++) {
            for (int x = 0; x < Point.SIZE; x++) {
                if (movable.contains(new Point(x, y))) {
                    System.out.print("O");
                } else if (immovable.contains(new Point(x, y))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    private static List<String> flipCounterClock(List<String> lines) {
        List<String> newList = new ArrayList<>();
        for (int x = 0; x < lines.get(0).length(); x++) {
            String newStr = "";
            for (int y = lines.size() - 1; y > -1; y--) {
                newStr += lines.get(y).substring(x, x+1);
            }
            newList.add(newStr);
        }
        return newList;
    }

    private static int calculate(List<String> lines) {
        int val = 0;
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(0).length(); x++) {
                String c = lines.get(y).substring(x, x+1);
                if (c.equals("O")) {
                    val += lines.size() - y;
                }
            }
        }
        return val;
    }

    private static void setCharAt(List<String> lines, int x,  int y, String val) {
        String s = lines.get(y).substring(0, x);
        s += val;
        s += lines.get(y).substring(x+1);
        lines.set(y, s);
    }

    private static void tiltNorth(List<String> lines) {
        for (int y = 1; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(0).length(); x++) {
                String c = lines.get(y).substring(x, x+1);
                if (c.equals("O")) {
                    setCharAt(lines, x, y, ".");
                    for (int i = y - 1; i > -1; i--) {
                        String v = lines.get(i).substring(x, x+1);
                        if(v.equals("#") || v.equals("O")) {
                            setCharAt(lines, x, i+1, "O");
                            break;
                        }
                        if(i == 0 && v.equals(".")) {
                            setCharAt(lines, x, 0, "O");
                        }
                    }
                }
            }
        }
    }
}
