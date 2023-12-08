package org.ea.day8;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Branch {
    private Branch left;
    private Branch right;
    //AAA = (BBB, CCC)

    private final String name;
    private final boolean endsWithZ;
    private final String leftStr;
    private final String rightStr;

    private Branch next;

    private static Pattern branchPattern = Pattern.compile(
        "(\\w\\w\\w) = \\((\\w\\w\\w), (\\w\\w\\w)\\)"
    );

    public Branch(String desc) {
        Matcher m = branchPattern.matcher(desc);
        m.find();
        name = m.group(1);
        leftStr = m.group(2);
        rightStr = m.group(3);
        endsWithZ = name.endsWith("Z");
    }

    public void updateMap(Map<String, Branch> map) {
        left = map.get(leftStr);
        right = map.get(rightStr);
    }

    public String getName() {
        return name;
    }

    public Branch getRight() {
        return right;
    }

    public Branch getLeft() {
        return left;
    }

    public void setNext(Branch next) {
        this.next = next;
    }

    public Branch getNext() {
        return next;
    }

    public boolean isEndsWithZ() {
        return endsWithZ;
    }
}
