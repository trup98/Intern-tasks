package inheritance;
interface AcceptOffer{
    void accept();
}
interface DeclineOffer{
    void decline();
}
class Employee implements AcceptOffer,DeclineOffer{
    @Override
    public void accept() {
        System.out.println("Employee Can Accept The Offer");
    }

    @Override
    public void decline() {
        System.out.println("Employee Can Decline The Offer");
    }
}
public class MultipleInheritance {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.accept();
        emp.decline();
    }
}
