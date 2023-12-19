package org.ea.day19;

import java.math.BigInteger;

public class FigPart {
    private int minX = 0;
    private int maxX = 4000;
    private int minM = 0;
    private int maxM = 4000;
    private int minA = 0;
    private int maxA = 4000;
    private int minS = 0;
    private int maxS = 4000;

    private String nextRule = null;

    public FigPart(String nextRule) {
        this.nextRule = nextRule;
    }

    public FigPart(FigPart fp) {
        this.minX = fp.minX;
        this.maxX = fp.maxX;
        this.minM = fp.minM;
        this.maxM = fp.maxM;
        this.minA = fp.minA;
        this.maxA = fp.maxA;
        this.minS = fp.minS;
        this.maxS = fp.maxS;
    }

    public void setNextRule(String nextRule) {
        this.nextRule = nextRule;
    }

    public String getNextRule() {
        return nextRule;
    }

    public void addGreater(String v, int number) {
        switch (v) {
            case "x": minX = Math.max(minX, number); break;
            case "m": minM = Math.max(minM, number); break;
            case "a": minA = Math.max(minA, number); break;
            case "s": minS = Math.max(minS, number); break;
        }
    }
    public void addSmaller(String v, int number) {
        switch (v) {
            case "x": maxX = Math.min(maxX, number); break;
            case "m": maxM = Math.min(maxM, number); break;
            case "a": maxA = Math.min(maxA, number); break;
            case "s": maxS = Math.min(maxS, number); break;
        }
    }

    public BigInteger getCombinations() {
        BigInteger comb = BigInteger.valueOf(maxX - minX);
        comb = comb.multiply(BigInteger.valueOf(maxM - minM));
        comb = comb.multiply(BigInteger.valueOf(maxA - minA));
        comb = comb.multiply(BigInteger.valueOf(maxS - minS));
        return comb;
    }
}
