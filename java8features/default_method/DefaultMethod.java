package default_method;

interface abc {
    default void dmethod() {
        System.out.println("Default method");
    }

    void amethod(String msg);
}

public class DefaultMethod implements abc {
    @Override
    public void amethod(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        DefaultMethod dm = new DefaultMethod();
        dm.dmethod();
        dm.amethod("cij");


    }
}
