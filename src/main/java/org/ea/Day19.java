package org.ea;

import org.ea.day19.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 {

    public static void main(String[] args) {

        String text = """
px{a<2006:qkq,m>2090:A,rfg}
pv{a>1716:R,A}
lnx{m>1548:A,A}
rfg{s<537:gd,x>2440:R,A}
qs{s>3448:A,lnx}
qkq{x<1416:A,crn}
crn{x>2662:A,R}
in{s<1351:px,qqz}
qqz{s>2770:qs,m<1801:hdj,R}
gd{a>3333:R,R}
hdj{m>838:A,pv}

{x=787,m=2655,a=1222,s=2876}
{x=1679,m=44,a=2067,s=496}
{x=2036,m=264,a=79,s=2244}
{x=2461,m=1339,a=466,s=291}
{x=2127,m=1623,a=2188,s=1013}                
                """;


        try {
            BufferedReader br = new BufferedReader(new FileReader("input/day19.txt"));
            long val = 0;

            List<Part> parts = new ArrayList<>();
            Map<String, Rule> ruleMap = new HashMap<>();

            boolean rules = true;
            for (String line : br.lines().toList()) { //text.split("\n")) {//
                if (line.isBlank()) {
                    rules = false;
                    continue;
                }

                if (rules) {
                    String[] arr = line.split("\\{");
                    ruleMap.put(arr[0], new Rule(arr[1].substring(0, arr[1].length() - 1)));
                } else {
                    parts.add(new Part(line));
                }
            }
/*
            for (Part p : parts) {
                String ruleName = "in";
                while (true) {
                    ruleName = ruleMap.get(ruleName).evaluate(p);
                    if (ruleName.equals("R")) {
                        break;
                    } else if (ruleName.equals("A")) {
                        val += p.getValue();
                        break;
                    }
                }
            }
 */

            FigPart fp = new FigPart("in");
            List<FigPart> fpList = new ArrayList<>();
            fpList.add(fp);
            boolean working = true;
            while (working) {
                working = false;
                List<FigPart> newList = new ArrayList<>();
                for (FigPart fp2 : fpList) {
                    String ruleName = fp2.getNextRule();
                    if (ruleName != null) {
                        newList.addAll(ruleMap.get(ruleName).evaluate(fp2));
                        working = true;
                    } else {
                        newList.add(fp2);
                    }
                }
                fpList = newList;
            }

            BigInteger bi = BigInteger.ZERO;
            for (FigPart fp3 : fpList) {
                bi = bi.add(fp3.getCombinations());
            }

            System.out.println(bi);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
