package org.ea.day16;

import org.ea.day14.Point;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Beam {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;

    public static final int SIZE = 110;

    private int x;
    private int y;
    private int dir;

    class BeamPoint {
        private int x;
        private int y;

        public BeamPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BeamPoint beamPoint = (BeamPoint) o;
            return x == beamPoint.x && y == beamPoint.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    Set<BeamPoint> visited = new HashSet<>();
    private boolean done = false;

    public Beam(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        visited.add(new BeamPoint(x, y));
    }

    public void move(int dir) {
        if (dir == NORTH) {
            this.dir = NORTH;
            y--;
        }
        if (dir == SOUTH) {
            this.dir = SOUTH;
            y++;
        }
        if (dir == EAST) {
            this.dir = EAST;
            x++;
        }
        if (dir == WEST) {
            this.dir = WEST;
            x--;
        }

        BeamPoint newPoint = new BeamPoint(x, y);
        visited.add(newPoint);

        if (x < 0 || y < 0 || x > SIZE - 1 || y > SIZE - 1) {
            done = true;
        }
    }

    public boolean isDone() {
        return done;
    }

    public int getDir() {
        return dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beam beam = (Beam) o;
        return x == beam.x && y == beam.y && dir == beam.dir;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, dir);
    }

    public boolean hasVisited(int x, int y) {
        return visited.contains(new BeamPoint(x, y));
    }
}
