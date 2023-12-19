package org.ea.day17;

import org.ea.Day17;

import java.util.List;

public class Crucible {
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int WEST = 2;
    public static final int EAST = 3;
    private boolean processed = false;
    private final int x;
    private final int y;
    private final int dir;
    private final int consecutiveMoves;
    private final long moves;

    public Crucible(int x, int y, int dir, long moves, int consecutiveMoves) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moves = moves;
        this.consecutiveMoves = consecutiveMoves;
    }

    private final int MIN = 3;
    private final int MAX = 10;

    public void calculateNeighbours(List<String> heatMap, List<Crucible>[] map, int width) {
        if (dir == NORTH) {
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x + 1, y, EAST, 1);
            }
            if (consecutiveMoves < MAX) {
                doMove(heatMap, map, width, x, y - 1, NORTH, consecutiveMoves + 1);
            }
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x - 1, y, WEST, 1);
            }
        }
        if (dir == SOUTH) {
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x + 1, y, EAST, 1);
            }
            if (consecutiveMoves < MAX) {
                doMove(heatMap, map, width, x, y + 1, SOUTH, consecutiveMoves + 1);
            }
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x - 1, y, WEST, 1);
            }
        }
        if (dir == EAST) {
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x, y - 1, NORTH, 1);
            }
            if (consecutiveMoves < MAX) {
                doMove(heatMap, map, width, x + 1, y, EAST, consecutiveMoves + 1);
            }
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x, y + 1, SOUTH, 1);
            }
        }
        if (dir == WEST) {
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x, y - 1, NORTH, 1);
            }
            if (consecutiveMoves < MAX) {
                doMove(heatMap, map, width, x - 1, y, WEST, consecutiveMoves + 1);
            }
            if (consecutiveMoves > MIN) {
                doMove(heatMap, map, width, x, y + 1, SOUTH, 1);
            }
        }
        processed = true;
    }

    private void doMove(List<String> heatMap, List<Crucible>[] map, int size, int newX, int newY, int newDir, int co) {
        if (newX < 0 || newY < 0 || newX > size - 1 || newY > size - 1) {
            return;
        }
        int nextHeat = Integer.parseInt(heatMap.get(newY).substring(newX, newX+1));

        for (Crucible c : map[newY * size + newX]) {
            if (c.getConsecutiveMoves() == co && c.getMoves() == moves + nextHeat && c.getDir() == newDir) {
                return;
            }
        }

        if (
            map[newY * size + newX].isEmpty() ||
            map[newY * size + newX].get(0).getMoves() + MAX > moves + nextHeat
        ) {
            map[newY * size + newX].add(new Crucible(newX, newY, newDir, moves + nextHeat, co));
            Day17.doneMap[newY * size + newX] = false;
        }
    }

    public long getMoves() {
        return moves;
    }

    public int getConsecutiveMoves() {
        return consecutiveMoves;
    }

    public int getDir() {
        return dir;
    }

    public boolean isProcessed() {
        return processed;
    }
}
