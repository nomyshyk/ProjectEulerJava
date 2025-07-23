package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0051 {

    static int limit = 1000000;
    static List<Long> primeCache = new ArrayList<>();
    static Set<String> cache = new HashSet<>();

    public static void main(String[] args) {
        solution(limit, 8);
    }

    // Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits)
    // with the same digit, is part of an eight prime value family.
    static long solution(int limit, int matches) {
        getPrimeList(limit);

        long result = 0;
        outer:
        for(int i = 0; i < limit; i++) {
            if(i % 2 == 0 || i % 5 == 0) {
                continue ;
            }
            List<String> myL = new ArrayList<>();
            replaceWithAsterisk("", String.valueOf(i), -1, myL);

            for (int j = 0; j < myL.size(); j++) {
                String alias = myL.get(j);
                if(alias.equals("***") || alias.equals("******")) {
                    continue;
                }
                if(cache.contains(alias)) {
                    continue;
                }
                int cnt = 0;
                int notFound = 0;
                List<String> primes = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    String replaceResult = alias.replace("*", String.valueOf(k));
                    Long longResult = Long.valueOf(replaceResult);
                    if (primeCache.contains(longResult) &&
                    String.valueOf(longResult).length() == replaceResult.length()
                    ) {
                        cnt++;
                        primes.add(replaceResult);
                    } else {
                        notFound++;
                    }
                    if (notFound >= 3) {
                        cache.add(alias);
                        break;
                    }
                    if (cnt == matches) {
                        result = Long.parseLong(primes.get(0));
                        System.out.println(primes);
                        break outer;
                    }
                }
            }
        }
        System.out.println("Result is " + result);
        return result;
    }

    static String replaceWithAsterisk(String value, String orig, int iter, List<String> pattern) {
        if(value.length() == orig.length() && value.contains("*")) {
            pattern.add(value);
        }
        if(value.length() == orig.length() || iter == orig.length()-1) {
            return value;
        }

        iter++;
        for(int j = 0; j < 2; j++) {
            char hea = j == 0 ? '*': orig.charAt(iter);
            replaceWithAsterisk(value+hea, orig, iter, pattern);
        }

        return value;
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

    static Map<Character, List<Integer>> stringToMap(String value) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < value.length(); i++) {
            String beg = i > 0 ? value.substring(0, i) : "";
            String end = (i < value.length()-1) ? value.substring(i+1) : "";
            //pattern.add(beg + "*" + end);
            List<Integer> positions = map.getOrDefault(value.charAt(i), new ArrayList<>());
            positions.add(i);
            map.put(value.charAt(i), positions);
        }
        return map;
    }
    static List<String> transform(String value) {
        Set<String> pattern = new HashSet<>();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < value.length(); i++) {
            String beg = i > 0 ? value.substring(0, i) : "";
            String end = (i < value.length()-1) ? value.substring(i+1) : "";
            pattern.add(beg + "*" + end);
            List<Integer> positions = map.getOrDefault(value.charAt(i), new ArrayList<>());
            positions.add(i);
            map.put(value.charAt(i), positions);
        }

        for(Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            List<Integer> mapVal = entry.getValue();
            String key = String.valueOf(entry.getKey());
            if(mapVal.size() == 1) {
                continue;
            } else {
                String curValue = new String(value);
                for(int i = 0; i < mapVal.size(); i++) {
                    String beg = mapVal.get(i) > 0 ? curValue.substring(0, mapVal.get(i)) : "";
                    String end = (mapVal.get(i) < curValue.length()-1) ?
                            curValue.substring(mapVal.get(i)+1) : "";
                    curValue = beg + "*" + end;
                    pattern.add(curValue);
                }
            }
        }
        System.out.println(pattern);
        return new ArrayList<>(pattern);
    }
}
