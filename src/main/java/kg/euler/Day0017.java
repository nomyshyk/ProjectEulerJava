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
        System.out.println(mapVal);
        solution(30);
    }


    // If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
    // how many letters would be used?
    static long solution(int number) {
        int curNumber = number;
        int zeros = (int)Math.floor(Math.log10(curNumber));
        System.out.println(zeros);
        StringBuilder sb = new StringBuilder();
        while (curNumber >= 1) {

            int razr = (int) Math.pow(10, zeros);
            double modulo = curNumber % razr;

            if(modulo == 0 && curNumber >= 100) {
                int head = curNumber / razr;
                sb.append(mapVal.get(head)).append(" ").append(mapVal.get(razr));
            } else if(modulo == 0) {
                //int head = curNumber / razr;
                sb.append(mapVal.get(curNumber));
            }


            curNumber = Math.floorDiv(curNumber, 10);

//            String numStr = mapVal.get(curNumber);
//            if(numStr != null) {
//                sb.append(mapVal).append(" ");
//            }
//            curNumber /= 10;
            break;
        };
        System.out.println(sb.toString());
        return 0L;
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