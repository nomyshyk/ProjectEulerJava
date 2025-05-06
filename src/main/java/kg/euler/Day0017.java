package kg.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Day0017 {
    static String vals = """
            1,one
            2,two
            3,three
            4,four
            5,five
            6,six
            7,seven
            8,eight
            9,nine
            10,ten
            11,eleven
            12,twelve
            13,thirteen
            14,fourteen
            15,fifteen
            16,sixteen
            17,seventeen
            18,eighteen
            19,nineteen
            20,twenty
            30,thirty
            40,forty
            50,fifty
            60,sixty
            70,seventy
            80,eighty
            90,ninety
            100,hundred
            1000,thousand""";
    static Map <Integer, String> mapVal = parseInput(vals);
    public static void main(String[] args) {
        //System.out.println(mapVal);
        solution(1000);
    }


    // If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
    // how many letters would be used?
    static long solution(int number) {
        int totalLetters = 0;
        for(int i = 1; i <= number; i++){
            String word = numberToWord(i);
            totalLetters += countLetters(word);
        }
        System.out.println("Total lettets number = " + totalLetters);
        return totalLetters;
    }

    static int countLetters(String text) {
        int cnt = 0;
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) != ' '){
                cnt++;
            }
        }
        return cnt;
    }

    static String numberToWord(int number) {
        int curNumber = number;
        StringBuilder sb = new StringBuilder();
        boolean hasHundreds = false;
        boolean andAdded = false;
        while (curNumber >= 1) {
            int zeros = (int)Math.floor(Math.log10(curNumber));

            int lead = Math.floorDiv(curNumber, (int) Math.pow(10, zeros));
            int substract = (int) Math.pow(10, zeros);

            if(lead * substract >= 100) {
                sb.append(mapVal.get(lead)).append(" ").append(mapVal.get(substract)).append(" ");
                hasHundreds = true;
            } else
            {
                if(hasHundreds && !andAdded) {
                    sb.append("and ");
                    andAdded = true;
                }
                if(mapVal.get(curNumber) != null) {
                    sb.append(mapVal.get(curNumber)).append(" ");
                    break;
                } else if(mapVal.get(lead * substract) != null) {
                    sb.append(mapVal.get(lead * substract)).append(" ");
                }
            }
            curNumber -= lead * substract;
        };
        System.out.println("number " + number + " is = " + sb.toString());
        return sb.toString();
    }

    static Map<Integer, String> parseInput(String str) {
        Map<Integer, String> map = new HashMap<>();
        for(String line : str.split("\n")) {
            String[] split = line.split(",");
            map.put(Integer.parseInt(split[0]), split[1]);
        }
        return map;
    }
}