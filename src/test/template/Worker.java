package test.template;

public abstract class Worker {
    protected abstract void doit();

    public final void work(){
        System.out.println("출근");
        doit();
        System.out.println("퇴근");
    }
}
