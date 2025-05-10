package kg.euler;

public class Day0024 {

    public static void main(String[] args) {
        solution("0123456789", 1000000);
    }

    // What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
    static String solution(String permutatingStr, int limit) {

        StringBuilder sb = new StringBuilder(permutatingStr);

        int maxIdx = -1;
        char val = 0;
        int permCnt = 1;

        while (permCnt < limit) {
            int idx = -1;
            for(int i = permutatingStr.length() - 1; i > 0; i--) {
                if(sb.charAt(i) > sb.charAt(i-1) && idx == -1) {
                    idx = i-1;
                    val = sb.charAt(i-1);
                }
            }

            if(idx == -1) {
                break;
            }

            for(int i = permutatingStr.length() - 1; i >= idx; i--) {
                if(val < sb.charAt(i)) {
                    maxIdx = i;
                    break;
                }
            }

            StringBuilder temp = new StringBuilder(sb);
            temp.setCharAt(idx, sb.charAt(maxIdx));
            temp.setCharAt(maxIdx, sb.charAt(idx));
            String substringEnd = temp.substring(idx+1, temp.length());
            StringBuilder sb2 = new StringBuilder(substringEnd).reverse();
            String substringBeg = temp.substring(0, idx+1);
            permCnt++;

            sb = new StringBuilder(substringBeg + sb2);
        }
        
        System.out.println(sb);
        return sb.toString();
    }
}