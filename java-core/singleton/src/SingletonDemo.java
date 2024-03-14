class SingletonDemoExample {
    // Creating Object of singleObject
    private static SingletonDemoExample singletonDemoExample;

    // Making this constructor private so class can not be instantiated
    private SingletonDemoExample() {

    }

    //  Making method public static that return single instance of the class
    public static SingletonDemoExample getInstance() {
        if (singletonDemoExample == null) {
            singletonDemoExample = new SingletonDemoExample();
        }
        return singletonDemoExample;
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        //  Creating Object of the class
        SingletonDemoExample example = SingletonDemoExample.getInstance();
        System.out.println(example.hashCode());
        //  Creating another Object of the same class
        SingletonDemoExample example1 = SingletonDemoExample.getInstance();
        System.out.println(example1.hashCode());
    }
}
