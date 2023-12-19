package org.ea.day19;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part {
    int x, m, a, s = 0;

    Pattern p = Pattern.compile("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}");

    public Part(String line) {
        Matcher m = p.matcher(line);

        if (m.find()) {
            this.x = Integer.parseInt(m.group(1));
            this.m = Integer.parseInt(m.group(2));
            this.a = Integer.parseInt(m.group(3));
            this.s = Integer.parseInt(m.group(4));
        }
    }

    public int getVar(String str) {
        switch(str) {
            case "x": return x;
            case "m": return m;
            case "a": return a;
            case "s": return s;
        }
        return 0;
    }

    public long getValue() {
        return x + m + a + s;
    }
}
