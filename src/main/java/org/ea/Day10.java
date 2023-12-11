package org.ea;

import org.ea.day10.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

public class Day10 {
    /**
     | is a vertical pipe connecting north and south.
     - is a horizontal pipe connecting east and west.
     L is a 90-degree bend connecting north and east.
     J is a 90-degree bend connecting north and west.
     7 is a 90-degree bend connecting south and west.
     F is a 90-degree bend connecting south and east.
     . is ground; there is no pipe in this tile.
     S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
     */

    private static String NORTH_SOUTH = "|";
    private static String EAST_WEST = "-";
    private static String NORTH_EAST = "L";
    private static String NORTH_WEST = "J";
    private static String SOUTH_WEST = "7";
    private static String SOUTH_EAST = "F";
    private static String GROUND = ".";
    private static String START = "S";

    private static int MAP_SIZE = 140;

    public static void main(String[] args) {
        String text = """
...........
.S-------7.
.|F-----7|.
.||.....||.
.||.....||.
.|L-7.F-J|.
.|..|.|..|.
.L--J.L--J.
...........
""";

        String[] map = new String[MAP_SIZE*MAP_SIZE];

        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day10.txt"));
            long val = 0;

            int x = 0;
            int y = 0;

            boolean[] visit = new boolean[MAP_SIZE*MAP_SIZE];

            int startX = 0;
            int startY = 0;

            for (String line : br.lines().toList()) { // text.split("\n")) {//
                if (line.isBlank()) continue;
                x = 0;
                for (String c : line.split("")) {
                    map[y * MAP_SIZE + x] = c;
                    if (c.equals(START)) {
                        startX = x;
                        startY = y;
                    }
                    x++;
                }
                y++;
            }
/*
            Position northP = new Position(startX, startY);
            northP.move(Position.NORTH);
            while (northP.isCanMove()) {
                northP = loopLength(northP, visit, map);
            }
            Position southP = new Position(startX, startY);
            southP.move(Position.SOUTH);
            while (southP.isCanMove()) {
                southP = loopLength(southP, visit, map);
            }
 */
            Position westP = new Position(startX, startY);
            westP.move(Position.WEST);
            while (westP.isCanMove()) {
                westP = loopLength(westP, visit, map);
            }
/*
            Position eastP = new Position(startX, startY);
            eastP.move(Position.EAST);
            while (eastP.isCanMove()) {
                eastP = loopLength(eastP, visit, map);
            }
 */
            visit[startY * MAP_SIZE + startX] = true;

            printMap(map, visit);

            //System.out.println(northP.getCount());
            //System.out.println(southP.getCount());
            System.out.println(westP.getCount());
            System.out.println(westP.getArea(visit));
            //System.out.println(eastP.getCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Position loopLength(Position p, boolean[] visit, String[] map) {
        int startX = p.getX();
        int startY = p.getY();
        int dir = p.getDirection();
        if (dir == Position.NORTH) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_SOUTH) && !visit[(startY + 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.NORTH);
                return p;
            }
            if (tile.equals(NORTH_EAST) && !visit[startY * MAP_SIZE + startX + 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.EAST);
                return p;
            }
            if (tile.equals(NORTH_WEST) && !visit[startY * MAP_SIZE + startX - 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.WEST);
                return p;
            }
        }

        if (dir == Position.SOUTH) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_SOUTH) && !visit[(startY - 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.SOUTH);
                return p;
            }
            if (tile.equals(SOUTH_EAST) && !visit[startY * MAP_SIZE + startX + 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.EAST);
                return p;
            }
            if (tile.equals(SOUTH_WEST) && !visit[startY * MAP_SIZE + startX - 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.WEST);
                return p;
            }
        }

        if (dir == Position.WEST) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_EAST) && !visit[(startY - 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.SOUTH);
                return p;
            }
            if (tile.equals(SOUTH_EAST) && !visit[(startY + 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.NORTH);
                return p;
            }
            if (tile.equals(EAST_WEST) && !visit[startY * MAP_SIZE + startX - 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.WEST);
                return p;
            }
        }

        if (dir == Position.EAST) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_WEST) && !visit[(startY - 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.SOUTH);
                return p;
            }
            if (tile.equals(SOUTH_WEST) && !visit[(startY + 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.NORTH);
                return p;
            }
            if (tile.equals(EAST_WEST) && !visit[startY * MAP_SIZE + startX + 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.EAST);
                return p;
            }
        }

        p.setCanMove(false);
        return p;
    }

    private static void printMap(String[] map, boolean[] visit) {
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                if (visit[y * MAP_SIZE + x]) {
                    System.out.print(map[y * MAP_SIZE + x]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
