package kg.euler;

import java.math.BigInteger;

public class Day0020 {

    public static void main(String[] args) {
        solution(100);
    }

    // Find the sum of the digits in the number 100!
    static long solution(int num) {

        int iter = 1;
        BigInteger product = BigInteger.ONE;
        while (iter <= num) {
            product = product.multiply(BigInteger.valueOf(iter));
            iter++;
        }
        System.out.println(product);
        String productStr = String.valueOf(product);
        int sum = 0;
        for(String str : productStr.split("")){
            sum += Integer.parseInt(str);
        }
        System.out.println("the answer is = " + sum);
        return sum;
    }
}