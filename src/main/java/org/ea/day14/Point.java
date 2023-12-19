package org.ea.day14;

import java.io.File;
import java.util.Objects;
import java.util.Set;

public class Point implements Comparable<Point> {
    public static final int SIZE = 100;
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveNorth(Set<Point> immovable) {
        Point checkPoint = new Point(x, y - 1);
        immovable.remove(this);
        while(y > 0 && !immovable.contains(checkPoint)) {
            y--;
            checkPoint = new Point(x, y - 1);
        }
        immovable.add(this);
    }

    public void moveSouth(Set<Point> immovable) {
        Point checkPoint = new Point(x, y + 1);
        immovable.remove(this);
        while(y < SIZE - 1 && !immovable.contains(checkPoint)) {
            y++;
            checkPoint = new Point(x, y + 1);
        }
        immovable.add(this);
    }

    public void moveWest(Set<Point> immovable) {
        Point checkPoint = new Point(x - 1, y);
        immovable.remove(this);
        while(x > 0 && !immovable.contains(checkPoint)) {
            x--;
            checkPoint = new Point(x - 1, y);
        }
        immovable.add(this);
    }

    public void moveEast(Set<Point> immovable) {
        Point checkPoint = new Point(x + 1, y);
        immovable.remove(this);
        while(x < SIZE - 1 && !immovable.contains(checkPoint)) {
            x++;
            checkPoint = new Point(x + 1, y);
        }
        immovable.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Point point) {
        if (point.y != this.y) {
            return Integer.compare(point.y, this.y);
        }
        return Integer.compare(point.x, this.x);
    }

    public int getValue() {
        return SIZE - y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public static void main(String[] args) {
        File dir = new File("day14data");
        long largest = 0;
        for (String s : dir.list()) {
            String[] name = s.split("\\.");
            long newVal = Long.parseLong(name[0]);
            if (largest < newVal) {
                largest = newVal;
            }
        }

        String filename = "" + largest + ".dat";
        System.out.println(filename);
    }
}
