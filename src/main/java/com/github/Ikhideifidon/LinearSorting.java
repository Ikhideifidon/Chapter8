package com.github.Ikhideifidon;

public class LinearSorting {
    /**
     * Given an array of integers in the range of 0 and some value k,
     * where k is not too large (k = O(n)), counting sort sorts the array in a time proportional to n + k.
     *
     * @param A : Non-negative integer array
     */
    public static void countingSort(int[] A) {
        // A = [1, 0, 3, 1, 3, 1]
        if (A == null) return;
        int n = A.length;
        if (n <= 1) return;
        int[] min_max = range(A);
        int min = min_max[0];
        int max = min_max[1];

        // Temporary working storage
        int[] C = new int[max - min + 1];
        // C = [0, 0, 0, 0]

        // Modify C to a frequency count of elements in A
        for (int i : A)
            C[i - min]++;
        // C = [1, 3, 0, 2]

        // Add elements in C cumulatively.
        for (int i = 1; i < C.length; i++)
            C[i] = C[i - 1] + C[i];
        // C = [1, 4, 4, 6]

        // Initialize an output array B
        int[] B = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            C[A[i] - min]--;                            // Decrease the respective frequency.
            B[C[A[i] - min]] = A[i];
        }
        System.arraycopy(B, 0, A, 0, n);
    }

    public static void radixSort(int[] A) {
        // Find the maximum number to know the number of digits.
        int max = range(A)[1];
        for (int exp = 1; max / exp > 0; exp *= 10)
            subroutineSort(A, exp);
    }

    private static void subroutineSort(int[] A, int exp) {
        if (A == null) return;
        int n = A.length;
        if (n <= 1) return;

        // Temporary working storage
        int[] C = new int[10];

        // Modify C to a frequency count of elements in A
        for (int num : A)
            C[(num / exp) % 10]++;

        // Add elements in C cumulatively.
        for (int i = 1; i < 10; i++)
            C[i] += C[i - 1];

        // Initialize an output array B
        int[] B = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            C[(A[i] / exp) % 10]--;
            B[C[(A[i] / exp) % 10]] = A[i];
        }
        // Array B is now the new array A.
        System.arraycopy(B, 0, A, 0, n);

    }

    private static int[] range(int[] A) {
        // Assume A.length >= 2
        int maximum = A[0];
        int minimum = A[0];
        for (int num : A) {
            if (num > maximum)
                maximum = num;
            if (num < minimum)
                minimum = num;
        }
        return new int[] {minimum, maximum};
    }

    // Key-indexed counting
    // In this given problem, a teacher has assigned some scores to the matriculation number of
    // some students in a given test. The score ranges from 1 to 20 (inclusive). The teacher would
    // like to sort these students based on their score. Sort these scores in a non-increasing order.
    //
    public static void keyIndexed(Item<String, Integer>[] items, int range) {
        if (items == null) return;
        int N = items.length;
        int[] counts = new int[range + 1];
        int i;

        // Compute frequency counts
        for (i = 0; i < N; i++)
            counts[items[i].value() + 1]++;

        // Transform counts to indices
        for(i = 0; i < range; i++)
            counts[i + 1] += counts[i];

        // Distribute the data
        //noinspection unchecked
        Item<String, Integer>[] aux = new Item[N];
        for (i = 0;  i < N; i++)
            aux[counts[items[i].value()]++] = items[i];
        System.arraycopy(aux, 0, items, 0, N);
    }

    // Suppose that a highway engineer sets up a device that records the license plate numbers
    // of all vehicles using a busy highway for a given period of time and wants to know the
    // number of different vehicles that used the highway.
    // LicencePlate = [
    //                  4PGC938, 2IYE230, 3CIO720, 1ICK750, 1OHV845, 4JZY524, 1ICK750, 3CIO720,
    //                  1OHV845, 1OHV845, 2RLA629, 2RLA629, 3ATW723
    //                ]
    // This is a classical string problem that can be solved by key-indexed count
    // Assuming that the strings are each of length W.
    // This leads to LSD String sort

    public static void LSDStringSort(String[] strings, int W) {
        // W is the size of each string in strings array.
        if (strings == null) return;
        int N = strings.length;
        int range = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            // Sort by key-indexed counting on the dth char

            // Compute the frequency counts.
            int[] counts = new int[range + 1];
            for (String string : strings)
                counts[string.charAt(d) + 1]++;

            // Transform counts to indices
            for (int i = 0; i < range; i++)
                counts[i + 1] += counts[i];

            // Distribute the data
            for (String string : strings)
                aux[counts[string.charAt(d)]++] = string;

            // Copy back
            System.arraycopy(aux, 0, strings, 0, N);
        }

    }



}
