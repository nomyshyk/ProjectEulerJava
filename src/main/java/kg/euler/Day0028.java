package kg.euler;

public class Day0028 {
    public static void main(String[] args) {
        long result = solution(1001);
        System.out.println("result is " + result);
    }

    // What is the sum of the numbers on the diagonals in a
    // 1001 by 1001
    // spiral formed in the same way?
    static long solution(int limit) {

        long topRight, topLeft, bottomLeft, bottomRight;
        int len = limit;
        long diagSum = 0;
        while (len > 0) {
            if(len == 1) {
                diagSum++;
                break;
            }
            topRight = (long)Math.pow(len, 2);
            topLeft = topRight - len + 1;
            bottomLeft = topLeft - len + 1;
            bottomRight = bottomLeft - len + 1;

            diagSum += topRight + topLeft + bottomLeft + bottomRight;
            len -= 2;
        }
        System.out.println(diagSum);
        return diagSum;
    }
}