package kg.euler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Day0055 {

    static int LIMIT_CYCLE = 50;
    static int LIMIT_NUMS = 10000;

    public static void main(String[] args) {
        solution(LIMIT_NUMS);
    }

    // How many Lychrel numbers are there below ten-thousand?
    static long solution(long limit) {
        long counterLychrel = 0;
        for (int i = 1; i <= limit; i++) {
            int tries = 1;
            BigInteger valA = BigInteger.valueOf(i);
            BigInteger valB = reverse(valA);
            boolean isLychrel = true;
            while(tries < LIMIT_CYCLE) {
                BigInteger summa = valA.add(valB);
                if(isPalindrome(summa)) {
                    isLychrel = false;
                    break;
                }
                tries++;
                valA = summa;
                valB = reverse(summa);
            }
            if(isLychrel){
                counterLychrel++;
            }
        }
        System.out.println(counterLychrel);
        return counterLychrel;
    }

    static BigInteger reverse (BigInteger num) {
        int razr = num.toString().length();
        BigInteger reversedValue = BigInteger.ZERO;
        while(num.compareTo(BigInteger.ONE) >= 0) {
            BigInteger digit = (num.mod(BigInteger.valueOf(10)));
            razr--;
            reversedValue = reversedValue.add(digit.multiply(BigInteger.TEN.pow(razr)));
            num = num.divide(BigInteger.TEN);
        }
        return reversedValue;
    }

    static boolean isPalindrome(BigInteger num) {
        String value = String.valueOf(num);
        for(int i = 0; i < value.length()/2 + 1; i++) {
            if(value.charAt(i) != value.charAt(value.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
}
