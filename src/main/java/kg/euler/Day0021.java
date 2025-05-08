package kg.euler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day0021 {

    public static void main(String[] args) {
        solution(10000);
    }

    // Evaluate the sum of all the amicable numbers under 10000
    static long solution(int num) {
        Map<Integer, Set<Integer>> divisorsMap = divisors(num);
        return amicableSum(divisorsMap);
    }

    static Map<Integer, Set<Integer>> divisors(int num) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 1; i < (num + 1) /2; i++) {
            for(int j = i; j < num; j++) {
                if(j%i == 0 && i < j) {
                    Set<Integer> divisors = map.getOrDefault(j, new HashSet<>());
                    divisors.add(i);
                    map.put(j, divisors);
                }
            }
        }
        //System.out.println(map);
        return map;
    }

    static int amicableSum(Map<Integer, Set<Integer>> divisorMap) {
        Map<Integer, Integer> divisorSumMap = new HashMap<>();
        for(Map.Entry<Integer, Set<Integer>> entry: divisorMap.entrySet()){
            int sum = 0;
            for(int divisor : entry.getValue()) {
                sum += divisor;
            }
            divisorSumMap.put(entry.getKey(), sum);
        }
        //System.out.println(divisorSumMap);

        int amicableSum = 0;
        for(Map.Entry<Integer, Integer> entry: divisorSumMap.entrySet()){
            int keyVal = divisorSumMap.getOrDefault(entry.getValue(), 0);
            if(keyVal == entry.getKey()) {
                System.out.printf("key %s and value %s \n", entry.getKey(), entry.getValue());
                if(!entry.getValue().equals(entry.getKey())) {
                    amicableSum += entry.getKey();
                }
            }
        }
        System.out.println("amicable sum is = " + amicableSum);
        return amicableSum;
    }
}