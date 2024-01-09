package CustomException;

public class CustomExceptionExample {
    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("You can't drive!");
        } else {
            System.out.println("You can drive");
        }
    }

    public static void main(String[] args) {
        try {
            checkAge(1);
        } catch (InvalidAgeException e) {
            System.out.println("Exception come");
            System.out.println("Exception" + e);
        }
        System.out.println("After code");
    }
}
