package kg.euler;

import java.util.List;
import java.util.stream.LongStream;

public class Day0044 {

    public static void main(String[] args) {
        solution(10000);
    }

    //Find the pair of pentagonal numbers, Pj and Pk ,
    // for which their sum and difference are pentagonal and
    // D = | Pk - Pj|  is minimised; what is the value of D?
    static long solution(int limit) {

        List<Long> pentagonalNumberList = generatePentagonalNumberList(limit);

        long minDiff = Long.MAX_VALUE;

        for (int i = 0; i < pentagonalNumberList.size(); i++) {
            for (int j = 0; j < pentagonalNumberList.size(); j++) {
                long a = pentagonalNumberList.get(i);
                long b = pentagonalNumberList.get(j);
                if(b >= a) {
                    break;
                }

                long summa = a + b;
                if(isPentagonal(summa) && isPentagonal(a - b)) {
                    System.out.printf("%s - %s = %s, diff is %s \n", a,
                            b, summa, a-b);
                    minDiff = Math.min((a-b), minDiff);
                }
            }
        }


        System.out.println("result is " + minDiff);
        return minDiff;
    }

    static List<Long> generatePentagonalNumberList(int limit) {
        return LongStream.range(1, limit).map(p -> (p * (3 * p - 1)) / 2).boxed().toList();
    }

    record PairDiff(long numA, long numB, long diff){

    }

    static boolean isPentagonal(long value) {
        long s = Math.round(Math.sqrt(1 + 24 * value));
        return (s*s == 1 + 24 * value) && ((1 + s) % 6 == 0);
    }
}
