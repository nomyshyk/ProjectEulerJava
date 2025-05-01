package kg.euler;

import java.util.ArrayList;

public class Day0004 {
    public static void main(String[] args) {
        solution(999);
    }

    //Find the largest palindrome made from the product of two
    //3-digit numbers.
    static long solution(long num) {
        long result = 0;
        for (int i = 1; i <= num; i++){
            for(int j = 1; j <= num; j++) {
                int val = i * j;
                if(isMirror(val)) {
                    result = Math.max(result, val);
                }
            }
        }
        System.out.println("result is = " + result);
        return result;
    }

    public static boolean isMirror(long num) {
        long razr = razr(num);
        long perem = num;
        ArrayList<Integer> values = new ArrayList<>();

        while (razr > 0) {
            int v = (int) (perem / Math.pow(10, razr-1));
            values.add(v);
            perem = (long) (perem - (v * Math.pow(10, razr-1)));
            razr--;
        }

        for(int i = 0; i < values.size(); i++) {
            if(!values.get(i).equals(values.get(values.size()-i-1))) {
                return false;
            }
        }
        //System.out.println(values);
        return true;
    }

    static long razr(long num) {
        long val = num;
        int cnt = 0;
        while (val > 1) {
            val /= 10;
            cnt++;
        }
        return cnt;
    }
}
