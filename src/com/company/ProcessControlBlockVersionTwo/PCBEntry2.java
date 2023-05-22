package com.company.ProcessControlBlockVersionTwo;

public class PCBEntry2 {

    protected int parent;
    protected int child;
    protected int ys;
    protected int os;


    public PCBEntry2(int parent) {
        this.parent = parent;
        child = -1;
        ys = -1;
        os = -1;
    }


    @Override
    public String toString() {
       String result = "The parent is: " + parent + "\n";

       if (child == -1) {
           result += "There is no child\n";
       } else {
           result += "The first child is " + child + "\n";
       }

       if (ys == -1) {
           result += "There is no younger sibling\n";
       } else {
           result += "The younger sibling is " + ys + "\n";
       }

       if (os == -1) {
           result += "There is no older sibling " + "\n";
       } else {
           result += "The old sibling is " + os + "\n";
       }

       return result;
    }
}
