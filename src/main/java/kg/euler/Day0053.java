package kg.euler;

import java.math.BigDecimal;

public class Day0053 {

    public static void main(String[] args) {
        solution(1000000);
    }
    static BigDecimal[] factorialCache = new BigDecimal[101];
    // How many, not necessarily distinct, values of (n/r)
    // for 1 <= n <= 100 , are greater than one-million?
    static long solution(long limit) {
        int cnt = 0;
        factorialCache[0] = BigDecimal.ONE;
        for(int i = 1; i < 101; i++) {
            factorialCache[i] = factorialCache[i-1].multiply(BigDecimal.valueOf(i));
        }

        BigDecimal maxBI = BigDecimal.valueOf(limit);
        for (int n = 0; n < factorialCache.length; n++) {
            for (int r = 0; r <= n; r++) {
                BigDecimal denomin = factorialCache[r]
                        .multiply(factorialCache[n - r]);
                BigDecimal divResult = factorialCache[n].divide(denomin);
                if(divResult.compareTo(maxBI) >= 0) {
                    cnt++;
                    //System.out.printf("n = %s, r = %s, tot = %s \n", n, r, divResult);
                }
            }
        }

        System.out.println("result is " + cnt);
        return cnt;
    }
}
