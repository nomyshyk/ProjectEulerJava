package kg.euler;

import java.util.LinkedHashSet;
import java.util.Set;

public class Day0031 {

    final static int[] COINS = {1, 2, 5, 10, 20, 50, 100, 200};
    public static void main(String[] args) {
        long result = solution(200);
    }

    // How many different ways can £2 be made using any number of coins?
    static long solution(int goalVal) {
        int amtOfNominals = COINS.length;
        int [][] matrix = new int[goalVal+1][amtOfNominals];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 1;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
                matrix[i][j] += matrix[i][j-1];
                if(i >= COINS[j]) {
                    matrix[i][j] += matrix[i - COINS[j]][j];
                }
            }
        }
        int result = matrix[goalVal][amtOfNominals-1];
        System.out.println(result);
        return result;
    }

    static Set<Integer> splitNums(int value) {
        int cur = value;
        Set<Integer> nums = new LinkedHashSet<>();
        while (cur >= 1) {
            int digit = cur % 10;
            nums.add(digit);
            cur /= 10;
        }
        return nums;
    }
}