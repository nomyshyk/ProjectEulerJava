package kg.euler;

public class Day0026 {

    public static void main(String[] args) {
        long result = solution(1000, 1000);
        System.out.println("result is " + result);
    }

    // Find the value of d < 1000
    // for which 1/d
    // contains the longest recurring cycle in its decimal fraction part.
    static long solution(int limit, int precision) {

        long max = 0;
        int longestDivisor = 1;
        String longestStr = null;
        for(int i = 2; i < limit; i++) {
            //Its not needed but lets leave it for fun
            String resultOfDivision = divideOne(i, precision);

            if(i % 2 == 0 || i % 5 == 0) {
                continue;
            }
            int multOrder = multiplicativeOrder(10, i);
            if(multOrder > max) {
                max = multOrder;
                longestDivisor = i;
                longestStr = resultOfDivision;
            }
        }
        System.out.printf("longest divisor is %s, it has recurring length %s \n div result is %s \n",
                longestDivisor, max, longestStr);
        return longestDivisor;
    }

    static String divideOne(int num, int precision) {
        int steps = 0;
        int val = 1;
        int res = 0;

        StringBuilder sb = new StringBuilder();
        while (steps <= precision) {
            res = (val * 10) / num;
            sb.append(res);
            val = (val * 10) % num;
            if(val == 0) {
                break;
            }
            steps++;
        }
        return sb.toString();
    }

    // function for GCD
    static int GCD(int a, int b) {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }

    // Function return smallest integer that
    // holds condition A^k(mod N) = 1
    static int multiplicativeOrder(int A, int N) {
        if (GCD(A, N) != 1)
            return -1;

        // result store power of A that raised to
        // the power N-1
        int result = 1;

        int K = 1;

        while (K < N) {
            // modular arithmetic
            result = (result * A) % N;
            // return smallest +ve integer
            if (result == 1)
                return K;
            // increment power
            K++;
        }
        return -1;
    }
}