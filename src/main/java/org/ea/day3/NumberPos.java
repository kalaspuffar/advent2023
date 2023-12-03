package org.ea.day3;

import org.ea.Day3;

import java.util.ArrayList;
import java.util.List;

public class NumberPos {

    private int xStart = -1;
    private int xEnd = -1;

    private int y = -1;

    private int number = 0;
    private String numberStr = "";

    class Gear {
        int x;
        int y;

        public Gear(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private List<Gear> gears = new ArrayList<>();

    public void addNumber(int x, int y, String num) {
        this.y = y;
        if (xStart == -1) {
            this.xStart = x;
        }
        this.xEnd = x;
        numberStr += num;
        number = Integer.parseInt(numberStr);
    }

    public boolean isAdjacent(int[] map) {
        for (int x = xStart; x <= xEnd; x++) {
            if (x < Day3.MAP_SIZE && map[y * Day3.MAP_SIZE + (x + 1)] != 0) return true;
            if (x > 0 && map[y * Day3.MAP_SIZE + (x - 1)] != 0) return true;
            if (y < Day3.MAP_SIZE && map[(y + 1) * Day3.MAP_SIZE + x] != 0) return true;
            if (y > 0 && map[(y - 1) * Day3.MAP_SIZE + x] != 0) return true;
            if (y < Day3.MAP_SIZE && x > 0 && map[(y + 1) * Day3.MAP_SIZE + (x - 1)] != 0) return true;
            if (y > 0 && x > 0 && map[(y - 1) * Day3.MAP_SIZE + (x - 1)] != 0) return true;
            if (y < Day3.MAP_SIZE && x < Day3.MAP_SIZE && map[(y + 1) * Day3.MAP_SIZE + (x + 1)] != 0) return true;
            if (x < Day3.MAP_SIZE && y > 0 && map[(y - 1) * Day3.MAP_SIZE + (x + 1)] != 0) return true;
        }
        return false;
    }

    public void findGears(int[] map) {
        for (int x = xStart; x <= xEnd; x++) {
            if (x < Day3.MAP_SIZE && map[y * Day3.MAP_SIZE + (x + 1)] == 1) {
                gears.add(new Gear(x + 1, y));
            }
            if (x > 0 && map[y * Day3.MAP_SIZE + (x - 1)] == 1) {
                gears.add(new Gear(x - 1, y));
            }
            if (y < Day3.MAP_SIZE && map[(y + 1) * Day3.MAP_SIZE + x] == 1) {
                gears.add(new Gear(x, y + 1));
            }
            if (y > 0 && map[(y - 1) * Day3.MAP_SIZE + x] == 1) {
                gears.add(new Gear(x, y - 1));
            }
            if (y < Day3.MAP_SIZE && x > 0 && map[(y + 1) * Day3.MAP_SIZE + (x - 1)] == 1) {
                gears.add(new Gear(x - 1, y + 1));
            }
            if (y > 0 && x > 0 && map[(y - 1) * Day3.MAP_SIZE + (x - 1)] == 1) {
                gears.add(new Gear(x - 1, y - 1));
            }
            if (y < Day3.MAP_SIZE && x < Day3.MAP_SIZE && map[(y + 1) * Day3.MAP_SIZE + (x + 1)] == 1) {
                gears.add(new Gear(x + 1, y + 1));
            }
            if (x < Day3.MAP_SIZE && y > 0 && map[(y - 1) * Day3.MAP_SIZE + (x + 1)] == 1) {
                gears.add(new Gear(x + 1, y - 1));
            }
        }
    }


    public int getGearShouldBeMoreRight(NumberPos np) {
        int val = 0;
        for (Gear g1 : gears) {
            boolean foundGear = false;
            int gearVal = 0;
            for (Gear g2 : np.gears) {
                if (g1.x == g2.x && g1.y == g2.y) {
                    if (foundGear) {
                        gearVal = 0;
                        break;
                    }
                    gearVal = this.getNumber() * np.getNumber();
                    foundGear = true;
                }
            }
            val += gearVal;
        }
        return val;
    }

    public int getGear(NumberPos np) {
        int val = 0;
        for (Gear g1 : gears) {
            for (Gear g2 : np.gears) {
                if (g1.x == g2.x && g1.y == g2.y) {
                    val = this.getNumber() * np.getNumber();
                }
            }
        }
        return val;
    }

    public int getNumber() {
        return number;
    }
}
