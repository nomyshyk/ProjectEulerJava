package kg.euler;

import java.util.HashSet;
import java.util.Set;

public class Day0010 {

    static Set<Integer> checked = new HashSet<>();
    public static void main(String[] args) {
        solution(2000000);
        //solution(10);
    }

    //Find the sum of all the primes below two million.
    static long solution(int maxValue) {
        long result = 0L;

       for(int i = 2; i < maxValue; i++){
           if(checked.contains(i)) {
               continue;
           }
           if(checkPrime(i)){
               result += i;
           }
           tableEratosfenSet(checked,i,maxValue);
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

    static Set<Integer> tableEratosfenSet(Set<Integer> ints, int numberToAdd, long limit) {
        for(int i = numberToAdd; i < limit; i+=numberToAdd) {
            ints.add(i);
        }
        return ints;
    }
}
