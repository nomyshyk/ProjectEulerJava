package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0047 {

    static List<Long> primeCache = new ArrayList<>();

    public static void main(String[] args) {
        solution(10000);
    }

    //Find the first four consecutive integers to have four distinct
    //prime factors each. What is the first of these numbers?
    static long solution(int limit) {
        getPrimeList(limit);
        getSquareList(limit);
        long result = 0;

        ext:
        for (long i = 3; i < limit; i+=2) {
            //primes
            if(primeCache.contains(i)) {
                continue;
            }
            List<Long> selectedPrimes = new ArrayList<>();
            for(int j = 0; j < primeCache.size(); j++) {
                if(i > primeCache.get(j)) {
                    selectedPrimes.add(primeCache.get(j));
                } else {
                    break;
                }
            }
        }

        return result;
    }

    static void getPrimeList(long num) {
        for(long i = 1; i <= num; i++) {
            if(checkPrime(i)) {
                primeCache.add(i);
            }
        }
    }

    static boolean checkPrime(long num) {
        if(num == 1) {
            return true;
        }
        for(int i = 1; i < primeCache.size(); i++) {
            long iValue = primeCache.get(i);
            if(!(iValue < (num/2) + 1)) {
                break;
            }
            if(num % iValue == 0) {
                return false;
            }
        }
        return true;
    }
}
