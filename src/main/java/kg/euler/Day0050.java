package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day0050 {

    static int limit = 100;
    static List<Long> primeCache = new ArrayList<>();
    static int[] dynamicTable = new int[limit];
    static List<List<Integer>> dynamicDetails = new ArrayList<>(limit);

    public static void main(String[] args) {
        solution(limit);
    }

    //Which prime, below one-million, can be written as the sum of the most consecutive primes?
    static int solution(int limit) {

        getPrimeList(limit);
        for(int i = 1; i <= limit; i++) {

        }

        return 0;
    }

    static int findSum(int num, int[] array) {
        if(array[num] != 0) {
            return array[num];
        }
        int curVal = num;
        int combs = 0;
        while (curVal > 0) {
            if(array[curVal] != 0) {
                combs += array[curVal];
            }

            for(int i = 0; i < primeCache.size(); i++) {
                if(primeCache.get(i) > num) {
                    break;
                }
            int pos = i;
            }

        }

//        for(int i = 0; i < primeCache.size(); i++) {
//            if(primeCache.get(i) > num) {
//                break;
//            }
//            int pos = i;
//        }

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
