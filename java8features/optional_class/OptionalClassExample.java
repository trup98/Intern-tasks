package optional_class;

import java.util.Optional;

public class OptionalClassExample {
    public static Optional<String> gwtInfo(){
        String nm="cd";
        return Optional.ofNullable(nm);
    }
    public static void main(String[] args) {
        String name=null;
        Optional<String> n = Optional.ofNullable(name);

//      System.out.println(n.get());
        System.out.println(n.isPresent());
        Optional<String> optional1= gwtInfo();
        System.out.println(optional1.orElse("Not Found"));

    }
}
