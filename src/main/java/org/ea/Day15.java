package org.ea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

    private static Map<Integer, List<String>> map = new HashMap<>();

    public static void main(String[] args) {

        String text = """
                rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
                """;


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day15.txt"));
            long val = 0;

            for (int i = 0; i < 256; i++) {
                map.put(i, new ArrayList<>());
            }

            for (String line : br.lines().toList()) { //text.split("\n")) {//
                if (line.isBlank()) continue;
                for (String s : line.split(",")) {
                    if (s.contains("=")) {
                        String[] arr = s.split("=");
                        insert(hash(arr[0]), arr[0], s);
                    } else {
                        String[] arr = s.split("-");
                        remove(hash(arr[0]), arr[0]);
                    }
                }
            }

            val = calculateBoxes(map);

            System.out.println(val);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long calculateBoxes(Map<Integer, List<String>> map) {
        long val = 0;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            long boxValue = entry.getKey() + 1;
            List<String> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                String[] operation = str.split("=");
                val += Integer.parseInt(operation[1]) * (i+1) * boxValue;
            }
        }
        return val;
    }

    /*
rn: 1 (box 0) * 1 (first slot) * 1 (focal length) = 1
cm: 1 (box 0) * 2 (second slot) * 2 (focal length) = 4
ot: 4 (box 3) * 1 (first slot) * 7 (focal length) = 28
ab: 4 (box 3) * 2 (second slot) * 5 (focal length) = 40
pc: 4 (box 3) * 3 (third slot) * 6 (focal length) = 72
 */

    private static void insert(int hash, String label, String value) {
        List<String> box = map.get(hash);
        boolean found = false;
        for (int i = 0; i < box.size(); i++) {
            if (box.get(i).startsWith(label + "=")) {
                box.set(i, value);
                found = true;
                break;
            }
        }

        if (!found) {
            box.add(value);
        }
    }

    private static void remove(int hash, String label) {
        List<String> box = map.get(hash);
        for (int i = 0; i < box.size(); i++) {
            if (box.get(i).startsWith(label + "=")) {
                box.remove(i);
                break;
            }
        }
    }

    private static int hash(String hash) {
        int hashVal = 0;
        for (int i = 0; i < hash.length(); i++) {
            hashVal += hash.codePointAt(i);
            hashVal *= 17;
            hashVal %= 256;
        }
        return hashVal;
    }
}
