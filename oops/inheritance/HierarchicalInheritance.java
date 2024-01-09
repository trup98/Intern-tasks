package inheritance;
class A{
    public void methodA(){
        System.out.println("Method Of Class A");
    }
}
class B extends A{

    void methodB(){
        System.out.println("Method of Class B");
    }
}
class C extends A{
    void methodC(){
        System.out.println("Method of Class C");
    }
}
public class HierarchicalInheritance {
    public static void main(String[] args) {
        A objA = new A();
        B objB = new B();
        C objC = new C();
//      All object can access Class A method
        objA.methodA();
        objB.methodA();
        objC.methodA();
    }
}
