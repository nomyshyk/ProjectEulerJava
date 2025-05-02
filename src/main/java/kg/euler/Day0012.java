package kg.euler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Day0012 {

    static Map<Long, Long> triangularValuesCache = new LinkedHashMap<>();
    public static void main(String[] args) {
        solution(500);
    }

    //What is the value of the first triangle number to have over five hundred divisors?
    static long solution(int maxNumOfDivisors) {
        int cnt = 0;
        int iter = 3;
        long triangleValue = 0L;
        while (cnt < maxNumOfDivisors) {
            triangleValue = triangleValueOf(++iter);
            //System.out.print("triangle value is = " + triangleValue);
            cnt = divisorsAmt(triangleValue);
            //System.out.println(", amt of divisors is = " + cnt);
        }

        System.out.println(triangleValue);
        return triangleValue;
    }

    static int divisorsAmt(long number) {
        int idx = 2;
        long updValue = number;
        Map<Integer, Integer> divisors = new HashMap<>();
        while(updValue > 1 ) {
            if(updValue % idx == 0) {
                updValue /= idx;
                divisors.put(idx, divisors.getOrDefault(idx,0) + 1);
            } else {
                idx++;
            }
        }

        int divisorCnt = 1;
        for (Map.Entry<Integer, Integer> entry : divisors.entrySet()) {
            divisorCnt *= entry.getValue() + 1;
        }
        return divisorCnt;
    }

    static long triangleValueOf(long number) {
        Long cachedValue = triangularValuesCache.get(number);
        if(cachedValue != null) {
            return cachedValue;
        }
        if(number == 1) {
            return 1;
        }
        long calc = (number) + triangleValueOf(number - 1);
        triangularValuesCache.put(number, calc);
        return calc;
    }
}
