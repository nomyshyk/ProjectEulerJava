package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0024 {

    public static void main(String[] args) {
        solution("012");
    }

    // What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
    static long solution(String permutatingStr) {

        StringBuilder sb = new StringBuilder(permutatingStr);
        int idx = -1;
        for(int i = permutatingStr.length() - 1; i > 0; i--) {
            if(sb.charAt(i) > sb.charAt(i-1)) {
                idx = i;
            }
        }
        System.out.println(idx);
        return 0;
    }
}