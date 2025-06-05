package kg.euler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0033 {

    static Set<Integer> uniqueSet = new HashSet<>();
    public static void main(String[] args) {
        //float cancelingFraction = isCancelingFraction(49, 98);
        //System.out.println(cancelingFraction);
        long result = solution(999999);
    }

    // here are exactly four non-trivial examples of this type of fraction,
    // less than one in value, and containing two digits in the numerator and denominator.
    // If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
    static long solution(int topBorder) {
        for(int i = 10; i < 100; i++){
            for(int j = 10; j < 100; j++) {
                BigDecimal divide = BigDecimal.valueOf(i).divide(BigDecimal.valueOf(j), 10, RoundingMode.HALF_UP);
                BigDecimal cancelingFraction = isCancelingFraction(i, j);
                if(cancelingFraction.equals(BigDecimal.ZERO)) {
                    continue;
                }
                if(divide.equals(cancelingFraction)){
                    System.out.printf("%s and %s , isEqto %s and %s\n", i, j, divide, cancelingFraction);
                }
            }
        }
        return 0;
    }

    static BigDecimal isCancelingFraction(int numerator, int denominator) {
        if(numerator >= denominator) {
            return BigDecimal.ZERO;
        }
        List<Integer> numeratorVals = new ArrayList<>(splitNums(numerator));
        List<Integer> denominatorVals = new ArrayList<>(splitNums(denominator));

        //if have dup vals like 99, 11
        if(numeratorVals.size() == 1 || denominatorVals.size() == 1) {
            return BigDecimal.ZERO;
        }

        //if theres similar vals in two nums like 37 / 78
        int cntSimilars = 0;
        int similarVal = -1;
        for(int i = 0; i < numeratorVals.size(); i++) {
            for(int j = 0; j < denominatorVals.size(); j++) {
                if(i != j && numeratorVals.get(i).equals(denominatorVals.get(j))) {
                    cntSimilars++;
                    similarVal = denominatorVals.get(j);
                }
            }
        }
        if (cntSimilars < 1) {
            return BigDecimal.ZERO;
        }

        int newNumer = 0;
        int newDenom = 0;
        for(int i = 0; i < numeratorVals.size(); i++) {
            if(numeratorVals.get(i) != similarVal){
                newNumer = numeratorVals.get(i);
            }
        }

        for(int i = 0; i < denominatorVals.size(); i++) {
            if(denominatorVals.get(i) != similarVal){
                newDenom = denominatorVals.get(i);
            }
        }
        if (newDenom == 0) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal.valueOf(newNumer).divide(BigDecimal.valueOf(newDenom), 10, RoundingMode.HALF_UP));
    }

    static Set<Integer> splitNums(int value) {
        int cur = value;
        Set<Integer> nums = new LinkedHashSet<>();
        while (cur > 1) {
            int digit = cur % 10;
            nums.add(digit);
            cur /= 10;
        }
        //System.out.println(nums);
        return nums;
    }
}