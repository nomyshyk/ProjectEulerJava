package kg.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Day0016 {
    public static void main(String[] args) {
        solution();
    }

    // What is the sum of the digits of the number 2^1000
    static long solution() {
        List<String> nums = generateStringNumberList(2, 20, 50);

        String curProduct = nums.get(0);
        for(int i = 1; i < nums.size(); i++) {
            curProduct = multiply(curProduct, nums.get(i));
        };

        System.out.println("product is = " + curProduct);
        int sumOfNums = 0;
        for(String v : curProduct.split("")) {
            sumOfNums += Integer.parseInt(v);
        }
        System.out.println("final sum of digits is = " + sumOfNums);
        return sumOfNums;
    }

    static List<String> generateStringNumberList(int base, int power, int times) {
        List<String> numList = new ArrayList<>();
        String someBigNumber = String.valueOf(((long)Math.pow(base, power)));
        for(int i = 0; i < times; i++) {
            //System.out.println(someBigNumber);
            numList.add(someBigNumber);
        }
        return numList;
    }

    static String multiply(String a, String b) {
        Deque<Integer> aQ = new LinkedList<>(
                Arrays.stream(a.split("")).map(Integer::parseInt).toList()
        );

        List<Integer> bList = Arrays.stream(b.split("")).map(Integer::parseInt).toList();

        List<Deque<Integer>> resQue = new ArrayList<>();

        while (!aQ.isEmpty()) {
            int aDig = aQ.pollLast();
            int ost = 0;
            int iter = bList.size()-1;
            Deque<Integer> innerQ = new LinkedList<>();
            while (iter >= 0 || ost > 0) {
                int bDig = iter < bList.size() ? (bList.get(iter)) : 0;
                int sumDig = (aDig * bDig) + ost;
                if(sumDig >= 10) {
                    ost = Math.floorDiv(sumDig, 10);
                    innerQ.addFirst(sumDig - (Math.floorDiv(sumDig, 10) * 10));
                } else {
                    innerQ.addFirst(sumDig);
                    ost = 0;
                }
                iter--;
            }
            resQue.add(innerQ);
        }

        Deque<Integer> curQ = resQue.get(0);
        for(int i = 1; i < resQue.size(); i++) {
            curQ = summarizeTwoArraysWithShiftOfSecond(curQ, resQue.get(i), i);
        }

        StringBuilder sb = new StringBuilder();
        while (!curQ.isEmpty()) {
            sb.append(curQ.poll());
        };

        return sb.toString();
    }

    static Deque<Integer> summarizeTwoArraysWithShiftOfSecond(Deque<Integer> q1, Deque<Integer> q2, int shiftSteps) {
        Deque<Integer> resultQ = new LinkedList<>();

        for(int i = 0; i < shiftSteps; i++) {
            q2.addLast(0);
        }

        int remainder = 0;
        int sumDigit = 0;
        while(!q1.isEmpty() || !q2.isEmpty() || remainder !=0) {
            int a = Optional.ofNullable(q1.pollLast()).orElse(0);
            int b = Optional.ofNullable(q2.pollLast()).orElse(0);
            int res = a + b + remainder;
            if(res >= 10) {
                remainder = 1;
                sumDigit = res - 10;
            } else {
                remainder = 0;
                sumDigit = res;
            }
            resultQ.addFirst(sumDigit);
        }

        return resultQ;
    }
}