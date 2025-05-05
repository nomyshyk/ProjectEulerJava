package kg.euler;

import java.util.HashMap;
import java.util.Map;

public class Day0015 {

    static Map<String, Long> cache = new HashMap<>();

    public static void main(String[] args) {
        solution(21);
    }

    // Starting in the top left corner of a 2 x 2 grid,
    // and only being able to move to the right and down, there are exactly 6
    // routes to the bottom right corner.
    // How many such routes are there through a
    // 20 x 20 grid?
    static long solution(int maxWidthNLength) {
        long[][] matrix = new long[maxWidthNLength][maxWidthNLength];
        long result = summarizeAdjacent(matrix);
        return result;
    }

    // Don't need BFS here. This will work, but too slowly
//    static long traverseBFS(List<List<Integer>> matrix) {
//        int sizeA = matrix.size();
//        int sizeB = matrix.get(0).size();
//        Queue<Point> q = new LinkedList<>();
//        //List<String> paths = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//        q.add(new Point(0, 0));
//        long numPaths = 0;
//        while(!q.isEmpty()){
//            Point p = q.poll();
//            if(p.a < sizeA-1) {
//                q.add(new Point(p.a+1, p.b));
//            }
//            if(p.b < sizeB-1) {
//                q.add(new Point(p.a, p.b+1));
//            }
//            if(p.a == sizeA-1 && p.b == sizeB-1) {
//                //sb.append(String.format("%s-%s ",p.a, p.b));
////                paths.add(sb.toString());
//                numPaths++;
//            }
//        }
//
////        for(String path : paths){
////            if(path.contains(String.format("%s-%s ",sizeA-1, sizeB-1))){
////                numPaths++;
////            };
////        }
//        System.out.println(numPaths);
//        return numPaths;
//    }

    //this is pretty cool math solution
    static long summarizeAdjacent(long[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(i == 0 && j == 0) {
                    matrix[i][j] = 0;
                }
                // 1-st row and column is always 1, except [0,0] which is = 0
                else if(i == 0 || j == 0) {
                    matrix[i][j] = 1;
                }
                // summarize adjacent up and left
                else {
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
                }
            }
        }
        System.out.println(matrix[matrix.length-1][matrix.length-1]);
        return matrix[matrix.length-1][matrix.length-1];
    }
}

//class Point{
//    public Point(int a, int b) {
//        this.a = a;
//        this.b = b;
//    }
//    int a;
//    int b;
//}