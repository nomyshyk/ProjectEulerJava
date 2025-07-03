package kg.euler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Day0049 {

    public static void main(String[] args) {
        solution(1000, 9999);
    }

    //What 12-digit number do you form by concatenating the three terms in this sequence?
    static String solution(int min, int max) {
        int cntCombs = 0;
//        for (int i = min; i <= max; i++) {
//            for(int j = min; j <= max; j++) {
//
//            }
//        }
        boolean v = isPermutation(1000,1099);
        int init = min;
        while (init < 9999) {
            stepLabel:
            for(int step = 99; step < 3333; step++) {
                for(int i = 1; i < 3; i++) {
                    int next = init + (step*i);
                    if(next >= 10000) {

                    }
                    boolean isPermutation = isPermutation(i, next);
                    if(!isPermutation) {
                        continue stepLabel;
                    }
                }
            }
            init++;
        }

        return null;
    }

    static boolean isPermutation(int a, int b) {
        String aValue = String.valueOf(a);
        String bValue = String.valueOf(b);

        if (aValue.length() != bValue.length()) {
            return false;
        }

        Map<Character, Integer> cntMap = new HashMap();
        //Map<Character, Integer> chB = new HashMap();
        for (int i=0; i < aValue.length(); i++) {
            cntMap.put(aValue.charAt(i), cntMap.getOrDefault(aValue.charAt(i), 0)+1);
            cntMap.put(bValue.charAt(i), cntMap.getOrDefault(bValue.charAt(i), 0)-1);
        }
        //System.out.println(cntMap);

        for (Map.Entry<Character, Integer> entry : cntMap.entrySet()) {
            if(!entry.getValue().equals(0)) {
                return false;
            }
        }

        return true;
    }
}
