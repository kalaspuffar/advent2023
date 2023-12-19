package org.ea.day14;

import java.util.*;

public class Counter {
    public static void main(String[] args) throws Exception {

        for (int i = 1000; i > 0; i -= 21) {
            System.out.println(i);
        }


        /*
        File dir = new File("day14data");
        dir.mkdir();

        List<Integer> values = new ArrayList<>();
        for (String s : dir.list()) {
            String[] name = s.split("\\.");
            values.add(Integer.parseInt(name[0]));
        }

        Collections.sort(values);

        List<Integer> initialList = new ArrayList<>();
        List<Integer> window = new ArrayList<>();
        List<Integer> smallWindow = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            File workfile = new File(dir, "" + values.get(i) + ".dat");
            BufferedReader br = new BufferedReader(new FileReader(workfile));
            int v = calculate(br.lines().toList());
            System.out.println(i + " = " + v);

            if (i < 2) {
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

        while (index < 1_000) {
            index += (window.size() - 5);
        }
        index -= (window.size() - 5);

        System.out.println(window.get(1_000 - index));
         */
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
}
