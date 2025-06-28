package kg.euler;

public class Day0045 {

    public static void main(String[] args) {
        solution(40755);
    }

    //Find the next triangle number that is also pentagonal and hexagonal.
    static long solution(int start) {
        long iter = start+5;
        long result = 0;
        while (true) {
            if(isHexagonal(iter) && isTriangular(iter) && isPentagonal(iter)) {
                result = iter;
                break;
            }
            iter+=5;
        }
        System.out.println("result is " + result);
        return result;
    }

    static boolean isPentagonal(long value) {
        long s = Math.round(Math.sqrt(1 + 24 * value));
        return (s*s == 1 + 24 * value) && ((1 + s) % 6 == 0);
    }

    static boolean isHexagonal(long value) {
        long s = Math.round(Math.sqrt(1+8*value));
        return (s*s == 1+8*value) && ((1 + s) % 4 == 0);
    }

    static boolean isTriangular(long value) {
        long s = Math.round(Math.sqrt(1+8*value));
        return (s*s == 1+8*value) && ((1 + s) % 2 == 0);
    }
}
