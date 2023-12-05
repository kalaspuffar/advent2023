package org.ea.day5;

import java.util.Map;

public class TestThread implements Runnable {

    private Map<String, Converter> converters;
    private long start;
    private long len;

    public TestThread(long start, long len, Map<String, Converter> converters) {
        this.start = start;
        this.len = len;
        this.converters = converters;
    }

    @Override
    public void run() {
        long smallestValue = Long.MAX_VALUE;

        for (long seed = start; seed < start + len; seed++) {
            String destination = "seed";
            long value = seed;
            while (!destination.equals("location")) {
                Converter currentConvert = converters.get(destination);
                destination = currentConvert.getDestination();
                value = currentConvert.getDestionationValue(value);
            }

            if (value < smallestValue) {
                smallestValue = value;
            }
        }
        System.out.println(smallestValue);
    }
}
