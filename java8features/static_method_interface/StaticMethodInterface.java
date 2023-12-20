package static_method_interface;
interface abc{
    default void dMethod(){
        System.out.println("Default Method");
    }
    static void sMethod(){
        System.out.println("Static Method");
    }
    void aMethod(String stringr);
}
public class StaticMethodInterface implements abc {
    @Override
    public void aMethod(String str){
        System.out.println(str);
    }

    public static void main(String[] args) {
//      Creating object
        StaticMethodInterface smi = new StaticMethodInterface();
//      Calling abstract method
        smi.aMethod("fb");
//      Calling default method
        smi.dMethod();
//      Calling static method
        abc.sMethod();
    }
}
