package kg.euler;

import java.util.HashMap;
import java.util.Map;

public class Day0041 {

    public static void main(String[] args) {
        solution(999999999);
    }

    //static int[] setka = new int[999999999];

    // What is the largest n-digit pandigital prime that exists?
    static long solution(int limit) {
        int max = 0;
        for(int i = limit; i > 0; i = i-2) {
            if(i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                continue;
            }

           if(isPandigit(i) && isPrime(i)) {
               max = i;
               break;
           }
        }
        System.out.println("result is " + max);
        return max;
    }

    static boolean isPandigit(int num) {
        int curNum = num;
        int summa = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int len = (int)Math.ceil(Math.log10(curNum));
        while(curNum >= 1) {
            int digit = curNum % 10;
            summa += digit;
            if(digit == 0) {
                return false;
            }
            int value = map.getOrDefault(digit, 0);
            if(value >= 1) {
                return false;
            }
            map.put(digit, value + 1);
            curNum /= 10;
        }

        if(summa % 3 == 0) {
            return false;
        }
        int iter = 1;
        while (iter <= len) {
            int i = map.getOrDefault(iter, 0);
            if(i == 0) {
                return false;
            }
            iter++;
        }
        return true;
    }

    static boolean isPrime(long num) {
        if(num < 2) {
            return false;
        }

        if(num % 2 == 0){
            return false;
        }

        for(long i=3; i < Math.sqrt(num+1); i+=2) {
            if(num % i == 0){
                return false;
            }
        }
        //setka.put(num, true);
        return true;
    }

//    static int fulfillSetka(int limit) {
//        int curV = 2;
//        int iter = 0;
//        while (limit >= curV) {
//            boolean prime = isPrime(curV);
//            if(prime) {
//                setka[iter++] = curV;
//            }
//            curV++;
//        }
//    }

}
