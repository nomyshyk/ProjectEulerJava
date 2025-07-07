package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0051 {

    static int limit = 1000;
    static List<Long> primeCache = new ArrayList<>();

    public static void main(String[] args) {
        solution(limit);
    }

    // Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits)
    // with the same digit, is part of an eight prime value family.
    static int solution(int limit) {
        getPrimeList(limit);

        return 0;
    }

    static int generateInterval(long min, long max) {
        for(long i = min; i <= max; i++) {
            String strValue = String.valueOf(i);
        }
        return 0;
    }

    static long replace(String strNum) {
        for(int i = 0; i < strNum.length(); i++) {
            for(int j = 1; j <= 9; j++) {
                //strNum.replace(i)
            }
        }
        return 0;
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
