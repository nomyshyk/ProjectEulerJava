package kg.euler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class Day0025 {

    public static void main(String[] args) {
        solution("0123456789", 1000000);
    }

    // What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
    static String solution(String permutatingStr, int limit) {
        return null;
    }

    static long fibonacciSequence (int maxLength) {
        StringBuilder summa = new StringBuilder("1");
        StringBuilder prevVal = new StringBuilder("1");
        while (summa.length() <= 1000) {
            summarizeTwoQueues();
        }
        for(int i = 0; ; i++) {
            if (summa >= max) {
                break;
            }
            queue.add(summa);

            summa = queue.poll() + summa;
            result += (summa%2==0) ? summa : 0;
        }
        System.out.println(result);
        return result;
    }

    static String summarizeTwoStrings(String s1, String s2) {
        //Deque<Integer> resultQ = new LinkedList<>();
        StringBuilder sb1 = new StringBuilder(s1);
        StringBuilder sb2 = new StringBuilder(s2);
        int remainder = 0;
        int sumDigit = 0;

        String[] first = s1.split("");
        String[] second = s2.split("");
        int longer = Math.max(first.length, second.length);
        for(int i = longer-1; i >= 0; i--) {

        }

        while(!q1.isEmpty() || !q2.isEmpty() || remainder !=0) {
            int a = Optional.ofNullable(q1.pollLast()).orElse(0);
            int b = Optional.ofNullable(q2.pollLast()).orElse(0);
            int res = a + b + remainder;
            if( res >= 10) {
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