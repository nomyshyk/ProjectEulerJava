package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day0022 {

    public static void main(String[] args) {
        solution(10000);
    }

    // What is the total of all the name scores in the file?
    static long solution(int num) {
        String text = inputLines("files/problem_0022.txt");
        String[] names = text.replaceAll("\"", "").split(",");
        // too lazy to do custom natural sorting
        Arrays.sort(names);

        int iter = 0;
        long result = 0;
        for (String name : names) {
            iter++;
            //System.out.println(name + " " + iter);
            result += summa(name, iter);
        }

        System.out.println(result);
        return result;
    }

    static long summa(String word, int orderNo) {
        int a = 'A';
        long summa = 0;
        for(int i = 0; i < word.length(); i++) {
            int charCode = word.charAt(i);
            int value = charCode - a + 1;
            summa += value;
        }
        return summa * orderNo;
    }

    public static String inputLines(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        return reader.lines().collect(Collectors.joining("\n"));
    }
}