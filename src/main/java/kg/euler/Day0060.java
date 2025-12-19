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

    static Map<Long, List<Long>> mapToPrimes = new HashMap<>();
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
                if(checkChangedOrder(primes.get(i), primes.get(j))) {
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

        long[] p1 = {3, 8};
        long[] p2 = {3, 109};
        long[] results = detectPairs(p1, p2);

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

    static boolean checkChangedOrder(long a, long b) {
        return Utils.checkPrime(Long.parseLong(String.format("%s%s",a, b)))
                && Utils.checkPrime(Long.parseLong(String.format("%s%s",b, a)));
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

    static long[] detectPairs(long[] pair1, long[] pair2) {
        // pair1.size >= 2
        // pair2.size = 2
        List<long[]> newPairList = new ArrayList<>();
        Map<Long, Integer> map = new HashMap();
        long dupValue = 0;
        int cntDuplicates = 0;
        // add records to hashmap to identify if lists have something in common
        for(int i = 0; i < pair1.length; i++){
            map.put(pair1[i], map.getOrDefault(pair1[i], 0) + 1);
        }
        for(int i = 0; i < pair1.length; i++){
            map.put(pair2[i], map.getOrDefault(pair2[i], 0) + 1);
        }

        // verify duplicate values
        for(Map.Entry<Long, Integer> entry : map.entrySet()) {
            if(entry.getValue() > 1) {
                dupValue = entry.getKey();
                cntDuplicates++;
            }
        }
        // if no duplicates return
        if(cntDuplicates != 1) {
            return null;
        }

        // if theres something in common add new values to list
        int cntPrimesAgain = 0;

        Set<String> cached=new HashSet<>();
        for(Map.Entry<Long, Integer> entry1 : map.entrySet()) {
            for(Map.Entry<Long, Integer> entry2 : map.entrySet()) {
                // don't check common value
                if(entry1.getKey() != dupValue && entry2.getKey() != dupValue
                 && !entry1.getKey().equals(entry2.getKey())) {
                    // compare all combinations
                    long[] pair = new long[2];
                    pair[0] = entry1.getKey() > entry2.getKey() ? entry2.getKey() : entry1.getKey();
                    pair[1] = entry1.getKey() < entry2.getKey() ? entry2.getKey() : entry1.getKey();
                    // if numbers are prime after changing order
                    if(checkChangedOrder(pair[0], pair[1]) &&
                            !cached.contains("%s-%s".formatted(pair[0], pair[1]))) {
                        cached.add("%s-%s".formatted(pair[0], pair[1]));
                        cntPrimesAgain++;
                    }
                }
            }
        }

        if(cntPrimesAgain == pair1.length - 1) {
            int iter = 0;
            long[] primeNewArr = new long[map.size()];
            for(Map.Entry<Long, Integer> entry : map.entrySet()) {
                primeNewArr[iter++] = entry.getKey();
            }
            return primeNewArr;
        }

        return null;
    }
}
