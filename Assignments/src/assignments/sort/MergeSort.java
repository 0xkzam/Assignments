/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 */

package assignments.sort;

/**
 *
 * @author Kasun Amarasena
 */
public class MergeSort {

    /**
     * Merge step of MergeSort algorithm
     * 
     * @param a array of Comparables
     * @param b clone of a
     * @param low lower index
     * @param mid middle index
     * @param high upper index
     */
    public static void merge(Comparable[] a, Comparable[] b, int low, int mid, int high) {

        for (int i = low; i <= high; i++) {
            b[i] = a[i];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = b[j++];
            } else if (j > high) {
                a[k] = b[i++];
            } else if (isGreater(b[i], b[j])) {
                a[k] = b[j++];
            } else {
                a[k] = b[i++];
            }
        }
    }

    private static void sort(Comparable[] a, Comparable[] b, int low, int high) {

        if ((high <= low)) {
            return;
        }
        int mid = low + ((high - low) >>> 1);

        sort(a, b, low, mid);
        sort(a, b, mid + 1, high);
        merge(a, b, low, mid, high);
    }

    /**
     *
     * merge sort example
     *
     * @param a the array of Comparables to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] b = a.clone();
        MergeSort.sort(a, b, 0, a.length - 1);

    }

    private static boolean isGreater(Comparable c1, Comparable c2) {
        return c1.compareTo(c2) > 0;
    }

    /**
     * @param a array of Comparables to be checked
     * @return true if array if sorted
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) {
                return false;
            }
        }
        return true;
    }

}
