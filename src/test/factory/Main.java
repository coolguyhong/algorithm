package test.factory;

public class Main {
    public static void main(String[] args) {
        Animal a1 = AnimalFactory.create("cow");
        a1.printDescription();

        Animal a2 = AnimalFactory.create("dog");
        a2.printDescription();

        Animal a3 = AnimalFactory.create("cat");
        a3.printDescription();
    }
}
