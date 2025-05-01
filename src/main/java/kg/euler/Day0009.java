package kg.euler;

public class Day0009 {

    public static void main(String[] args) {

        solution(1000);
    }

    //There exists exactly one Pythagorean triplet for which
    // a + b + c = 1000. Find the product abc
    static long solution(int aimToFind) {

       for(int a = 1; a < 1000; a++) {
           for (int b = 1; b < 1000; b++) {
               if(a==b) {
                   break;
               }
               int quadrSum = (a * a) + (b * b);
               int root = root(quadrSum);
               if( root != -1) {
                   if(a + b + root == aimToFind) {
                       System.out.printf("a = %d, b = %d, c = %d \n", a, b, root);
                       System.out.printf("result is %d", (long) a*b*root);
                       return (long) a*b*root;
                   }
               }
           }
       }
       return 0L;
    }

    static int root(int num) {
        for(int i = 1; i < num/2 + 1; i++) {
            if (i*i == num) {
                return i;
            }
        }
        return -1;
    }
}
