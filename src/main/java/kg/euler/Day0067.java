package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day0067 {
    static List<List<Integer>> pyramid = parseInput(inputLines("files/problem_0067.txt"));
    public static void main(String[] args) {
        solution(pyramid);
    }


    // Find the maximum total from top to bottom of the triangle below
    static long solution(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[][] updatedTriangle = new int[m][m];
        String[][] maxAddresses = new String[m][m];

        int finalMax = 0;
        String adrMax = null;
        for (int i = 0; i < triangle.size(); i++) {
            for (int j =0; j < triangle.get(i).size(); j++) {
                updatedTriangle[i][j] = triangle.get(i).get(j);
                int topRight = (i - 1 < 0) ? 0 : // topmost 0
                        (j < triangle.get(i-1).size()) ? updatedTriangle[i - 1][j]: 0;
                int topLeft = (i - 1 < 0) ? 0 : // topmost 0
                        (j >= 1) ? updatedTriangle[i - 1][j - 1] : 0;

                if(topLeft > topRight) {
                    maxAddresses[i][j] = String.format("%s-%s", i-1, j - 1);
                } else {
                    maxAddresses[i][j] = String.format("%s-%s", i-1, j);
                }

                updatedTriangle[i][j] = Math.max(topLeft, topRight) + updatedTriangle[i][j];
                if(finalMax < updatedTriangle[i][j]) {
                    finalMax = updatedTriangle[i][j];
                    adrMax = String.format("%s-%s", i, j);
                }
            }
        }

        long result = identifyChain(adrMax, maxAddresses, triangle, m);

        System.out.println("result is " + result);
        return result;
    }

    static long identifyChain(String addressMax, String[][] coords,
                                        List<List<Integer>> triangle, int maxSize) {
        int cnt = 0;
        String[] coord = addressMax.split("-");
        int i = Integer.parseInt(coord[0]);
        int j = Integer.parseInt(coord[1]);
        long summa = 0;
        while (cnt < maxSize) {
            summa += triangle.get(i).get(j);
            coord = coords[i][j].split("-");
            if(i == 0 && j ==0 ) {
                break;
            }
            i = Integer.parseInt(coord[0]);
            j = Integer.parseInt(coord[1]);

            cnt++;
        }
        return summa;
    }

    static List<List<Integer>> parseInput(String str) {
        List<List<Integer>> list = new ArrayList<>();
        for(String line : str.split("\n")) {
            List<Integer> ints = new ArrayList<>();
            String[] split = line.split(" ");
            for(String val : split) {
                ints.add(Integer.parseInt(val));
            }
            list.add(ints);
        }
        return list;
    }

    public static String inputLines(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        return reader.lines().collect(Collectors.joining("\n"));
    }
}