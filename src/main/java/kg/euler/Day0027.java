package kg.euler;

import java.util.HashMap;
import java.util.Map;

public class Day0027 {

    static Map<Long, Boolean> setka = new HashMap<>();

    public static void main(String[] args) {
        long result = solution(999);
        System.out.println("result is " + result);
    }

    // Find the product of the coefficients, a and b,
    // for the quadratic expression that produces the maximum number of primes
    // for consecutive values of n, starting with n = 0.
    static long solution(int limit) {
        long maxLen = 0;
        int resA = 0;
        int resB = 0;
        for(int i = -limit; i <= limit; i++) {
            for(int j = -limit; j <= limit; j++) {
                long lengthFound = formula(i, j);
                if(lengthFound > maxLen) {
                    maxLen = lengthFound;
                    resA = j;
                    resB = i;
                }
            }
        }

        System.out.printf("A is %s, B is %s, lengths is %s \n", resA, resB, maxLen);

        return (long)resA * resB;
    }

    static long formula(int a, int b) {
        int cnt = 0;
        for(int n = 0; ; n++) {
            long result = n * n + a * n + b;
            if(isPrime(result)) {
                cnt++;
            } else {
                return cnt;
            }
        }
    }

    static boolean isPrime(long num) {
        if(num < 2) {
            return false;
        }

        Boolean isPrime = setka.get(num);
        if(isPrime != null) {
            return isPrime;
        }
        for(long i=2; i < Math.sqrt(num); i++) {
            if(num % i == 0){
                setka.put(i, false);
                return false;
            }

        }
        setka.put(num, true);
        return true;
    }
}