package com.company;

import com.company.ProcessControlBlockVersionTwo.ProcessControlBlocks2;
import com.company.ProcessControlBlockVersionOne.ProcessControlBlocks;

public class Main {

    public static void main(String[] args) {

        long t1 = test(100000, 1);
        long t2 = test(100000, 2);

        long difference = t1 - t2;

        System.out.println("Version 1 used " + t1 + " milliseconds");
        System.out.println("Version 2 used " + t2 + " milliseconds");


//        output which one took a longer time

        if(difference > 0 ) {
            System.out.println("Version 1 is " + difference + " milliseconds slower");
        }
        else {
            System.out.println("Version 2 is " + difference + " milliseconds slower");
        }

    }

    public static long test(int n, int version) {
        PCBS pcbs;

        if (version == 1) {
            pcbs = new ProcessControlBlocks(100);
        }
        else {
            pcbs = new ProcessControlBlocks2(100);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            pcbs.create(0);
            pcbs.create(0);
            pcbs.create(2);
            pcbs.create(3);
            pcbs.create(0);

            pcbs.destroy(0);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
