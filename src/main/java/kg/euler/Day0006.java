package kg.euler;

public class Day0006 {
    public static void main(String[] args) {
        solution(100);
    }

    //Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
    // num = 100
    static long solution(long num) {
        long base = 0L;
        long powerBase = 0L;
        for(int i = 1; i <= num; i++) {
            base += i;
            powerBase += (long) Math.pow(i, 2);
        }
        base = (long) Math.pow(base, 2);

        System.out.println(base - powerBase);
        return (base - powerBase);
    }
}
