package kg.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Day0037 {

    static Map<Long, Boolean> setka = new HashMap<>();
    static Set<Integer> perms = new LinkedHashSet<>();
    public static void main(String[] args) {
        solution(11);
    }

    // Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
    static long solution(int limit) {
        return 0;
    }

    static int[] isAllOdd(int num) {
        if (num <= 1) {
            return null;
        }
        if (num == 2) {
            return new int[]{2};
        }
        Queue<Integer> nums = new LinkedList<>();
        while(num >= 1) {
            int digit = (num % 10);
            if (digit % 2 == 0) {
                return null;
            }
            nums.add(digit);
            num /= 10;
        }
        int[] numArr = new int[nums.size()];
        int i = nums.size()-1;
        while (!nums.isEmpty()) {
            numArr[i--] = nums.poll();
        }

        return numArr;
    }

    static boolean isPrime(long num) {
        if(num < 2) {
            return false;
        }

        Boolean isPrime = setka.get(num);
        if(isPrime != null) {
            return isPrime;
        }
        for(long i=2; i < Math.sqrt(num+1); i++) {
            if(num % i == 0){
                setka.put(i, false);
                return false;
            }
        }
        setka.put(num, true);
        return true;
    }
}
