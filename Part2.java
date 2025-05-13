package com.example.project7_2;

public class Part2 {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        // Call partition and print trace information
        int pivotIndex = partition(a, lo, hi);
        System.out.println("Pivot = " + a[pivotIndex]);
        System.out.println("Partitioned: " + java.util.Arrays.toString(a));

        // Recursive calls for left and right subarrays
        sort(a, lo, pivotIndex - 1);   // Sort left part
        sort(a, pivotIndex + 1, hi);   // Sort right part
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable pivot = a[lo];
        int i = lo + 1;
        int j = hi;

        while (true) {
            while (i <= hi && less(a[i], pivot)) i++;
            while (j >= lo + 1 && less(pivot, a[j])) j--;
            if (i >= j) break;
            exch(a, i, j); // Swap elements
        }

        exch(a, lo, j); // Swap pivot with a[j]
        return j; // Return the index of pivot
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] a = {5, 4, 2, 9, 1, 7, 3, 8, 6};
        System.out.println("Original Array: " + java.util.Arrays.toString(a));
        sort(a);
        System.out.println("\nSorted Array: " + java.util.Arrays.toString(a));
    }
}

