package com.github.Ikhideifidon;

public class LinearSorting {
    /**
     * Given an array of integers in the range of 0 and some value k,
     * where k is not too large (k = O(n)), counting sort sorts the array in a time proportional to n + k.
     *
     * @param A : Non-negative integer array
     */
    public static int[] countingSort(int[] A) {
        // A = [1, 0, 3, 1, 3, 1]
        if (A == null) return null;
        int n = A.length;
        if (n <= 1) return A;
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
        return B;
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
}
