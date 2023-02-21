package com.github.Ikhideifidon;

/**
 * To implement a general-purpose string sort, where strings are not necessarily all the same length,
 * we consider the characters in left-to-right order.
 */
public class MSD {
    // Assuming extended ASCII only.
    private static final int RANGE = 256;
    private static final int CUTOFF = 15;
    private static String[] aux;

    public static void sort(String[] strings) {
        int N = strings.length;
        aux = new String[N];
        MSDSort(strings, 0, strings.length - 1, 0);
    }

    private static void MSDSort(String[] strings, int low, int high, int d) {
        if (high <= low + CUTOFF) {
            insertionSort(strings, low, high, d);
            return;
        }

        int[] count = new int[RANGE + 2];                // +1 for empty string and another +1 for right shift.
        // Compute Frequency counts
        for (int i = low; i <= high; i++)
            count[charAt(strings[i], d) + 2]++;

        // Transform counts to indices
        for (int i = 0; i <= RANGE; i++)
            count[i + 1] += count[i];

        // Distribute
        for (int i = low; i <= high; i++)
            aux[count[charAt(strings[i], d) + 1]++] = strings[i];

        // Copy back
        if (high + 1 - low >= 0)
            System.arraycopy(aux, 0, strings, low, high + 1 - low);

        for (int r = 0; r < RANGE; r++)
            MSDSort(strings, low + count[r], low + count[r + 1] - 1, d + 1);
    }

    private static void insertionSort(String[] strings, int low, int high, int d) {
        for (int i = low; i <= high; i++) {
            int j = i;
            while (j > low && greater(strings[j - 1], strings[j], d)) {
                swap(strings, j, j - 1);
                j--;
            }
        }
    }

    private static void swap(String[] strings, int from, int to) {
        String temp = strings[from];
        strings[from] = strings[to];
        strings[to] = temp;
    }

    private static boolean greater(String first, String second, int d) {
        return first.substring(d).compareTo(second.substring(d)) > 0;
    }


    private static int charAt(String string, int d) {
        if (d >= string.length())
            return -1;
        return string.charAt(d);
    }

}
