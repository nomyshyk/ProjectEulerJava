package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day0059 {
    public static void main(String[] args) {

        long solution = solution(10);
        //System.out.println("result is = " + solution);
    }

    // decrypt the message and find the sum of the ASCII values in the original text
    static long solution(int max) {
        List<Integer> parsedData = parseInput(inputLines("files/problem_0059.txt"));
        String allowedChars = " .,:;+?!/[]()'\\\"";
        char inc = 'a';
        boolean found = false;
        for (int i = 1; i <= 3; i++) {
            for(Integer val : parsedData) {
                char chr = (char) (inc ^ val);
                String strF = String.valueOf(chr);
                if(!(allowedChars.contains(strF) || (chr >= 'a' && chr <='z'))) {
                    System.out.println(strF);
                    break;
                }
            }
            if(!found) {
                inc++;
            }
        }



        //System.out.println(parsedData);
        return 0;
    }

    public static String inputLines(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        return reader.lines().collect(Collectors.joining("\n"));
    }

    static List<Integer> parseInput(String str) {
        List<Integer> list = new ArrayList<>();
        for(String line : str.split(",")) {
            list.add(Integer.parseInt(line));
        }
        return list;
    }
}
