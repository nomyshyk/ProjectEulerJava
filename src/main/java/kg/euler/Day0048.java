package kg.euler;

import java.math.BigInteger;

public class Day0048 {

    public static void main(String[] args) {
        solution(1000);
    }

    //Find the last ten digits of the series, 1^1, 2^2, 3^3, 1000^1000.
    static String solution(int limit) {
        BigInteger result = BigInteger.ZERO;

        for(int i = 1; i <= limit; i++) {
            BigInteger powed = BigInteger.valueOf(i).pow(i);
            result = result.add(powed);
        }

        String last = result.toString();
        String substrLast10 = last.substring(last.length() - 10);
        System.out.println("result is " + substrLast10);
        return substrLast10;
    }
}
