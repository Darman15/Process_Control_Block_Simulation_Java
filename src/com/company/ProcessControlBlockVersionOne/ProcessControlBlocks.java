package com.company.ProcessControlBlockVersionOne;

import com.company.PCBS;

import java.util.Arrays;

public class ProcessControlBlocks implements PCBS {

    private PCBEntry[] pcbs;

    public ProcessControlBlocks(int n) {
        pcbs = new PCBEntry[n];
        pcbs[0] = new PCBEntry(-1);
    }

    @Override
    public String create(int p) {
//        first, check if the parameter input here can serve as a parent
        if(pcbs[p] == null) {
            return "No such a parent process";
        }
//        We then need to find a spot in the array that can hold the newly created child. The variables and
//       loop below should allow us to find the nearest empty spot in the array to serve as a child for p
        int count = 1;
        int total = pcbs.length;
        int q = (p + count) % total;
       while(pcbs[q] != null && count < total) {
           count++;
           q = (q + 1) % total;
       }
//        System.out.println("The child will be inserted at position " + q);
       if (count == total) {
           return "No space for new child process";
       }
       PCBEntry child = new PCBEntry(p);
       pcbs[q] = child;
       pcbs[p].children.add(q);
       return null;
    }

    @Override
    public void destroy(int p) {
//        again, check if parameter p exist with pcbs arrayList
        if (pcbs[p] == null) {
            return;
        }
//        iterate through pcbs[p]'s children and remove as long as children remain
        while (!pcbs[p].children.isEmpty()) {
            int q = pcbs[p].children.remove(0);
            destroy(q);
//        set the position n pcbs back to null;
            pcbs[q] = null;
        }
    }

    @Override
    public String toString() {

        String result = "";

        for (int i = 0; i < pcbs.length; i++) {
            if (pcbs[i] != null) {
                result += "Process " + i + " Information: " + pcbs[i].toString() + "\n";
            }

        }
        return result;

    }

    public static void main(String[] args) {

        ProcessControlBlocks pcbs = new ProcessControlBlocks(10);

        pcbs.create(0);
        pcbs.create(0);
        pcbs.create(2);
        pcbs.create(0);
        System.out.println(pcbs);
        pcbs.destroy(0);
        System.out.println("After destroying all children of process 0. pcbs information is: ");
        System.out.println(pcbs);

    }
}
