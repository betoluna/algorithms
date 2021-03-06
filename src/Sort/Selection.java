package Sort;

import helper.A;

/**
 * Created by beto on 5/18/17.
 */
public class Selection {

    public static void sort(int[] a) {
        // Sort a[] into increasing order.
        int N = a.length;
        for(int i = 0; i < N; i++) {
            int min = i;
            for(int j = i + 1; j < N; j++) {
                if(A.less(a[j], a[min]))
                    min = j;
            }
            A.swap(a, i, min);
        }

    }

    public static void sort(Comparable[] a) {
        // Sort a[] into increasing order.
        int N = a.length;
        for(int i = 0; i < N; i++) {
            int min = i;
            for(int j = i + 1; j < N; j++) {
                if(A.less(a[j], a[min]))
                    min = j;
            }
            A.swap(a, i, min);
        }

    }

    // TEST
    public static void main(String[] args) {
        Comparable[] arr = {"n", "e", "w", "r", "z", "M"};
        //int[] arr = {2,1,5,4,8,7};
        A.show(arr);
        sort(arr);
        A.show(arr);
    }
}
