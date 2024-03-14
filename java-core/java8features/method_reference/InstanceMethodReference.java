package method_reference;

interface Sayable {
    void say();
}

public class InstanceMethodReference {
    public void saySomething() {
        System.out.println("cnun");
    }

    public static void main(String[] args) {
//      Creating object
        InstanceMethodReference imr = new InstanceMethodReference();
//      Referring non-static method by reference
        Sayable sayable = imr::saySomething;
//      Calling interface method
        sayable.say();
//      Referring non-static method with anonymous object
        Sayable sayable1 = new InstanceMethodReference()::saySomething;
//      Calling interface method
        sayable1.say();
    }
}
