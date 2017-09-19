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
//        System.out.println(fibonacci(5));
//        int n = 20;
//        for (int i = 0; i < n; n--) {
//            System.out.print("* ");
//        }
        new Main(null);
    }

//    private static int fibonacci(int n) {
//        if (n <= 1) {
//            return n;
//        }
//        return fibonacci(n-1) + fibonacci(n-2);
//    }
}
