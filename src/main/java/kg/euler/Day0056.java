package kg.euler;

import java.math.BigInteger;

public class Day0056 {

    static int LIMIT_A = 100;
    static int LIMIT_B = 100;

    public static void main(String[] args) {
        solution(LIMIT_A, LIMIT_B);
    }

    // considering natural numbers of the form a^b,
    // where a,b < 100
    // what is the maximum digital sum?
    static long solution(int limitA, int limitB) {
        long maxDigit = 0;
        for (int i = 1; i <= limitA; i++) {
            BigInteger curVal = BigInteger.ONE;
            for (int j = 1; j <= limitB; j++) {
                curVal = curVal.multiply(BigInteger.valueOf(i));
                String toStr = curVal.toString();
                int sumOfDigitsVar = sumOfDigits(toStr);
                if(sumOfDigitsVar > maxDigit) {
                    maxDigit = sumOfDigitsVar;
                }
            }
        }
        System.out.println(maxDigit);
        return maxDigit;
    }

    static int sumOfDigits(String string) {
        int summa = 0;
        for(int i = 0; i < string.length(); i++) {
            summa += Integer.valueOf(string.substring(i, i+1));
        }
        return summa;
    }
}
