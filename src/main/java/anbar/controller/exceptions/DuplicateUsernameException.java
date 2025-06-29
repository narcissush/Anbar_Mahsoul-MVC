package anbar.controller.exceptions;

public class DuplicateUsernameException extends Exception {
    public DuplicateUsernameException() {
        super("Username already exists !!!");
    }
}
