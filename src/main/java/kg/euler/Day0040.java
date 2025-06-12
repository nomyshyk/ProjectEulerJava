package kg.euler;

public class Day0040 {

    static int[] array = new int[1000000];
    static int[] neededIndexes= {1, 10, 100, 1000, 10000, 100000, 1000000};

    public static void main(String[] args) {
        solution(1000000);
    }

    // If dn represents the n-th digit of the fractional part, find the value of the following expression.
    // d1*d10*d100*d1000**d10000*d100000*d1000000
    static long solution(int total) {
        orderNum(total);
        long resProduct = 1L;
        for (int v : neededIndexes) {
            resProduct *= array[v-1];
            System.out.println(array[v-1]);
        }
        System.out.println("result is " + resProduct);

        return resProduct;
    }

    static void orderNum(int num) {
        int i = 0;
        int iter = 0;
        outer:
        while(i <= array.length) {
            String numStr = String.valueOf(i+1);
            for (int j=0; j < numStr.length(); j++) {
                array[iter] = Integer.parseInt(numStr.substring(j,j+1));
                iter++;
                if(iter >= num) {
                    break outer;
                }
            }
            i++;
        }
    }
}
