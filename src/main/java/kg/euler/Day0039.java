package kg.euler;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Day0039 {
    public static void main(String[] args) {
        solution(1001);
    }

    // For which value of p<=100, is the number of solutions maximised?
    static long solution(int total) {

        Map<Integer, Set<Pair>> map = new LinkedHashMap<>();

        for (int a = 1; a < total; a++) {
            for (int b = 1; b < total; b++){
                int multy = (int) (Math.pow(a, 2) + Math.pow(b, 2));
                int sqrt = (int)(Math.sqrt(multy));

                if(sqrt <= total && sqrt*sqrt == multy) {
                    Set<Pair> uniquePairs = map.getOrDefault(sqrt, new LinkedHashSet<>());
                    Pair pair;
                    if(a > b) {
                        pair = new Pair(a, b);
                    } else {
                        pair = new Pair(b, a);
                    }
                    uniquePairs.add(pair);
                    map.put(sqrt, uniquePairs);
                }
            }
        }


        Map<Integer, Integer> mapCount = new LinkedHashMap<>();

        for(Map.Entry<Integer, Set<Pair>> entries : map.entrySet()) {
            int num = entries.getKey();
            for(Pair pair : entries.getValue()) {
                int key = pair.a + pair.b + num;
                mapCount.put(key, mapCount.getOrDefault(key, 0)+1);
            }
        }

        int max = 0;
        int result = 0;
        for(Map.Entry<Integer, Integer> entries : mapCount.entrySet()) {
            if(entries.getValue() > max && entries.getKey() <= total) {
                max = entries.getValue();
                result = entries.getKey();
            }
        }
        System.out.println(mapCount);
        System.out.println("max P entries = " + mapCount.get(result));
        System.out.println("result = " + result);

        return result;
    }

    record Pair(int a, int b){}
}
