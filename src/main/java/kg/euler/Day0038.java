package kg.euler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day0038 {
    public static void main(String[] args) {
        solution(900000);
    }

    // What is the largest 1 to 9 pandigital
    // 9-digit number that can be formed as the
    // concatenated product of an integer with
    // (1,2...,n) where n > 1
    static long solution(int limit) {
        long max = 0;
        for (int i = 2; i < limit; i++) {
            max = Math.max(recursiveMultiply(i), max);
        }
        System.out.println("result is " + max);
        return max;
    }

    static long recursiveMultiply(int num) {
        int multiplier = 1;
        StringBuilder len = new StringBuilder();
        while (len.length() <= 9) {
            int res = num * multiplier ;
            multiplier++;
            len.append(res);
            if(len.length() > 9) {
                break;
            }
            boolean isFinal = len.length() == 9;
            List<Integer> pandigitalCheck = pandigitalList(Integer.parseInt(len.toString()), isFinal);
            if(pandigitalCheck == null) {
                break;
            }
            if(isFinal) {
                return Integer.parseInt(len.toString());
            }
        }
        return 0;
    }

    static List<Integer> pandigitalList(int num, boolean isFinal) {
        StringBuilder sb = new StringBuilder();
        sb.append(num);

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < sb.length(); i++){
            map.put(sb.charAt(i), map.getOrDefault(sb.charAt(i), 0) + 1);
        }

        if(isFinal) {
            if(map.size() != 9) {
                return null;
            }

            for(Map.Entry<Character, Integer> set: map.entrySet()) {
                if(set.getKey() == '0') {
                    return null;
                }
            }
        } else {
            for(Map.Entry<Character, Integer> set: map.entrySet()) {
                if(set.getValue() > 1) {
                    return null;
                }
            }
        }
        return List.of(num);
    }
}
