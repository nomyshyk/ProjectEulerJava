package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0046 {

    static List<Long> primeCache = new ArrayList<>();
    static List<Long> primeSquare = new ArrayList<>();

    public static void main(String[] args) {
        solution(10000);
    }

    //What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
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
            //squares
            List<Long> selectedSquares = new ArrayList<>();
            for(int j = 0; j < primeSquare.size(); j++) {
                if(i > primeSquare.get(j)) {
                    selectedSquares.add(primeSquare.get(j));
                } else {
                    break;
                }
            }

            for (int j = 0; j < selectedPrimes.size(); j++) {
                for (int k = 0; k < selectedSquares.size(); k++) {
                    long res = selectedPrimes.get(j) + selectedSquares.get(k) * 2;
                    if (res == i) {
                        continue ext;
                    }
                }
            }
            result = i;
            System.out.println("result is " + result);
            break ext;
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

    static void getSquareList(long num) {
        for(long i = 1; i <= num; i++) {
            primeSquare.add(i*i);
        }
    }
}
