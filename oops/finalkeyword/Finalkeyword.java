package finalkeyword;

public class Finalkeyword {
//  Making Final Variable
    final int speedLimit= 45;
    void changeSpeedLimit(){
//      Changing The Value Of Final Variable
//      speedLimit=55;
        System.out.println(speedLimit);
    }

    public static void main(String[] args) {
//      Creating Class Object
        Finalkeyword fk = new Finalkeyword();
        fk.changeSpeedLimit();
    }
}
