package arrays;

import java.math.BigInteger;
import java.util.*;

import helper.A;

//import static helper.A.reverse;

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
        int tmp = 1;
        for(int i = 1; i < A.length; i++) {
            if(!(A[i] == A[tmp - 1])) {
                A[tmp++] = A[i];
            }
        }
        return tmp;

//        int hi = A.length;
//        for(int i = 1; i < hi; i++) {
//            while(A[i] == A[i - 1] && hi > i) {
//                for(int j = i + 1; j < hi; j++) {
//                    A[j - 1] = A[j];
//                }
//                hi--;//after sliding elements, decrease hi
//            }
//        }
//
//        return hi;
    }

    //O(nlogn) because of sorting
    public static int findFirstMissingPositive(int[] A) {
        Arrays.sort(A);
        //show(A);
        int fmp = Integer.MAX_VALUE;
        int i = 1;
        while(i < A.length) {
            //when you find a positive, find out if there is
            //a positive gap between A[i] and A[i - 1]
            if(A[i] >= 2) {
                if (A[i - 1] <= 0) {
                    fmp = 1;
                } else if(A[i] - A[i - 1] > 1){
                    int tmp = A[i - 1] + 1;
                    if(tmp < fmp) fmp = tmp;
                }
            }
            i++;
        }

        if(fmp == Integer.MAX_VALUE) {//if not found yet
            if(A[A.length - 1] <= 0) {// if all negative array
                fmp = 1;
            } else { // all positive numbers in array are consecutive
                fmp = A[A.length - 1] + 1;
            }
        }

        return fmp;
    }

    //find first missing positive O(n) in A w/o sorting but O(n) extra space
    public static int findMissingPositive(int[] A) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < A.length; i++) {
            if(A[i] > 0) set.add(A[i]);
        }

        //find the 1st missing positive if it exist
        int i;
        for(i = 1; i <= set.size(); i++) {
            if(!set.contains(i)) return i;//return 1st missing
        }

        //note that i increases past the set.size() on the
        //last iteration before it fails the loop condition
        return i;
    }

    // uses String operations to check if an int is a palindrome
    public static boolean isPalindrome(int x) {
        if(x < 0) return false;

        //converting to a String
        String s = String.valueOf(x);
        for(int i = 0, j = s.length() - 1; i < s.length() / 2 && j >= s.length() / 2; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    //uses Math ops (no String) to check if an int is a palindrome
    //x has to be <= to Integer.MAX_VALUE
    public static boolean isIntPalindrome(int x) {
        if(x < 0) return false;

        int numOfDigits = (int)Math.floor(Math.log10(x)) + 1;
        int mask = (int)Math.pow(10, numOfDigits - 1);

        for(int i = 0; i < numOfDigits / 2; i++) {
            //compare most and least significant digits
            int msd = x / mask;
            int lsd = x % 10;
            if(msd != lsd) {
                return false;
            }
            //remove msd & lsd
            x %= mask;
            x /= 10;
            //reduce the mask accordingly
            mask /= 100;
        }

        return true;
    }

    //Optimized find 1st missing positive in A. O(n), O(1) extra space
    public static int findMissingPositiv(int[] A) {
        //1st pass, if A[i] in range of interest (1 - A.length) and elem at index where
        //this elem would finally have to go (A[A[i] - 1) not equal to this elem (A[i])
        int i = 0;
        while(i < A.length) {
            if(A[i] > 0 && A[i] <= A.length && A[A[i] - 1] != A[i]) {
                //swap
                int tmp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = tmp;
            } else {
                i++;
            }
        }

        //2nd pass
        for(int j = 0; j < A.length; j++) {
            if(A[j] != j + 1) {
                return j + 1;
            }
        }

        return A.length + 1;
    }

    //find next permutation under dictionary ordering
    public static boolean findNextPermutation(int[] A) {
        int i = A.length - 2;
        //find longest decreasing eg [2,3,1] subarray from the end if it exists
        while(i >= 0 && A[i] > A[i + 1]) {
            i--;
        }

        //A is already the largest permutation under dictionary ordering
        if(i < 0) return false;

        //find & swap 1st 'entry' to left of the subarray with
        //smallest that is greater than 'entry' in found subarray
        int j = A.length - 1;
        while(A[j] <= A[i]) {
            j--;
        }
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;

        //Swap. Reorganize subarray in increasing order to
        //make permutation the smallest next possible
        i = i + 1;
        j = A.length - 1;
        while(j >= i) {
            tmp = A[i];
            A[i++] = A[j];
            A[j--] = tmp;
        }

        return true;
    }

    //rotate array clockwise by k entries. O(nk) time, O(1) extra memory
    public static void rotateArrayByK(int[] A, int k) {
        if(k <= 0 || k >= A.length) return;// do nothing

        int times = 0;
        int i = k;
        int tmp = A[k];
        while(times < k) {
            if(i == -1) i = A.length - 1;

            if(i == 0) {
                if(k == A.length - 1) {//corner case for k
                    A[i] = tmp;
                    tmp = A[A.length - 1];
                    times++;//whole turn completed
                } else {
                    A[i] = A[A.length - 1];
                }
            } else {
                if(i - 1 == k) {//whole turn completed
                    A[i] = tmp;
                    tmp = A[i - 1];
                    times++;
                } else {//most usual case
                    A[i] = A[i - 1];
                }
            }
            i--;
        }
    }

    //optimized rotateArray by k using reverse. O(n) time, O(1) space
    public static void rotateArray(int[] a, int k) {
        int i = k % a.length;

        A.reverse(a, 0, a.length);
        A.reverse(a, 0, i);
        A.reverse(a, i, a.length);
    }

    public static void main(String[] args) {
        //int[] a = {3,5,1,4,1,7};
        //int[] a = {2,1,1,1,1,1};
        //int[] a = {1,2,3,3,3,3,4};
        int[] a = {6,2,1,5,4,3,0};
        A.show(a);
        rotateArray(a, 9);
        A.show(a);


    }
}
