package kg.euler;

import java.util.PriorityQueue;
import java.util.Queue;

public class Day0002 {
    public static void main(String[] args) {
        solution(4000000L);
    }

    static long solution(long max) {
        Queue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        long summa = 1;
        long result = 0L;
        for(int i = 0; i <= max; i++) {
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
}
