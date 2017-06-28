package arrays;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by beto on 6/13/17.
 */
public class Epi6 {

    /**
     * @param A an int Array
     * @param pivot an index into A
     * @return an Array such that elements < A[pivot] appear
     * first, followed by elements equal to A[pivot], followed
     * by elements > A[pivot]
     */
    public static void dutchNatFlagTwoPass(Integer[] A, int pivot) {
        List<Integer> L = Arrays.asList(A);
        int trailPtr = 0;// use this for swapping
        //first pass
        for(int i = 0; i < L.size(); i++) {
           if(L.get(i) < L.get(pivot)) {
               //move smaller elements to the left of pivot
               Collections.swap(L, i, trailPtr++);
           }
        }
        //second pass
        trailPtr = L.size() - 1;
        // add && condition to stop when you find an elem
        // smaller than L[pivot] since they are all already to the left.
        for(int i = L.size() - 1; i >= 0 && L.get(i) >= L.get(pivot); i--) {
            if(L.get(i) > L.get(pivot)) {
                Collections.swap(L, i, trailPtr--);
            }
        }

    }

    /*
     * Take an array A of digits encoding a decimal number D and
     * updates A to represent de number D + 1. NOTE it only deals
     * with positive Big Integer
     * e.g A = <1,2,9> should update to <1,3,0>
     */
    public static int[] incrementBigInteger(int[] A) {
        int n = A.length - 1;
        A[n] = A[n] + 1;
        for(int i = n; i > 0; i--) {
            if(A[i] ==  10) {
                A[i] = 0;
                A[i - 1] = A[i - 1] + 1;
            }
        }

        //increase array size if needed
        if(A[0] == 10) {
            int[] newArr = new int[A.length + 1];
            newArr[0] = 1;
            newArr[1] = 0;
            for(int i = 2; i < newArr.length; i++) {
                newArr[i] = A[i - 1];
            }
            return newArr;
        } else {
            return A;
        }

        /* Take care of negative BigInteger also:
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < a.length; i++) {
            sb.append(String.valueOf(a[i]));
        }

        BigInteger bi1 = new BigInteger(sb.toString(), 10);
        BigInteger inc = new BigInteger("1", 10);
        BigInteger increased = bi1.add(inc);

        int[] arr;
        if(increased.toString().charAt(0) == '-') {
            arr = new int[increased.toString().length() - 1];
            arr[0] = -Integer.parseInt(Character.toString(increased.toString().charAt(1)));
            for(int i = 2; i < increased.toString().length(); i++) {
                arr[i - 1] = Integer.parseInt(Character.toString(increased.toString().charAt(i)));
            }
            show(arr);
        } else {
            arr = new int[increased.toString().length()];
            for(int i = 0; i < increased.toString().length(); i++) {
                arr[i] = Integer.parseInt(Character.toString(increased.toString().charAt(i)));
            }
            show(arr);
        }
        */
    }

    /*
     * Take two strings representing integers and return
     * a integer representing their product
     */
    public static List<Integer> multiply2BigInts(String bigInt1, String bigInt2) {
        boolean isProductNegative = false;
        int[] a, b;
        int i = (bigInt1.charAt(0) == '-') ? 1 : 0;
        if(i == 1) {
            isProductNegative = !isProductNegative;
            a = new int[bigInt1.length() - 1];
        } else {
            a = new int[bigInt1.length()];
        }

        //copy bigIntI to int[] a
        for(int j = 0; j < a.length; j++) {
            a[j] = Integer.parseInt(String.valueOf(bigInt1.charAt(i++)));
        }

        i = (bigInt2.charAt(0) == '-') ? 1 : 0;
        if(i == 1) {
            isProductNegative = !isProductNegative;
            b = new int[bigInt2.length() - 1];
        } else {
            b = new int[bigInt2.length()];
        }

        //copy bigIntI to int[] b
        for(int j = 0; j < b.length; j++, i++) {
            b[j] = Integer.parseInt(String.valueOf(bigInt2.charAt(i)));
        }

        //multiply the 2 int arrays using grade school multiplication algorithm
        int[] res = new int[a.length + b.length];
        int next = res.length;
        int ptr;
        for(i = a.length - 1; i >= 0; i--) {
            next--;
            ptr = next;
            for(int j = b.length - 1; j >= 0; j--) {
                int prod = a[i] * b[j];
                int ones = prod % 10;
                int tens = prod / 10;
                int carry = 0;

                // if RHS sum >= 10, carry is > 0
                if((res[ptr] + ones) >= 10) {
                    carry = (res[ptr] + ones) / 10;
                    res[ptr] = (res[ptr] + ones) % 10;
                } else {// no carry
                    res[ptr] = res[ptr] + ones;
                }

                res[ptr - 1] = res[ptr - 1] + tens + carry;
                ptr--;
            }
        }

        // get rid of leading zeros
        i = 0;
        while(res[i] == 0) {
            i++;
        }
        //int[] bigInt = new int[a.length + b.length - i];
        int len = a.length + b.length - i;
        List<Integer> bigInt = new ArrayList<>();
        for(int j = 0; j < len; j++) {
            bigInt.add(res[i++]);
        }

        if(isProductNegative) {
            bigInt.set(0, -bigInt.get(0));
        }

        return bigInt;
    }

    private static void show(int[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    // return the number of elements
    public static int removeKey(int[] A, int key) {
        int hi = A.length;
        for(int i = 0; i < hi; i++) {
            // make sure hi stops decreasing below k
            // otherwise k may become < 0 and keep going until
            // it overflows and becomes Integer.MAX_VALUE
            while(A[i] == key && hi > i) {
                for(int j = i + 1; j < hi; j++) {
                    A[j - 1] = A[j];
                }
                hi--;//after sliding elements, decrease hi
            }
        }

        // if returning # of elements left
        return hi;

        // if returning a list
        /*List<Integer> l = new ArrayList<>();
        for(int i = 0; i < hi; i++) {
            l.add(A[i]);
        }

        return l;*/
    }

    //Removes duplicates from SORTED array A
    //and slides elements to left to fill gaps.
    //Return number of elements left.
    public static int removeDupsFromSortedArr(int[] A) {
        int hi = A.length;
        for(int i = 1; i < hi; i++) {
            while(A[i] == A[i - 1] && hi > i) {
                for(int j = i + 1; j < hi; j++) {
                    A[j - 1] = A[j];
                }
                hi--;//after sliding elements, decrease hi
            }
        }

        return hi;
    }

    public static void main(String[] args) {
        int[] a = {3,5,1,4,1,7};
        //int[] a = {2,1,1,1,1,1};
        show(a);
        System.out.println(removeKey(a, 1));

        //System.out.println(l.toString());

    }
}
