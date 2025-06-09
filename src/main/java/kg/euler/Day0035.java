package kg.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Day0035 {

    static Map<Long, Boolean> setka = new HashMap<>();
    static Set<Integer> perms = new LinkedHashSet<>();
    public static void main(String[] args) {
        solution(1000000);
    }

    // How many circular primes are there below one million?
    static long solution(int limit) {
        outer:
        for(int i = 2; i < limit; i++) {
            boolean prime = isPrime(i);
            if(!prime) {
                continue;
            }
            int[] allOdd = isAllOdd(i);
            if(allOdd == null) {
                continue;
            }

            List<Integer> numRotated = new ArrayList<>();
            List<List<Integer>> rotate = rotate(allOdd);
            for(int j = 0; j < rotate.size(); j++) {
                int num = arrayToNumber(rotate.get(j));
                numRotated.add(num);
                if(!isPrime(num)) {
                    continue outer;
                }
            }
            perms.addAll(new ArrayList<>(numRotated));
        }
        System.out.println(perms);
        System.out.println("result is " + perms.size());
        return perms.size();
    }


    static int arrayToNumber (List<Integer> array) {
        int num = 0;
        int pow = 0;
        for(int i = array.size()-1; i >= 0; i--) {
            num += (array.get(i) * Math.pow(10, pow++));
        }
        return num;
    }

    static List<List<Integer>> rotate(int[] array) {
        List<Integer> initArr = Arrays.stream(array).boxed().toList();;
        List<List<Integer>> listInts = new ArrayList<>();

        List<Integer> permList = new ArrayList<>(initArr);

        for (int i = 0; i < initArr.size(); i++) {
            int first = permList.get(0);
            permList.remove(0);
            permList.add(first);
            listInts.add(new ArrayList<>(permList));
        }
        return listInts;
    }

    static int[] isAllOdd(int num) {
        if (num <= 1) {
            return null;
        }
        if (num == 2) {
            return new int[]{2};
        }
        Queue<Integer> nums = new LinkedList<>();
        while(num >= 1) {
            int digit = (num % 10);
            if (digit % 2 == 0) {
                return null;
            }
            nums.add(digit);
            num /= 10;
        }
        int[] numArr = new int[nums.size()];
        int i = nums.size()-1;
        while (!nums.isEmpty()) {
            numArr[i--] = nums.poll();
        }

        return numArr;
    }

    static boolean isPrime(long num) {
        if(num < 2) {
            return false;
        }

        Boolean isPrime = setka.get(num);
        if(isPrime != null) {
            return isPrime;
        }
        for(long i=2; i < Math.sqrt(num+1); i++) {
            if(num % i == 0){
                setka.put(i, false);
                return false;
            }
        }
        setka.put(num, true);
        return true;
    }
}
