package kg.euler;

public class Day0030 {
    public static void main(String[] args) {
        long result = solution(5,  10000000);
        System.out.println("result is " + result);
    }

    // Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
    static long solution(int power, int max) {
        long result = 0;
        for (int i = 10; i <= max; i++) {
            int log10 = (int) Math.log10(i);
            int curVal = i;
            int razr = 0;
            long summa = 0;
            for (int j = log10; j >= 0; j--){
                razr = (int)(curVal / Math.pow(10, j));
                curVal = curVal - ((int) (razr * Math.pow(10, j)));
                summa += (long)Math.pow(razr, power);
            }
            if(summa == i) {
                System.out.println("num = " + summa);
                result += summa;
            }
        }
        return result;
    }
}