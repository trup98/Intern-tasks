package encapsulation;
class student{
    public static void main(String[] args) {
        Encapsulation en = new Encapsulation();
        en.setName("Vai");
        System.out.println(en.getName());
    }
}
public class Encapsulation {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
