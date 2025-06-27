package kg.euler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

public class Day0044 {

    static Set<Pair> cache = new HashSet<>();

    public static void main(String[] args) {
        solution(100000);
    }

    //Find the pair of pentagonal numbers, Pj and Pk ,
    // for which their sum and difference are pentagonal and
    // D = | Pk - Pj|  is minimised; what is the value of D?
    static long solution(int limit) {

        List<Long> pentagonalNumberList = generatePentagonalNumberList(100000);
        System.out.println(pentagonalNumberList.get(pentagonalNumberList.size()-1));
        //System.out.println(pentagonalNumberList);
        long minDiff = Long.MAX_VALUE;

        for (int i = 0; i < pentagonalNumberList.size(); i++) {
            for (int j = 0; j < pentagonalNumberList.size(); j++) {
                Pair pair;
                long a = pentagonalNumberList.get(i);
                long b = pentagonalNumberList.get(j);
                if(a == b) {
                    continue;
                }
                if (a > b) {
                    pair = new Pair(a, b);
                } else {
                    pair = new Pair(b, a);
                }
                if(cache.contains(pair)) {
                    continue;
                } else {
                    cache.add(pair);
                }
                long summa = a + b;
                if(pentagonalNumberList.contains(summa)) {
                    long diff = Math.abs(a - b);
                    if (pentagonalNumberList.contains(diff)) {
                        System.out.printf("%s - %s = %s, diff is %s", a,
                                b, summa, diff);
                        minDiff = Math.min(diff, minDiff);
                    }
                }
            }
        }


        System.out.println("result is " + minDiff);
        return minDiff;
    }

    static List<Long> generatePentagonalNumberList(int limit) {

        return LongStream.range(1, limit).map(p -> (p * (3 * p - 1)) / 2).boxed().toList();

//        List<Long> pentagonalNumbers = new ArrayList<>();
//        for(int i = 0; i <= limit; i++) {
//            long pentagonal = (i * (3*i - 1)) / 2;
//            pentagonalNumbers.add(pentagonal);
//        }
//        return pentagonalNumbers;
    }

    record PairDiff(long numA, long numB, long diff){

    }

    static boolean isPentagonal() {
        return false;
    }

    record Pair(long a, long b){};
}
