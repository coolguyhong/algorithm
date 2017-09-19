package test.singleton;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.Amethod();
        main.Bmethod();
    }

    private void Amethod() {
        Singleton sc = Singleton.getInstance();
        System.out.println("Amethod 에서 호출한 값 : " + sc.getNextInt());
    }

    private void Bmethod() {
        Singleton sc = Singleton.getInstance();
        System.out.println("Bmethod 에서 호출한 값 : " + sc.getNextInt());
    }
}
