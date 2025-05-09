package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0024 {

    public static void main(String[] args) {
        solution("120");
    }

    // What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
    static long solution(String permutatingStr) {

        StringBuilder sb = new StringBuilder(permutatingStr);
        int idx = -1;
        int max = -1;
        boolean foundGreater = true;
        int maxIdx = -1;
        char val = 0;

        while (foundGreater) {
            for(int i = permutatingStr.length() - 1; i > 0; i--) {
                if(sb.charAt(i) > sb.charAt(i-1) && idx == -1) {
                    idx = i-1;
                    val = sb.charAt(i-1);
                }
                if(idx >= 0 && val < sb.charAt(i)) {
                    if(max < sb.charAt(i)) {
                        max = sb.charAt(i);
                        maxIdx = i;
                    }
                }
            }
            System.out.printf("Idx = %s, maxIdx = %s, char[idx]=%s, char[maxIdx]=%s \n",
                    idx, maxIdx, sb.charAt(idx), sb.charAt(maxIdx));
            break;
        }


        System.out.println(idx);
        return 0;
    }
}