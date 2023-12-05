package org.ea;

import org.ea.day4.Card;
import org.ea.day5.Converter;
import org.ea.day5.TestThread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Day5 {

    public static void main(String[] args) {

        String text = """
seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4
                """;


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day5.txt"));
            int val = 0;

            Map<String, Converter> converters = new HashMap<>();
            List<Long> seeds = new ArrayList<>();

            Converter currentConverter = null;

            for (String line : br.lines().toList()) { // text.split("\n")) {//
                if (line.isBlank()) continue;
                if (line.startsWith("seeds:")) {
                    for (String seed : line.replace("seeds:", "").trim().split(" ")) {
                        seeds.add(Long.parseLong(seed));
                    }
                } else {
                    if(line.matches("^\\d.*")) {
                        currentConverter.addRange(line);
                    } else {
                        String[] parts = line.split("-");
                        currentConverter = new Converter(parts[0], parts[2].replace("map:", "").trim());
                        converters.put(parts[0], currentConverter);
                    }
                }
            }

            ExecutorService fixedPool = Executors.newFixedThreadPool(10);

            for (int i = 0; i < seeds.size(); i += 2) {
                long start = seeds.get(i);
                long len = seeds.get(i + 1);
                fixedPool.submit(new TestThread(start, len, converters));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}