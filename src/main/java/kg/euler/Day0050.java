package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0050 {

    static int limit = 1_000_000;
    static List<Long> primeCache = new ArrayList<>();

    public static void main(String[] args) {
        solution(limit);
    }

    //Which prime, below one-million, can be written as the sum of the most consecutive primes?
    static int solution(int limit) {

        getPrimeList(limit);
        int maxSumma = 0;
        int maxCnt = 0;
        int maxCombsForLimit = maxCombsForLimit(limit);
        for(int i = 1; i <= maxCombsForLimit; i++) {
            int summa = 0;
            for(int j = i; j <= maxCombsForLimit; j++) {
                summa += primeCache.get(j);
                if(summa > limit) {
                    break;
                } else {
                    if(summa > maxSumma && (j - i > maxCnt) && checkPrime(summa)) {
                        maxSumma = summa;
                        maxCnt = j - i;
                    }
                }
            }
        }
        System.out.println("result is " + maxSumma);
        return maxCnt;
    }

    static int maxCombsForLimit(int limit) {
        int cur = 0;
        int cnt = 0;
        for(int i = 0; i < limit; i++) {
            cur += primeCache.get(i);
            if(cur >= limit) {
                return cnt;
            }
            cnt++;
        }
        return cnt;
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
