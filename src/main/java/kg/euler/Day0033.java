package kg.euler;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Day0033 {

    public static void main(String[] args) {
        long result = solution();
        System.out.println("result " + result);
    }

    // here are exactly four non-trivial examples of this type of fraction,
    // less than one in value, and containing two digits in the numerator and denominator.
    // If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
    static long solution() {
        int mulDeno = 1;
        int mulNumer = 1;
        for(int i = 10; i < 100; i++){
            for(int j = 10; j < 100; j++) {
                float divide = (float) i/j;
                Pair cancelingFraction = isCancelingFraction(i, j);
                if(cancelingFraction == null) {
                    continue;
                }
                if(divide == cancelingFraction.val){
                    System.out.printf("%s and %s , isEqTo %s and %s\n", i, j, cancelingFraction.numerator, cancelingFraction.denominator);
                    mulDeno *= cancelingFraction.denominator;
                    mulNumer *= cancelingFraction.numerator;
                }
            }
        }

        return mulDeno / gcd(mulDeno, mulNumer);
    }

    static Pair isCancelingFraction(int numerator, int denominator) {
        if(numerator >= denominator) {
            return null;
        }
        List<Integer> numeratorVals = new ArrayList<>(splitNums(numerator));
        List<Integer> denominatorVals = new ArrayList<>(splitNums(denominator));

        //if have dup vals like 99, 11
        if(numeratorVals.size() == 1 || denominatorVals.size() == 1) {
            return null;
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
            return null;
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
            return null;
        }
        return new Pair((float)newNumer/newDenom, newNumer, newDenom);
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
    record Pair(float val, int numerator, int denominator){};

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
