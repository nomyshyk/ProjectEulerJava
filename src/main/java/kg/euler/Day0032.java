package kg.euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0032 {

    static Set<Map<Integer, Integer>> checkedCombs = new HashSet<>();
    public static void main(String[] args) {
        long result = solution(200);
    }

    // Find the sum of all products whose multiplicand/multiplier/product identity can be written as a
    // 1 through 9 pandigital.
    static long solution(int goalVal) {
//        for (int i = 0; i <= 10000; i ++){
//
//        }
        return 0;
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
        //int cnt = 0;
        for(int i = 0; i < sb.length(); i++){
            int val = map.getOrDefault(sb.charAt(i), 0) + 1;
            if (val > 1) {
                return false;
            }
        }
        return true;
    }

}