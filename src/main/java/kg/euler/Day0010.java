package kg.euler;

import java.util.HashSet;
import java.util.Set;

public class Day0010 {

    static Set<Integer> checked = new HashSet<>();
    public static void main(String[] args) {
        //solution(2000000);
        solution(500000);
    }

    //Find the sum of all the primes below two million.
    static long solution(int maxValue) {
        long result = 0L;
        outer:
       for(int i = 2; i < maxValue; i++){

           //IntStream.range(i, maxValue).filter(value -> value);

           if(checkPrime(i)){
               result += i;
           }
//           checked.add(i);
       }
        System.out.println(result);
       return result;
    }

    static boolean checkPrime(int num) {
        for(int i=2; i < (num/2) + 1; i++) {
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
