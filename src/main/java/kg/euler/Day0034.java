package kg.euler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day0034 {

    static Map<Integer, Long> factorialMap = new LinkedHashMap<>();
    public static void main(String[] args) {
        long result = solution();
        System.out.println("result " + result);
    }

    // Find the sum of all numbers which are equal to the sum of the factorial of their digits.
    static long solution() {
        long factorial = fillFactorial(9);
        long summa = 0L;
        for(long i = 3; i < 10000000; i++) {
            long sumResult = splitNums(i).stream().map(Day0034::getFactorial).reduce(Long::sum).get();
            if(i == sumResult) {
                summa += i;
                System.out.println(sumResult);
            }
        }

        return summa;
    }

    static long getFactorial(int value) {
        if(value == 0) {
            return 1;
        }
        return factorialMap.get(value);
    }

    static long fillFactorial(int value) {
        long res = 1;
        int curV = 0;
        while (curV < value) {
            res *= ++curV;
            factorialMap.put(curV, res);
        }
        return res;
    }

    static List<Integer> splitNums(long value) {
        long cur = value;
        List<Integer> nums = new ArrayList<>();
        while (cur >= 1) {
            int digit = (int)cur % 10;
            nums.add(digit);
            cur /= 10;
        }
        return nums;
    }
}
