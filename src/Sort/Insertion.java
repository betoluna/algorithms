package Sort;

import helper.A;

/**
 * Created by beto on 5/18/17.
 */
public class Insertion {

    public static void sort(int[] a) {

        for(int i = 1; i < a.length; i++) {

            for(int j = i; j > 0 && A.less(a[j], a[j - 1]); j--) {
                A.swap(a, j, j - 1);
            }
        }
    }

    public static void sort(Comparable[] a) {

        for(int i = 1; i < a.length; i++) {

            for(int j = i; j > 0 && A.less(a[j], a[j - 1]); j--) {
                A.swap(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {

        Comparable[] arr = {"n", "e", "w", "r", "z", "M"};
        int[] a = A.generateIntArray(20, 10, 30);
        A.show(a);
        sort(a);
        A.show(a);


    }
}
