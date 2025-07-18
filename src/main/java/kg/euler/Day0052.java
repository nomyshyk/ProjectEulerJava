package kg.euler;

import java.util.LinkedHashMap;
import java.util.Map;

public class Day0052 {

    public static void main(String[] args) {
        solution(1000000);
    }

    //Find the smallest positive integer, x , such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
    static long solution(long limit) {

        long result = 0;
        for (long i = 10; i < limit; i++) {
            boolean passedAll = true;
            Map<Long, int[]> mapBase = getMapPrint(i);
            for(int j = 2; j <= 6; j++) {
                Map<Long, int[]> mapMultiply = getMapPrint(i*j);
                boolean compareRes = compareMaps(mapBase, mapMultiply);
                if(!compareRes) {
                    passedAll = false;
                    break;
                }
            }
            if(passedAll) {
                System.out.println("result is " + i);
                result = i;
                break;
            }
        }
        return result;
    }

    static Map<Long, int[]> getMapPrint(long value) {
        int len = (int)Math.log10(value);
        long cur = value;
        Map<Long, int[]> ordMap = new LinkedHashMap<>();
        int pos = 0;
        while (cur >= 1) {
            long dig = cur % 10;
            cur /= 10;
            int[] ints = ordMap.getOrDefault(dig, new int[2]);
            // 0 - cnt
            // 1 - pos
            if(ints[0] == 1) {
                return null;
            }
            ints[0] = ints[0] + 1;
            ints[1] = len - pos;
            ordMap.put(dig, ints);
            pos++;
        }
        return ordMap;
    }

    static boolean compareMaps(Map<Long, int[]> map1, Map<Long, int[]> map2) {
        if(map1 == null || map2 == null) {
            return false;
        }

        if(map1.size() != map2.size()) {
            return false;
        }

        for(Map.Entry<Long, int[]> entry : map1.entrySet()) {
            long key1 = entry.getKey();
            int[] value2 = map2.get(key1);
            if(value2 == null) {
                return false;
            }
            int[] value1 = entry.getValue();
            if(value1[1] == value2[1]) {
                return false;
            }
        }
        return true;
    }
}
