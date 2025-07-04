package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day0049 {

    static List<Long> primeCache = new ArrayList<>();

    public static void main(String[] args) {
        solution(1001, 9999);
    }

    //What 12-digit number do you form by concatenating the three terms in this sequence?
    static String solution(int min, int max) {
        getPrimeList(max);

        Map<Integer, List<Integer>> map = new HashMap<>();

        outer:
        for(int i = min; i <= max; i+=2) {
            boolean isPrime = checkPrime(i);
            if(!isPrime) {
                continue;
            }
            List<Integer> combs = new ArrayList<>();
            inner:
            for(int j = 1; j <= max; j++) {
                if(i + 2*j > max) {
                    break;
                }
                boolean isPrimeSec = checkPrime(i + j);
                if(!isPrimeSec) {
                    continue;
                }
                boolean permutation = isPermutation(i, i + j);
                if(permutation) {
                    combs.add(i+j);
                }
            }
            if(!combs.isEmpty()) {
                map.put(i, combs);
            }
        }

        List<Integer> resMap = new ArrayList<>();
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if(entry.getKey().equals(1487)) {
                continue;
            }
            for (int val : entry.getValue()) {
                int nextElem = val + val - entry.getKey();
                boolean isPerm2 = isPermutation(val, nextElem);
                if(!checkPrime(nextElem)) {
                    continue;
                }
                if(isPerm2) {
                    List<Integer> arr = List.of(entry.getKey(), val, nextElem);
                    resMap.addAll(arr);
                }
            }
        }

        String result = resMap.stream().map(String::valueOf).reduce((o1, o2) -> o1 + o2).get();
        System.out.println("result is " + result);

        return result;
    }

    static boolean isPermutation(int a, int b) {
        String aValue = String.valueOf(a);
        String bValue = String.valueOf(b);

        if (aValue.length() != bValue.length()) {
            return false;
        }

        Map<Character, Integer> cntMap = new HashMap();
        for (int i=0; i < aValue.length(); i++) {
            cntMap.put(aValue.charAt(i), cntMap.getOrDefault(aValue.charAt(i), 0)+1);
            cntMap.put(bValue.charAt(i), cntMap.getOrDefault(bValue.charAt(i), 0)-1);
        }

        for (Map.Entry<Character, Integer> entry : cntMap.entrySet()) {
            if(!entry.getValue().equals(0)) {
                return false;
            }
        }

        return true;
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
