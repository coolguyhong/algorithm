package test.template;

public class Main {

    public static void main(String[] args) {
        Worker designer = new Designer();
        designer.work();

        Worker game = new Game();
        game.work();
    }
}
