package kg.euler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

public class Day0026 {

    public static void main(String[] args) {
        solution(1000);
    }

    // Find the value of d < 1000
    // for which 1/d
    // contains the longest recurring cycle in its decimal fraction part.
    static long solution(int limit) {
        for(int i = 2; i < limit; i++) {
            //System.out.println("v = " + BigDecimal.valueOf(1).divide(BigDecimal.valueOf(i), RoundingMode.valueOf(5)));
            BigDecimal divide = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(i), 100, RoundingMode.HALF_UP);

            System.out.println(i+ " " + divide);
        }
        return 0;
    }

    static long recurringCycleLen() {
        return 0;
    }
}