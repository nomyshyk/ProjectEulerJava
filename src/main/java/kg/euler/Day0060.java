package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day0060 {

    static Set<Utils.Pair<Long, Long>> primePairs = new HashSet<>();
    public static void main(String[] args) {

        List<Long> primes = Utils.generatePrimeList(1000);
        long solution = solution(primes);
        System.out.println("result is = " + solution);
    }

    // Find the lowest sum for a set of five primes for which any
    // two primes concatenate to produce another prime.
    static long solution(List<Long> primes) {
        for (int i = 0; i < primes.size(); i++) {
//            if(nonPrime.contains(primes.get(i))) {
//                continue;
//            }
            for (int j = 0; j < primes.size(); j++) {
//                if(nonPrime.contains(primes.get(j))) {
//                    continue;
//                }
                if(i >= j) {
                    continue;
                }
                if(Utils.checkPrime(Long.parseLong(String.format("%s%s",primes.get(i),primes.get(j))))
                    && Utils.checkPrime(Long.parseLong(String.format("%s%s",primes.get(j),primes.get(i))))) {
                    System.out.printf("%s %s are prime when permutated \n", primes.get(i), primes.get(j));
                    primePairs.add(new Utils.Pair<>(primes.get(i), primes.get(j)));
                } else {
//                    nonPrime.add(primes.get(i));
//                    nonPrime.add(primes.get(j));
                }
            }
        }
        return 0;
    }
}
