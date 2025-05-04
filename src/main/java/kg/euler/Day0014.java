package kg.euler;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

public class Day0014 {

    static Map<Long, Long> cache = new HashMap<>();

    public static void main(String[] args) {
        solution(1000000);
    }

    // Which starting number, under one million, produces the longest chain?
    // Collatz Sequence
    static long solution(int maxStartingNum) {
        long maxSequence = 0L;
        long startNum = 0;
        for(long i = 3; i < maxStartingNum; i++) {
            long collatz = collatzWhile(i);
            if(maxSequence < collatz) {
                startNum = i;
                maxSequence = collatz;
            }
        }
        System.out.printf("start number is %s, seq len is %s \n", startNum, maxSequence);
        return startNum;
    }

    static long collatzWhile(long num) {
        long cnt = 1L;
        Deque<Long> valsInQ = new LinkedList<>();

        while(num != 1) {
            valsInQ.add(num);
            long cacheVal = cache.getOrDefault(num,0L);
            if (cacheVal != 0L) {
                cnt += cacheVal;
                break;
            }

            if(num % 2  == 0) {
                num /= 2;
            } else {
                num = (3 * num) + 1;
            }
            cnt++;
        }

        // Add sequence to cache
        long retval = cnt;
        while (cnt > 0) {
            Long val = Optional.ofNullable(valsInQ.pollFirst()).orElse(1L);
            cache.putIfAbsent(val, cnt);
            cnt--;
        }

        return retval;
    }

    // This is cool, but it's not working for large numbers
    // recursion is too deep, even if dynamic programming is used
    static long collatzRecursive(int num) {
        long cacheVal = cache.getOrDefault(num,0L);
        if (cacheVal != 0L) {
            //System.out.println("hit recursive");
            return cacheVal;
        }
        long cnt = 0L;
        if(num == 1) {
            return 1;
        }

        if(num % 2  == 0) {
            num /= 2;
        } else {
            num = (3 * num) + 1;
        }
        cnt ++;
        return cnt + collatzRecursive(num);
    }
}
