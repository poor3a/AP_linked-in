package exceptions;

public class UserDAOException extends Exception {
    // UserDAOException is a custom exception class that is responsible for handling exceptions that occur in the UserDAO class
    public UserDAOException(String message) {
        super(message);
    }
}