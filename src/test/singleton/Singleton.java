package test.singleton;

public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }

    private int cnt = 0;

    public int getNextInt() {
        return ++cnt;
    }
}
