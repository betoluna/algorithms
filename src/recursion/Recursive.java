package recursion;

/**
 * Created by beto on 9/23/16.
 */
public class Recursive {

    public static long factorial(int n) {
        if(n == 1) return 1;

        return n * factorial(n - 1);
    }

    public static int gcd(int p , int q) {
        System.out.println("p = " + p + ", q = " + q);
        if (q == 0) {
            return p;
        } else return gcd(q, p % q);
    }

    /*
     *  Recursive top-down Dynamic Programming fibonacci function
     *    n = 0, 1, 2, 3, 4, 5, 6, 7,  8,  9,  10, 11, 12,  13,  14,  ...
     * f(n) = 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, ...
     *
     * Note: the 93rd Fibonacci number will overflow a long.
     */
    public static long topDownfibonacci(int n) {
        long[] f = new long[92];

        if(n == 0) return 0;
        if(n == 1) return 1;

        //return cached value if previously computed
        if(f[n] > 0) return f[n];

        f[n] = topDownfibonacci(n - 1) + topDownfibonacci(n - 2);
        return f[n];
    }

    /*
     *  Non-recursive buttom-up Dynamic Programming fibonacci function
     *    n = 0, 1, 2, 3, 4, 5, 6, 7,  8,  9,  10, 11, 12,  13,  14,  ...
     * f(n) = 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, ...
     *
     * Note: the 93rd Fibonacci number will overflow a long.
     */
    public static long bottomUpfibonacci(int n) {
        long[] f = new long[n + 1];

        f[0] = 0;
        f[1] = 1;

        for(int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f[n];
    }

    public static void main(String[] args) {

        /* test the factorial function */
        //int n = 5;
        //System.out.println(n + " factorial is: " + factorial(n));

        /* test the euclid gcd function */
//        int p = -1440, q = 408;
//        System.out.println("gcd of p and q = " + gcd(p, q));

        //test Dynamic programming fibonacci
        int fib = 93;
//        System.out.println("top-down fibonacci of " + fib + " : " + topDownfibonacci(fib));
        System.out.println("bottom-up fibonacci of " + fib + " : " + bottomUpfibonacci(fib));
    }
}
