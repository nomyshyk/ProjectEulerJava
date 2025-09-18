package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0058 {
    static List<Long> primeCache = new ArrayList<>();

    public static void main(String[] args) {
        generatePrimeList(30000);
        long solution = solution(10);
        System.out.println("result is = " + solution);
    }

    // what is the side length of the square spiral for
    // which the ratio of primes along both diagonals first falls below 10%?
    static long solution(int max) {
        long allNums = 1;
        long primeCnt = 0;
        double perc = 100;
        long iter = 1;
        while (perc >= max) {
            iter += 2;
            if(iter > primeCache.get(primeCache.size()-1)) {
                throw new RuntimeException("Not enough primes generated");
            }
            long[] arr = new long[4];

            for (int i = 3; i >= 0; i--) {
                if(i == 3) {
                    arr[i] = iter * iter;
                } else {
                    arr[i] = arr[i+1] - iter + 1;
                    if(checkPrime(arr[i])) {
                        primeCnt++;
                    }
                }

            }
            allNums += 4;

            perc = ((1.0 * primeCnt * 100.00)) / (allNums);
            System.out.printf("perc = %f, primeCnt = %d, allNums = %d \n", perc, primeCnt, allNums);
        }
        return iter;
    }

    static void generatePrimeList(long num) {
        int size = primeCache.size();
        long lastVal = size == 0 ? 1 : primeCache.get(size - 1);
        for(long i = lastVal + 1; i <= num; i++) {
            if(checkPrime(i)) {
                primeCache.add(i);
            }
        }
    }

    static boolean checkPrime(long num) {
        if(num == 1) {
            return false;
        }
        for(int i = 0; i < primeCache.size(); i++) {
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
