package inheritance;
class First{
    void firstshow(){
        System.out.println("First Class");
    }
}
class Secound extends First{
    void secoundShow(){
        System.out.println("Secound Class");
    }
}
class Third extends Secound{
    void show(){
        System.out.println("Third Class");

    }
}
public class MultilevelInheritance {
    public static void main(String[] args) {
        Third t = new Third();
        t.show();
        t.secoundShow();
        t.firstshow();
    }
}
