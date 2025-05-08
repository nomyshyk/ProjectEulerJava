package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day0023 {

    public static void main(String[] args) {
        solution(10000);
    }

    // Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
    static long solution(int num) {
        abundantNumList(28123);
        //abundantNumList(20);
        return 0;
    }

    static List<Integer> abundantNumList(int maxNum) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 1; i <= (maxNum + 1) /2; i++) {
            for(int j = i; j <= maxNum; j++) {
                if(j%i == 0 && i < j) {
                    int divisors = map.getOrDefault(j, 0) + i;
                    map.put(j, divisors);
                }
            }
        }

        List<Integer> abundantNumbers = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getKey() < entry.getValue()) {
                abundantNumbers.add(entry.getKey());
            }
        }
        //System.out.println(abundantNumbers);
        return abundantNumbers;
    }
}