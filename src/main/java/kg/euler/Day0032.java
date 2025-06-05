package kg.euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0032 {

    static Set<Integer> uniqueSet = new HashSet<>();
    public static void main(String[] args) {
        long result = solution(999999);
    }

    // Find the sum of all products whose multiplicand/multiplier/product identity can be written as a
    // 1 through 9 pandigital.
    static long solution(int topBorder) {
        for (int i = 1; i <= topBorder; i ++){
            for (int j = 1; j <= topBorder; j ++){
                int prod = i * j;
                if(prod > topBorder) {
                    break;
                }
                if(isPandigital(List.of(i, j, prod))) {
                    uniqueSet.add(prod);
                };
            }
        }
        System.out.println(uniqueSet);
        int result = 0;
        for (Integer val: uniqueSet) {
            result += val;
        }
        System.out.println("result = " + result);
        return result;
    }

    static boolean isPandigital(List<Integer> vals) {
        StringBuilder sb = new StringBuilder();
        for (Integer val : vals) {
            sb.append(val);
        }

        if(sb.length() != 9) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < sb.length(); i++){
            map.put(sb.charAt(i), map.getOrDefault(sb.charAt(i), 0) + 1);
        }

        if(map.size() != 9) {
            return false;
        }

        for(Map.Entry<Character, Integer> set: map.entrySet()) {
            if(set.getKey() == '0') {
                return false;
            }
        }
        return true;
    }
}