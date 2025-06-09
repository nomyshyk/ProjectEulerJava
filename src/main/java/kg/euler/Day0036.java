package kg.euler;

import java.util.LinkedList;
import java.util.Queue;

public class Day0036 {
    public static void main(String[] args) {
        solution(1000000);
    }

    // Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
    static long solution(int limit) {
        int summa = 0;
        for (int i = 0 ; i < limit; i++) {
            if(isPalindrome10(i) != null && isPalindrome02(i) != null) {
                summa += i;
            }
        }
        System.out.println(summa);
        return summa;
    }

    static String isPalindrome02(int num) {
        String binaryString = Integer.toBinaryString(num);
        for(int i = 0; i < binaryString.length(); i++) {
            if(binaryString.charAt(i) != binaryString.charAt(binaryString.length()-1-i)) {
                return null;
            }
        }
        return binaryString;
    }

    static int[] isPalindrome10(int num) {
        Queue<Integer> nums = new LinkedList<>();
        while(num >= 1) {
            int digit = (num % 10);
            nums.add(digit);
            num /= 10;
        }
        int[] numArr = new int[nums.size()];
        int i = nums.size()-1;
        while (!nums.isEmpty()) {
            numArr[i--] = nums.poll();
        }

        for(int j = 0; j < numArr.length/2; j++) {
            if (numArr[j] != numArr[numArr.length-1-j]) {
                return null;
            }
        }

        return numArr;
    }
}
