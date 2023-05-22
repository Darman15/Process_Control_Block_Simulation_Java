package com.company.ProcessControlBlockVersionOne;

import java.util.ArrayList;

public class PCBEntry {

    protected int parent;
    protected ArrayList<Integer> children;


    public PCBEntry(int parent) {
        this.parent = parent;
        children = new ArrayList<Integer>();
    }

    @Override
    public String toString() {
        String result = "The parent is: " + parent + "\n";
        if (children.size() == 0) {
            result += "There is no child";
        }
        else {
            result += "The children are processes: " + children.toString();
        }
        return result;
    }
}
