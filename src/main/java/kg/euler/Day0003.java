package kg.euler;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Day0003 {
    public static void main(String[] args) {
        solution(600851475143L);
    }

    static long solution(long num) {
        List<Long> primes = new ArrayList<>();
        long remainder = num;

        while (remainder > 1) {
            long[] res = checkPrime(remainder);
            if(remainder == res[1]) {
                primes.add(remainder);
                break;
            }
            remainder = res[1];
            long div = res[0];
            primes.add(div);
        }
        System.out.println(primes);
        long max = 0;
        for(long l : primes){
            max = Math.max(max, l);
        }

        return max;
    }

    static long[] checkPrime(long num) {
        for(int i=2; i < (num/2) + 1; i++) {
            if(num % i == 0){
                return new long[]{i, num/i};
            }
        }
        return new long[]{1, num};
    }
}
