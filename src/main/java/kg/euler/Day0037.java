package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Day0037 {

    static Map<Long, Boolean> setka = new HashMap<>();
    static Map<Integer, Boolean> cache = new HashMap<>();
    public static void main(String[] args) {
        solution(1000000);
    }

    // Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
    static long solution(int limit) {
        int cnt = 0;
        long summa = 0;
        outer:
        for(int k = 10; k < limit; k++) {
            if(!isPrime(k)) {
                cache.put(k, false);
                continue ;
            }
            int[] allOdd = isAllOdd(k);
            if(allOdd == null) {
                cache.put(k, false);
                continue;
            }

            Set<Integer> vals = new LinkedHashSet<>(truncateRight(k));
            vals.addAll(truncateLeft(k));

            for(int val : vals) {
                if(!isPrime(val)) {
                    continue outer;
                }
            }
            summa += k;
            cnt++;
            System.out.println("k " + k);
            System.out.println("cnt = " + cnt);

            if (cnt == 11) {
                System.out.println("result = " + summa);
                return summa;
            }
        }

        return summa;
    }

    static List<Integer> truncateRight(int num) {
        int v = num;
        List<Integer> list = new ArrayList<>();
        while (v > 1) {
            v/=10;
            if(v>0) {
                list.add(v);
            }
        }
        return list;
    }

    static List<Integer> truncateLeft(int num) {
        int v = num;
        List<Integer> list = new ArrayList<>();
        while (v > 1) {
            int tens = (int)Math.pow(10, Math.ceil(Math.log10(v))-1);
            int lead = v / tens;
            v -= lead*tens;
            if(v > 0) {
                list.add(v);
            }
        }
        return list;
    }

    static int[] isAllOdd(int num) {
        if (num <= 1) {
            return null;
        }
        if (num == 2) {
            return new int[]{2};
        }
        Queue<Integer> nums = new LinkedList<>();
        boolean isLast = false;
        while(num >= 1) {
            int digit = (num % 10);
            if (digit % 2 == 0 && digit != 2) {
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
                return false;
            }
        }
        setka.put(num, true);
        return true;
    }
}
