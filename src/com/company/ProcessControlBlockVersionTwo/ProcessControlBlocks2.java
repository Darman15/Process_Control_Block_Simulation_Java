package com.company.ProcessControlBlockVersionTwo;

import com.company.PCBS;

public class ProcessControlBlocks2 implements PCBS {

    private PCBEntry2[] pcbs;

    public ProcessControlBlocks2(int n) {
        pcbs = new PCBEntry2[n];
        pcbs[0] = new PCBEntry2(-1);
    }

    @Override
    public String create(int p) {
//        Check to ensure a parent process of parameter p exist before proceeding
        if (pcbs[p] == null) {
            return "No such parent process";
        }
        int count = 1;
        int total = pcbs.length;
        int q = (p + count) % total;
//      find next spot in the array for the child
        while (pcbs[q] != null && count < total) {
            count++;
            q = (q + 1) % total;
        }
        if (count == total) {
            return "No space for new child process";
        }
        PCBEntry2 newEntry = new PCBEntry2(p);
        pcbs[q] = newEntry;

        int index = pcbs[p].child;
        int prevIndex = -1;
//        if index is -1.  this is the first child
        if (index == -1) {
            pcbs[p].child = q;
        }

        else {
            while (index != -1) {
                prevIndex = index;
                index = pcbs[index].ys;
            }

            pcbs[q].os = prevIndex;
            pcbs[prevIndex].ys = q;

        }
//        System.out.println(Arrays.toString(pcbs));
        return null;
    }

    @Override
    public void destroy(int p) {
        if(p == -1 || pcbs[p] == null) {
            return;
        }
//        instantiates an instance of p's first child, initializes a current variable for use
        int index = pcbs[p].child;
        int current = -1;

//        recursivly loops through deleting p's children
        while (index != -1) {

            current = index;
            index = pcbs[current].ys;
            destroy(current);
            pcbs[current] = null;
        }
//        once this loop has finished, the process p should no longer have any children and is set to -1
        pcbs[p].child = -1;
    }

    @Override
    public String toString() {

        String result = " ";

        for (int i = 0; i < pcbs.length; i++) {
            if (pcbs[i] != null) {
                result += "Process " + i + " Information: " + pcbs[i].toString() + "\n";
            }

        }
        return result;

    }

    public static void main(String[] args) {
        ProcessControlBlocks2 pcbs = new ProcessControlBlocks2(10);

        pcbs.create(0);
        pcbs.create(0);
        pcbs.create(2);
        pcbs.create(0);
        pcbs.create(2);
        pcbs.create(5);
        pcbs.create(3);
        pcbs.create(7);

        System.out.println(pcbs);

        pcbs.destroy(2);
        System.out.println("After destroying all children of process 2, the bcbs information is: \n");
        System.out.println(pcbs);
    }
}
