package org.ea.day13;

import java.util.ArrayList;
import java.util.List;

public class Mirror {

    private List<String> orgHorizontal = new ArrayList<>();
    private List<String> orgVertical = new ArrayList<>();

    private List<String> horizontal = new ArrayList<>();
    private List<String> vertical = new ArrayList<>();

    public void addLine(String line) {
        horizontal.add(line);
        orgHorizontal.add(line);
    }

    public void flip() {
        for(int i = 0; i < horizontal.get(0).length(); i++) {
            String current = "";
            for (String s : horizontal) {
                current += s.substring(i, i+1);
            }
            vertical.add(current);
        }
        orgVertical.addAll(vertical);
    }

    public int getWidth() {
        return horizontal.get(0).length();
    }
    public int getHeight() {
        return horizontal.size();
    }

    public void flipXY(int x, int y) {
        horizontal.clear();;
        vertical.clear();
        horizontal.addAll(orgHorizontal);
        vertical.addAll(orgVertical);

        String line = horizontal.get(y);
        String val = line.substring(x, x+1);
        String s = line.substring(0, x);
        s += val.equals("#") ? "." : "#";
        s += line.substring(x+1);
        horizontal.set(y, s);

        line = vertical.get(x);
        val = line.substring(y, y+1);
        s = line.substring(0, y);
        s += val.equals("#") ? "." : "#";
        s += line.substring(y+1);
        vertical.set(x, s);
    }

    public long getSum(long current) {
        int oldRow = (int)current / 100;
        int oldCol = (int)current % 100;

        int row = findMatch(horizontal, oldRow);
        int col = 0;
        if (row == 0 || oldRow == row)
            col = findMatch(vertical, oldCol);
        return row * 100 + col;
    }

    private int findMatch(List<String> strings, int oldval) {
        List<Integer> potentials = new ArrayList<>();
        for (int i = 0; i < strings.size() - 1; i++) {
            if (strings.get(i).equals(strings.get(i+1))) {
                potentials.add(i);
            }
        }

        for (int pos : potentials) {
            if (pos == (oldval - 1)) continue;
            int numSteps = Math.min(pos + 1, strings.size() - pos - 1);

            boolean found = true;
            for (int i = 0; i < numSteps; i++) {
                String first = strings.get(pos + i + 1);
                String second = strings.get(pos - i);
                if (!first.equals(second)) {
                    found = false;
                    break;
                }
            }

            if (found) {
                return pos + 1;
            }
        }

        return 0;
    }
}
