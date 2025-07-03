package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0047 {

    static List<Long> primeCache = new ArrayList<>();

    public static void main(String[] args) {
        solution(1000000,4);
    }

    //Find the first four consecutive integers to have four distinct
    //prime factors each. What is the first of these numbers?
    static long solution(int limit, int seqLen) {
        getPrimeList(limit);
        long result = 0;
        int cntConseq = 0;
        for(long i = 2; i <= limit; i++) {

            boolean checkCompound = divideNonPrimeNum(i, seqLen);
            if(checkCompound) {
                cntConseq++;
            } else {
                cntConseq = 0;
            }
            if(cntConseq == seqLen) {
                result = (i-cntConseq)+1;
                System.out.println("result is " + result);
                break;
            }
        }
        return result;
    }

    static boolean divideNonPrimeNum(long num, int limitDivs) {
        long curN = num;
        int iter = 1;
        long currDivisor = 0;
        int cntDivs = 0;
        while (curN > 1) {
            long toDiv = primeCache.get(iter);
            long divisor = curN % toDiv;
            if(divisor == 0) {
                curN /= toDiv;
                if(currDivisor == 0) {
                    currDivisor = iter;
                    cntDivs++;
                } else {
                    if(currDivisor != iter) {
                        cntDivs++;
                        currDivisor = iter;
                    }
                }
            } else {
                iter++;
            }
            if(limitDivs < cntDivs) {
                return false;
            }
        }
        return limitDivs == cntDivs;
    }

    static void getPrimeList(long num) {
        for(long i = 1; i <= num; i++) {
            if(checkPrime(i)) {
                primeCache.add(i);
            }
        }
    }

    static boolean checkPrime(long num) {
        if(num == 1) {
            return true;
        }
        for(int i = 1; i < primeCache.size(); i++) {
            long iValue = primeCache.get(i);
            if(!(iValue < (num/2) + 1)) {
                break;
            }
            if(num % iValue == 0) {
                return false;
            }
        }
        return true;
    }
}
