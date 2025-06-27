package kg.euler;

public class Day0043 {

    static int[] divisors = {2, 3, 5, 7, 11, 13, 17};

    public static void main(String[] args) {
        solution("0123456789");
    }

    //Find the sum of all 0 to 9 pandigital numbers with this property.
    static long solution(String text) {

        String permVal = new String(text);
        long result = 0;

        while (permVal != null) {
            boolean isNotDivisable = true;
            int startIdx = 1;
            long value = Long.parseLong(permVal);
            for (int i = 0; i < divisors.length; i++) {
                if (startIdx >= 8) {
                    break;
                }
                String substring = permVal.substring(startIdx, startIdx + 3);
                if (substring.length() < 3) {
                    break;
                }
                if (Long.parseLong(substring) % divisors[i] != 0) {
                    isNotDivisable = false;
                    break;
                }
                startIdx++;
            }

            if (isNotDivisable) {
                System.out.println(value);
                result += value;
            }

            permVal = permutate(permVal);
        }
        System.out.println("result is " + result);
        return result;
    }

    static String permutate(String permutatingStr) {
        StringBuilder sb = new StringBuilder(permutatingStr);
        int maxIdx = -1;
        char val = 0;

        int idx = -1;
        for (int i = permutatingStr.length() - 1; i > 0; i--) {
            if (sb.charAt(i) > sb.charAt(i - 1) && idx == -1) {
                idx = i - 1;
                val = sb.charAt(i - 1);
            }
        }

        if (idx == -1) {
            return null;
        }

        for (int i = permutatingStr.length() - 1; i >= idx; i--) {
            if (val < sb.charAt(i)) {
                maxIdx = i;
                break;
            }
        }

        StringBuilder temp = new StringBuilder(sb);
        temp.setCharAt(idx, sb.charAt(maxIdx));
        temp.setCharAt(maxIdx, sb.charAt(idx));
        String substringEnd = temp.substring(idx + 1, temp.length());
        StringBuilder sb2 = new StringBuilder(substringEnd).reverse();
        String substringBeg = temp.substring(0, idx + 1);

        sb = new StringBuilder(substringBeg + sb2);
        return sb.toString();

    }
}
