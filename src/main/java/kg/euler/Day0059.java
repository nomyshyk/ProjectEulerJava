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

        long solution = solution();
        System.out.println("result is = " + solution);
    }

    static long solution() {
        List<Byte> parsedData = parseInput(inputLines("files/problem_0059.txt"));
        List<Byte> bestKey = null;
        List<Byte> bestDecrypted = null;
        double bestScore = Double.NaN;
        for (byte x = 'a'; x <= 'z'; x++) {
            for (byte y = 'a'; y <= 'z'; y++) {
                for (byte z = 'a'; z <= 'z'; z++) {
                    List<Byte> key = List.of(x, y, z);
                    List<Byte> decrypted = decrypt(parsedData, key);
                    double score = score(decrypted);
                    if (bestKey == null || score > bestScore) {
                        bestKey = key;
                        bestDecrypted = decrypted;
                        bestScore = score;
                        System.out.printf("%s%s%s\n", (char)x, (char)y, (char)z);
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < bestDecrypted.size(); i++) {
            sum += bestDecrypted.get(i);
        }
        return sum;
    }

    private static double score(List<Byte> b) {
        double sum = 0;
        for (int i = 0; i < b.size(); i++) {
            char c = (char)((byte)b.get(i));
            if ('A' <= c && c <= 'Z')
                sum += 1;
            else if ('a' <= c && c <= 'z')
                sum += 2;
            else if (c < 0x20 || c == 0x7F)
                sum -= 10;
        }
        return sum;
    }

    public static String inputLines(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        return reader.lines().collect(Collectors.joining("\n"));
    }

    private static List<Byte> decrypt(List<Byte> cipherByteList, List<Byte> key) {
        List<Byte> text = new ArrayList<>();
        for (int i = 0; i < cipherByteList.size(); i++) {
            text.add((byte)(cipherByteList.get(i) ^ key.get(i % key.size())));
        }
        return text;
    }

    static List<Byte> parseInput(String str) {
        List<Byte> list = new ArrayList<>();
        for(String line : str.split(",")) {
            list.add(Byte.parseByte(line));
        }
        return list;
    }
}
