import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by beto on 6/6/17.
 */
public class Generate {

    /**
     * Use sieve of Eratosthenes to find primes up to n.
     * @param n the number you want to generate primes up to [inclusive].
     * @return a list of primes up to n [inclusive]
     */
    public static List<Integer> primes(int n) {
        int len = n + 1;//add one to include n
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[len];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < len; i++) {
            if (isPrime[i] == true) {
                primes.add(i);
                // initialize to false all the positive
                // multiples of i [the prime number]
                for (int j = 2 * i; j < len; j = j + i) {
                    isPrime[j] = false;
                }
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int n = 1009;
        List<Integer> primes = primes(n);
        for (Integer prime : primes) {
            System.out.print(prime + " ");
        }

        System.out.println();
        System.out.println("primes size: " + primes.size());
    }
}
