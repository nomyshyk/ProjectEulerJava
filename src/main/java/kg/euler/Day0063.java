package kg.euler;

import java.math.BigInteger;

public class Day0063 {
    public static void main(String[] args) {
        long solution = solution(100);
        System.out.println("result is = " + solution);
    }

    // How many n-digit positive integers exist which are also an n-th power?
    static long solution(int max) {
        long cnt = 0;
        for (int i = 1; i < max; i ++) {
            for(int j = 1; j < max; j ++) {
                BigInteger pow = BigInteger.valueOf(i).pow(j);
                if(pow.toString().length() == j) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

}
