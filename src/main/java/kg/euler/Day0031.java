package kg.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day0031 {

    final static int[] COINS = {1, 2, 5, 10, 20, 50, 100, 200};
    static Set<Map<Integer, Integer>> checkedCombs = new HashSet<>();
    public static void main(String[] args) {
        long result = solution(10, new LinkedHashMap<>());
        System.out.println(checkedCombs);
    }

    // How many different ways can £2 be made using any number of coins?
    static long solution(int targetAmount, Map<Integer, Integer> coinCombination) {
        System.out.println(targetAmount);
        System.out.println(coinCombination);
        System.out.println("hash=" + coinCombination.hashCode());
        if(targetAmount == 0) {
            if(!checkedCombs.contains(coinCombination)) {
                checkedCombs.add(new LinkedHashMap<>(coinCombination));
            }
            coinCombination.clear();
            return 0;
        } else if (targetAmount < 0) {
            System.out.println();
            coinCombination.clear();
            return 0;
        }

        for(int i = 0; i < COINS.length; i++) {
//            if(targetAmount == 0) {
//                System.out.println("bingo");
//                coinCombination.clear();
//                return 0;
//            }
            if(targetAmount >= COINS[i]) {
                //System.out.println("lg");
                targetAmount -= COINS[i];
                coinCombination.put(COINS[i], coinCombination.getOrDefault(COINS[i], 0) + 1);
                solution(targetAmount, coinCombination);
            }
//            else {
//                //coinCombination.clear();
//                return 0;
//            }
        }
        return 0;
    }
}