package statickeyword;

class Student{
    int rollNo;
    String name;
//  Making Static Variable
    static String collegeName="Gls";

//  Creating Constructor
    Student(int i,String s){
        this.rollNo=i;
        this.name=s;
    }
    void display(){
        System.out.println("RollNo: "+rollNo+" Name:"+name+" CollegeName:"+collegeName);
    }
}
public class StaticKeyword {
    public static void main(String[] args) {
//      Creating Student Object
        Student s1 = new Student(12,"shb");
//      Calling Display Method
        s1.display();
//      Changing Static Variable Value
        Student.collegeName="Gu";
        s1.display();
    }
}
