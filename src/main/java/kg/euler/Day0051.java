package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day0051 {

    static int limit = 100000;
    static List<Long> primeCache = new ArrayList<>();
    //static List<String> pattern = new ArrayList<>();

    public static void main(String[] args) {
        solution(limit);
    }

    // Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits)
    // with the same digit, is part of an eight prime value family.
    static int solution(int limit) {
        //transform("16113");
        stringToMap("1232");
        getPrimeList(limit);

        Map<String, Integer> mapCnt = new HashMap<>();
        for(int i = 0; i < primeCache.size(); i++) {
            List<String> pattern = new ArrayList<>(transform(String.valueOf(primeCache.get(i))));
            //replaceWithAsterisk("" ,String.valueOf(primeCache.get(i)), -1, pattern);

            for(int j = 0; j < pattern.size(); j++) {
                String key = pattern.get(j);
                int val = mapCnt.getOrDefault(key, 0);
                mapCnt.put(key, val + 1);
            }
        }

        //System.out.println(mapCnt);
        //System.out.println(mapCnt);


//        System.out.println(pattern);
//        System.out.println(val);

        ;
        return 0;
    }

    static String replaceWithAsterisk(String value, String orig, int iter, List<String> pattern,
                                      Map<Character, List<Integer>> map) {
        if(value.length() == orig.length() && value.contains("*")) {
            pattern.add(value);
        }
        if(value.length() == orig.length() || iter == orig.length()-1) {
            return value;
        }

        iter++;
        for(int j = 0; j < 2; j++) {

            if(map.get(orig.charAt(iter)).size() == 1 && value.contains("*")) {
                continue;
            } else {
                char hea = j == 0 ? '*': orig.charAt(iter);
                replaceWithAsterisk(value+hea, orig, iter, pattern, map);
            }
        }

        return value;
    }

//    static String replaceRecursive(String value, String orig, int iter, List<String> pattern,
//                                   Map<Character, List<Integer>> map) {
//        if(value.length() == orig.length()) {
//            return
//        }
//    }

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

       // System.out.println(pattern);

        // System.out.println(map);
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
        //System.out.println("after 2");
    }
}
