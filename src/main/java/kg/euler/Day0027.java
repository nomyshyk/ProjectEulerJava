package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day0027 {

    static Map<Long, Boolean> setka = new HashMap<>();
    //static Map<Long, Boolean> cache = new HashMap<>();
    public static void main(String[] args) {
        long result = solution(1000);
        System.out.println("result is " + result);
    }

    // Find the product of the coefficients, a and b,
    // for the quadratic expression that produces the maximum number of primes
    // for consecutive values of n, starting with n = 0.
    static long solution(int limit) {
        List<Integer> primesList = getPrimesList(limit);
        long maxLen = 0;
        int resA = 0;
        int resB = 0;
        for(int i = 0; i < primesList.size(); i++) {
            for(int j = -999; j <= 999; j++) {
                long lengthFound = formula(j, primesList.get(i));
                if(lengthFound > maxLen) {
                    maxLen = lengthFound;
                    resA = j;
                    resB = primesList.get(i);
                }
            }
        }

        System.out.printf("A is %s, B is %s, lengths is %s \n", resA, resB, maxLen);

        return (long)resA * resB;
    }

    static long formula(int a, int b) {
        int cnt = 0;

        for(int i = 0; ; i++) {
            long result = ((long)i * i) + ((long)a * i) + b;
            //System.out.printf("a=%s, b=%s, n=%s, res=%s \n", a, b, i, result);
            if(isPrime(Math.abs(result))) {
                cnt++;
            } else {
                return cnt;
            }
        }
    }

    static List<Integer> getPrimesList(int maxLimit) {
        List<Integer> primesList = new ArrayList<>();

        for (int i = 3; i <= maxLimit; i++){
            if(isPrime(i)){
                primesList.add(i);
            }
        }
        return primesList;
    }

    static boolean isPrime(long num) {
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