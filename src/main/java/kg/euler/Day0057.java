package kg.euler;

import java.math.BigInteger;

public class Day0057 {

    static int LIMIT = 1000;

    public static void main(String[] args) {
        int solution = solution(LIMIT);
        System.out.println("result is = " + solution);
    }

    // In the first one-thousand expansions, how many fractions
    // contain a numerator with more digits than the denominator?
    static int solution(int max) {
        BigInteger nom = BigInteger.valueOf(3);
        BigInteger denom = BigInteger.valueOf(2);
        int cnt = 0;
        for(int i = 1; i < max; i++) {
            BigInteger newNom = (denom.multiply(BigInteger.valueOf(2)).add(nom));
            BigInteger newDenom = nom.add(denom);
            if((newNom.toString().length()) > (newDenom.toString().length())) {
                cnt++;
            }
            nom = newNom;
            denom = newDenom;
        }
        return cnt;
    }
}
