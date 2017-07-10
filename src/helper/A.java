package helper;

import java.util.Random;

/**
 * Array helper functions. Created by beto on 5/18/17.
 */
public class A {

    /*************************************************
     * Utl functions
     ************************************************/
    public static boolean less(int v, int w) {
        return v < w;
    }

    public static boolean less(Comparable v, Comparable w) { return v.compareTo(w) < 0; }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void show(int[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    //reverse int array in-place from lo (inclusive) to hi (exclusive)
    public static void reverse(int[] A, int lo, int hi) {
        if(A.length == 0) return;

        hi = hi - 1;//move hi inside array bounds
        int tmp;
        while(hi >= lo) {
            tmp = A[lo];
            A[lo++] = A[hi];
            A[hi--] = tmp;
        }
    }

    /*************************************************
     * Generate random numbers
     ************************************************/

    /**
     * generate and uniformly fill array of length N with
     * random ints in range 'from' to 'to'
     * @param N the size of the array to generate
     * @param from the lower bound - from (inclusive)
     * @param to the upper bound - to (exclusive)
     * @return the new array
     */
    public static int[] generateIntArray(int N, int from, int to) {
        Random random = new Random();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = from + random.nextInt(to + 1 - from);//add 1 so that 'to' is included in range
        }

        return arr;
    }

    /**
     * generate and uniformly fill array of length N with
     * random double values in range 'from' to 'to'
     * @param N the size of the array to generate
     * @param from the lower bound - from (inclusive)
     * @param to the upper bound - to (exclusive)
     * @return the new array
     */
    public static double[] generateDoubleArray(int N, double from, double to) {
        Random random = new Random();
        double[] arr = new double[N];
        for(int i = 0; i < N; i++) {
            arr[i] = from + random.nextDouble() * (from - to);
        }

        return arr;
    }
}
