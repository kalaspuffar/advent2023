package org.ea.day5;

import java.util.HashMap;
import java.util.Map;

public class Converter {

    private String source;
    private String destination;

    private class Range {
        public long start;
        public long end;
    }

    private Map<Range, Range> ranges = new HashMap<>();

    public Converter(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void addRange(String range) {
        String[] parts = range.trim().split(" ");
        Range source = new Range();
        Range destination = new Range();
        long dest = Long.parseLong(parts[0]);
        long src = Long.parseLong(parts[1]);
        long len = Long.parseLong(parts[2]);

        destination.start = dest;
        destination.end = dest + len - 1;
        source.start = src;
        source.end = src + len - 1;

        ranges.put(source, destination);
    }

    public long getDestionationValue(long value) {
        for (Map.Entry<Range, Range> entry : ranges.entrySet()) {
            Range source = entry.getKey();
            Range destination = entry.getValue();
            if (value >= source.start && value <= source.end) {
                long point = value - source.start;
                return destination.start + point;
            }
        }
        return value;
    }
}
