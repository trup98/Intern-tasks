package constuctor;

class Constructor {
    int id;
    String name;
//    Default Constructor
    Constructor(){
        System.out.println("Calling from Const");
    }
//    Parameter Constructor
    Constructor(int i,String s){
        this.id=i;
        this.name=s;
    }
//    Method to display the parameter constructor
    void display(){
        System.out.println(id+" "+name);
    }

    public static void main(String[] args) {
//      Creating Default Constructor
        Constructor con = new Constructor();
//      Creating Parameter Constructor
        Constructor con2 = new Constructor(45,"snd");
//      Calling Display Method
        con2.display();
    }
}
