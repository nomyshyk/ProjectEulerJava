package kg.euler;

import jdk.jshell.execution.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day0060 {

    //static Set<Utils.Pair<Long, Long>> primePairs = new HashSet<>();
    static Map<Integer, List<long[]>> levelsOf = new HashMap<>();
    public static void main(String[] args) {

        List<Long> primes = Utils.generatePrimeList(1000);
        long solution = solution(primes);
        System.out.println("result is = " + solution);
    }

    // Find the lowest sum for a set of five primes for which any
    // two primes concatenate to produce another prime.
    static long solution(List<Long> primes) {
        List<long[]> listOfLong = new ArrayList<>();
        for (int i = 0; i < primes.size(); i++) {
            for (int j = 0; j < primes.size(); j++) {
                if(i >= j) {
                    continue;
                }
                if(Utils.checkPrime(Long.parseLong(String.format("%s%s",primes.get(i),primes.get(j))))
                    && Utils.checkPrime(Long.parseLong(String.format("%s%s",primes.get(j),primes.get(i))))) {
                    System.out.printf("%s %s are prime when permutated \n", primes.get(i), primes.get(j));
                    //primePairs.add(new Utils.Pair<>(primes.get(i), primes.get(j)));
                    long arr[] = new long[2];
                    arr[0] = primes.get(i);
                    arr[1] = primes.get(j);
                    listOfLong.add(arr);
                }
            }
        }
        levelsOf.put(2, listOfLong);
        return 0;
    }

    static boolean isConnected(long[] basePair, long[] addPair, List<long[]> pairsList) {
        int cnt = 0;
        for(int i = 0; i < basePair.length; i++) {
            for(int j = 0; j < addPair.length; j++) {
                if(cnt > 1) {
                    return false;
                }
                if(basePair[i] == addPair[j]) {
                    cnt++;
                } else {
                    //addPair[i]
                }

            }
        }
        return false;
    }

    // compare [3,7] with allPairs
    static boolean searchInList(long[] pair, List<long[]> pairsList) {
        for(int j = 0; j < pairsList.size(); j++) {
            if(pair[0] == pairsList.get(j)[0] && pair[1] == pairsList.get(j)[1]) {
                return true;
            }
            if(pair[0] > pairsList.get(j)[0]) {
                return false;
            }
        }
        return false;
    }
}
