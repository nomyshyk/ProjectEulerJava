package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0007 {
    public static void main(String[] args) {
        solution(10001);
    }

    //What is the 10001st prime number
    // num = 10001
    static long solution(int num) {
        List<Integer> primesList = new ArrayList<>();
        int primeCounter = 0;
        int iter = 0;
        while(primeCounter <= num) {
            iter++;
            if(checkPrime(iter)){
                primesList.add(iter);
                primeCounter++;
            }
        }
        System.out.println(primesList.get(primesList.size()-1));
        return primesList.get(primesList.size()-1);
    }

    static boolean checkPrime(long num) {
        for(int i=2; i < (num/2) + 1; i++) {
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
