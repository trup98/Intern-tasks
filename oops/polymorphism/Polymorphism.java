package polymorphism;
// The Task can perform single action in different ways

class Shape{
    public void show(){
        System.out.println("calling shape class");
    }
}

class Triangle extends Shape{
    public void show()
    {
        System.out.println("calling from Triangle class");
    }
}

class Cricle extends  Shape{
    public void show(){
        System.out.println("Callling from Circle Class");
    }
}

class Square extends Shape{
    public void show() {
        System.out.println("Calling from Square Class");
    }
}


public class Polymorphism {
    public static void main(String[] args) {
//      co
        Shape shapeShow = new Shape();
        shapeShow.show();
        Shape triangleShow = new Triangle();
        triangleShow.show();
        Shape CircleShow = new Cricle();
        CircleShow.show();
        Shape SquareShow = new Square();
        SquareShow.show();

    }
}
