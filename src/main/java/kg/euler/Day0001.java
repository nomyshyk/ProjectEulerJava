package kg.euler;

import java.util.stream.IntStream;

public class Day0001 {
    public static void main(String[] args) {
        long result = peDay1(3, 5, 1000);
        System.out.println(result);
    }

    static long peDay1(int a, int b, int max) {
        return IntStream.range(1, max).filter((value -> (value % a == 0 || value % b == 0))).sum();
    }
}
