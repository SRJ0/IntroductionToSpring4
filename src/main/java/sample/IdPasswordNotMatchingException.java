package sample;

public class IdPasswordNotMatchingException extends RuntimeException {

    IdPasswordNotMatchingException(String msg) {
        super(msg);
    }
}
