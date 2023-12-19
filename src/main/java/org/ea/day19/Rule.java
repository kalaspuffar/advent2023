package org.ea.day19;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private static final int GREATER = 1;
    private static final int SMALLER = 2;

    private String variable;
    private int number;

    private int operation = -1;

    private Rule trueRule;
    private Rule falseRule;

    public String endArgument = null;
    public Rule(String line) {
        int argumentSplit = line.indexOf(":");
        if (argumentSplit == -1) {
            endArgument = line;
            return;
        }

        variable = line.substring(0, 1);
        if (line.substring(1, 2).equals(">")) {
            operation = GREATER;
        } else {
            operation = SMALLER;
        }

        number = Integer.parseInt(line.substring(2, argumentSplit));

        int ruleSplit = line.indexOf(",");

        trueRule = new Rule(line.substring(argumentSplit + 1, ruleSplit));
        falseRule = new Rule(line.substring(ruleSplit + 1));

        // a<2006:qkq,m>2090:A,rfg
    }

    public String evaluate(Part p) {
        if (endArgument != null) {
            return endArgument;
        }

        if (operation == GREATER) {
            if (p.getVar(variable) > number) {
                return trueRule.evaluate(p);
            } else {
                return falseRule.evaluate(p);
            }
        } else {
            if (p.getVar(variable) < number) {
                return trueRule.evaluate(p);
            } else {
                return falseRule.evaluate(p);
            }
        }
    }

    public List<FigPart> evaluate(FigPart fp) {
        List<FigPart> newList = new ArrayList<>();
        if (endArgument == null) {
            if (operation == GREATER) {
                FigPart trueFP = new FigPart(fp);
                trueFP.addGreater(variable, number);
                FigPart falseFP = new FigPart(fp);
                falseFP.addSmaller(variable, number);
                newList.addAll(trueRule.evaluate(trueFP));
                newList.addAll(falseRule.evaluate(falseFP));
            } else {
                FigPart trueFP = new FigPart(fp);
                trueFP.addSmaller(variable, number - 1);
                FigPart falseFP = new FigPart(fp);
                falseFP.addGreater(variable, number - 1);
                newList.addAll(trueRule.evaluate(trueFP));
                newList.addAll(falseRule.evaluate(falseFP));
            }
        } else if (endArgument.equals("A")) {
            newList.add(fp);
        } else if (endArgument.equals("R")) {
        } else {
            fp.setNextRule(endArgument);
            newList.add(fp);
        }
        return newList;
    }
}
