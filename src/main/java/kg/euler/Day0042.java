package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day0042 {

    public static void main(String[] args) {
        solution(7654321);
    }

    //how many are triangle words?
    static long solution(int limit) {
        String words[] = inputLines("files/problem_0042.txt")
                .replaceAll("\"", "")
                .split(",");

        List<Integer> triangleNums = generateTriangleNums(100);

        int cnt = 0;
        for(String word : words) {
            int summa = transformWordToNum(word);
            if (triangleNums.contains(summa)) {
                cnt++;
            };
        }
        System.out.println("result is " + cnt);
        return cnt;
    }

    static List<Integer> generateTriangleNums(int max) {
        List<Integer> trVals = new ArrayList<>();
        for(int n = 1; n < max; n++) {
            int triangleNumber = (n + 1) * n;
            triangleNumber /= 2;
            trVals.add(triangleNumber);
        }
        return trVals;
    }

    static int transformWordToNum(String word) {
        int sum = 0;
        for(int i = 0; i < word.length(); i++) {
            sum += word.charAt(i) - 'A' + 1;
        }
        return sum;
    }

    static String inputLines(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
