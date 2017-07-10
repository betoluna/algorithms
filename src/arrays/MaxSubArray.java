package arrays;

/**
 * Created by beto on 5/23/17.
 * Divide and Conquer paradigm. Java implementation of
 * Cormen 3rd. Find-Maximum-SubArray, p. 71 - 73.
 */
public class MaxSubArray {

    /**
     * Find max subArray in whole array.
     * @param A Array to find max subArray from
     * @return an Array a[], where a[0] and a[1] are indices that
     * demarcate a maximum subArray, along with a[2] which is the
     * max sum of the values in a max subArray. Note that
     * max sum can overflow an int.
     */
    public static int[] find(int[] A) {
        return find(A, 0, A.length - 1);
    }

    /**
     * Find the maximum subArray in A[], passing index arguments.
     * @param A Array/subArray to find max subArray from
     * @param lo subArray lower index
     * @param hi subArray upper index
     * @return an Array a[], where a[0] and a[1] are indices that
     * demarcate a maximum subArray. a[2] is the max sum. Note that
     * max sum can overflow an int.
     */
    public static int[] find(int[] A, int lo, int hi) {
        // base case: only one element
        if (hi == lo) {
            return new int[] {lo, hi, A[lo]};
        } else {
            // Divide
            int mid = (lo + hi) / 2;

            // Conquer (left subArray)
            int[] tmp = find(A, lo, mid);
            int leftLo = tmp[0];
            int leftHi = tmp[1];
            int leftSum = tmp[2];
            // Conquer (right subArray)
            tmp = find(A, mid + 1, hi);
            int rightLo = tmp[0];
            int rightHi = tmp[1];
            int rightSum = tmp[2];

            // Combine (find the max crossing subArray)
            tmp = findMaxCrossingSubArray(A, lo, mid, hi);
            int crossLo = tmp[0];
            int crossHi = tmp[1];
            int crossSum = tmp[2];

            if (leftSum >= rightSum && leftSum >= crossSum) {
                return new int[] {leftLo, leftHi, leftSum};
            } else if (rightSum >= leftSum && rightSum >= crossSum) {
                return new int[] {rightLo, rightHi, rightSum};
            } else {
                return new int[] {crossLo, crossHi, crossSum};
            }
        }
    }

    /**
     * Find subArray crossing the mid point
     * @param A the original input Array
     * @param lo the low subArray index
     * @param mid the mid subArray index
     * @param hi the high subArray index
     * @return an Array a[], where a[0] and a[1] are indices that
     * demarcate a maximum crossing subArray on the left and right side of mid
     * . a[2] is the sum from left and right. Note that the sum can overflow an int.
     */
    private static int[] findMaxCrossingSubArray(int[] A, int lo, int mid, int hi) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = -1, maxRight = -1;//max left and right indices
        for(int i = mid; i >= lo; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for(int j = mid + 1; j <= hi; j++) {
            sum += A[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        return new int[] {maxLeft, maxRight, leftSum + rightSum};
    }

    // TEST
    public static void main(String[] args) {
//        int[] a = Utl.generateIntArray(12, -9, 6);
//        //int[] a = {-7, -4, -2 , -1, -3}; // when all negative
//        Utl.show(a);
//        Utl.show(find(a));

        String s = "el perro y el gato";
        String[] a = s.split("\\s", 5);
        for (String str : a) {
            System.out.println(str);
        }
    }
}
