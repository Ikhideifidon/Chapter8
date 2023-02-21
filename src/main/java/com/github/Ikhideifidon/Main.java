package com.github.Ikhideifidon;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        float[] A = {0.78F, 0.17F, 0.39F, 0.26F, 0.72F, 0.94F, 0.21F, 0.12F, 0.23F, 0.68F};
        LinearSorting.bucketSort(A);
        System.out.println(Arrays.toString(A));
    }
}
