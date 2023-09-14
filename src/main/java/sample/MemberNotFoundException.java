package sample;

public class MemberNotFoundException extends RuntimeException {
    MemberNotFoundException(String msg) {
        super(msg);
    }
}
