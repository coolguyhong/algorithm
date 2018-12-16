package test.interview;

public class Main {
    public Main(Object o) {
        System.out.println("object");
    }

    public Main(double[] args) {
        System.out.println("double");
    }

    public Main(int i) {
        System.out.println("int");
    }

    public static void main(String[] args) {
//        System.out.println(termsum.baekjoon.fibonacci(5));
//        int n = 20;
//        for (int i = 0; i < n; n--) {
//            System.out.print("* ");
//        }
        new Main(null);
    }

//    private static int termsum.baekjoon.fibonacci(int n) {
//        if (n <= 1) {
//            return n;
//        }
//        return termsum.baekjoon.fibonacci(n-1) + termsum.baekjoon.fibonacci(n-2);
//    }
}
