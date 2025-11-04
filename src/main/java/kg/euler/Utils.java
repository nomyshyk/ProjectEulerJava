package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Long> primeCache = new ArrayList<>();

    static List<Long> generatePrimeList(long num) {
        int size = primeCache.size();
        long lastVal = size == 0 ? 1 : primeCache.get(size - 1);
        for(long i = lastVal + 1; i <= num; i++) {
            if(checkPrime(i)) {
                primeCache.add(i);
            }
        }
        return primeCache;
    }

    static boolean checkPrime(long num) {
        if(num == 1) {
            return false;
        }
        for(int i = 0; i < primeCache.size(); i++) {
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

    record Pair<L, R>(L left, R right){};
}
