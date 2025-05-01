package kg.euler;

import java.util.ArrayList;

public class Day0005 {
    public static void main(String[] args) {
        solution(20);
    }

    //What is the smallest positive number that is evenly divisible by all of the numbers from
    // 1 to 20
    // num = 20
    static long solution(long num) {
        long limit = 1L;
        for(int i = 1; i <= num; i++) {
            limit *= i;
        }
        System.out.println(limit);
        outer:
        for(long i = num; i < limit; i = i+num) {
            for(long j = num; j > 0; j--) {
                if (i % j != 0) {
                    continue outer;
                }
            }
            System.out.println(i);
            return i;
        }
        return limit;
    }
}
