package abstraction;
// The process of hiding certain details and showing only essential information
abstract class Car{
    abstract public void show();
    abstract public void price();
}
class Mercedes extends Car {
    @Override
    public void show() {
        System.out.println("The GWagon");
    }
    @Override
    public void price(){
        System.out.println("3000000000");
    }
    public void door(int i ){
        System.out.println(i);
    }
}
class Maruti extends  Car{
    @Override
    public void show() {
        System.out.println("Swift");
    }
    @Override
    public void price(){
        System.out.println("350000");
    }
    public void door(int i){
        System.out.println(i);
    }
}
public class Abstration {
    public static void main(String[] args) {
        Mercedes car1 = new Mercedes();
        car1.show();
        car1.price();
        car1.door(5);
        Maruti car2 = new Maruti();
        car2.show();
        car2.price();
        car2.door(4);


    }
}
