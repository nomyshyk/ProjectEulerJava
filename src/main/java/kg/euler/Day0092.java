package kg.euler;

public class Day0092 {
    public static void main(String[] args) {
        long solution = solution(10000000);
        System.out.println("result is = " + solution);
    }

    // How many starting numbers below ten million will arrive at 89?
    static long solution(int max) {
        long cnt = 0;

        for (int i = 2; i < max; i++) {
            long cur = i;
            while (true) {
                String[] nums = Long.toString(cur).split("");
                long newV = 0;
                for(String num : nums) {
                    int intV = Integer.parseInt(num);
                    int square = intV * intV;
                    newV = newV + square;
                }

                if(newV == 89 ) {
                    cnt ++;
                    break;
                }

                if(newV == 1) {
                    break;
                }

                cur = newV;
            }
        }
        return cnt;
    }

}
