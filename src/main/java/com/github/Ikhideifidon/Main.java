package com.github.Ikhideifidon;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Item<String, Integer> item = new Item<>("ENG1002138", 4);

        System.out.println(item.count());
        System.out.println(item);
        String[] A = {"d", "a", "c", "f", "f", "b", "d", "b", "f", "b", "e", "a"};
        int W = A[0].length();
        LinearSorting.LSDStringSort(A, W);
        System.out.println(Arrays.toString(A));


        System.out.println("Cinema: ");
        System.out.print(" ");
        for (int i = 1; i < 9; i++) {
            System.out.print(" " + i);
        }

        int i = 1;
        while (i < 8) {
            System.out.println();
            System.out.print(i++);
            for (int j = 1; j < 9; j++)
                System.out.print(" " + "S");
        }
        System.out.println();
        double rand = (Math.random() * 10);
        System.out.println(rand);

        Comparator<Item<String, Integer>> comp = (o1, o2) -> Integer.compare(o2.value(), o1.value());

    }




}
