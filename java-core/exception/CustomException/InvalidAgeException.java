package CustomException;

public class InvalidAgeException extends Exception {
    InvalidAgeException(String string){
        super(string);
    }
}
