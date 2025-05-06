package kg.euler;

import java.util.ArrayList;
import java.util.List;

public class Day0018 {
    static String vals = """
            75
            95 64
            17 47 82
            18 35 87 10
            20 04 82 47 65
            19 01 23 75 03 34
            88 02 77 73 07 63 67
            99 65 04 28 06 16 70 92
            41 41 26 56 83 40 80 70 33
            41 48 72 33 47 32 37 16 94 29
            53 71 44 65 25 43 91 52 97 51 14
            70 11 33 28 77 73 17 78 39 68 17 57
            91 71 52 38 17 14 91 43 58 50 27 29 48
            63 66 04 68 89 53 67 30 73 16 69 87 40 31
            04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
            """;
    static List<List<Integer>> pyramid = parseInput(vals);
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
}