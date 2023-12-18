package inheritance;
class Animal{
    void eat(){
        System.out.println("Eatting");
    }
}
class Dog extends Animal{
    void bark(){
        System.out.println("Barkking");
    }
}
public class SingleInheritance {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.bark();
        d.eat();
    }
}
