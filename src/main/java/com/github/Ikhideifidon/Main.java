package com.github.Ikhideifidon;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] A = {995, 992, 993, 995, 993, 999, 998, 992, 990, 991};
        System.out.println(Arrays.toString(LinearSorting.countingSort(A)));

        int[] B = {170, 45, 75, 90, 802, 24, 2, 66};
        LinearSorting.radixSort(B);
        System.out.println(Arrays.toString(B));
    }
}