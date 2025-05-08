package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0023 {

    public static void main(String[] args) {
        solution(28123);
    }

    // Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
    static long solution(int num) {
        List<Integer> abundantNums = abundantNumList(num);
        Set<Integer> uniqueSums = new HashSet<>();
        for(int i = 0; i < abundantNums.size(); i++) {
            for(int j = i; j < abundantNums.size()-1; j++) {
                uniqueSums.add(abundantNums.get(i) + abundantNums.get(j));
            }
        }

        long totalSumma = 0;
        for (int i = 0; i <= num; i++) {
            totalSumma += i;
        }
        System.out.printf("total eq or less to %s is %s \n", num, totalSumma);
        long razn = totalSumma;
        for (Integer val : uniqueSums) {
            if(val <= num) {
                razn -= val;
            }
        }

        System.out.printf("result is %s \n", razn);
        return razn;
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