package Sort;

import helper.Utl;

/**
 * Created by beto on 5/22/17.
 * Implementation of Cormen 3rd. Edition Merge-Sort p. 31 - 34
 */
public class Merge {

    // Sort the complete array
    public static void sort(int[] A) {
        sort(A, 0, A.length - 1);
    }

    // Sort array from lo to hi
    public static void sort(int A[], int lo, int hi) {
        if (lo < hi) {
            // Divide
            int mid = (lo + hi) / 2;
            // Conquer
            sort(A, lo, mid);
            sort(A, mid + 1, hi);

            // Combine
            merge(A, lo, mid, hi);
         }
    }

    private static void merge(int[] A, int lo, int mid, int hi) {
        int leftLen = mid - lo + 1;
        int rightLen = hi - mid;
        int[] L = new int[leftLen + 1];// leave extra space for sentinel
        int[] R = new int[rightLen + 1];

        // copy subarray A[lo...mid] to L
        for (int i = 0; i < leftLen; i++) {
            L[i] = A[lo + i];
        }
        // copy subarray A[mid + 1...hi] to R
        for (int j = 0; j < rightLen; j++) {
            R[j] = A[mid + 1 + j];
        }

        // Place the sentinel at end of the two subarrays
        L[leftLen] = Integer.MAX_VALUE;
        R[rightLen] = Integer.MAX_VALUE;

        // Merge the two subarrays - L and R back to A
        int i = 0, j = 0;
        for (int k = lo; k <= hi; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }
    }

    // Test
    public static void main(String[] args) {
        int[] a = Utl.generateIntArray(31, 0, 100);
        //int[] a = {2,4,8,3,5,9,1};
        Utl.show(a);
        sort(a);
        Utl.show(a);

    }
}
