public class InvalidUserAccountException extends Exception {
    public InvalidUserAccountException(String message) {
        super(message);
    }

    public InvalidUserAccountException() {
        super();
    }
}
