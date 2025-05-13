package kg.euler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

public class Day0025 {

    public static void main(String[] args) {
        solution(1000);
    }

    // What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
    static long solution(int limit) {
        return fibonacciSequence(limit);
    }

    static long fibonacciSequence (int maxLength) {

        Deque<Integer> q1 = new LinkedList<>();
        Deque<Integer> q2 = new LinkedList<>();
        q1.add(1);
        q2.add(1);
        int iter = 2;
        while (q1.size() <= maxLength) {
            Deque<Integer> ni = new LinkedList<>(q1);
            q1 = summarizeTwoQueues(q1, q2);
            iter++;
            if(q1.size() >= maxLength) {
                break;
            }
            q2 = ni;
        }
        System.out.println(q1);
        System.out.println("iter = " + iter);
        return iter;
    }
    static Deque<Integer> summarizeTwoQueues(Deque<Integer> q1, Deque<Integer> q2) {
        Deque<Integer> resultQ = new LinkedList<>();
        int remainder = 0;
        int sumDigit = 0;
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