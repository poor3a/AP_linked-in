package exceptions;

public class ProfileDAOException extends Exception{
    // ProfileDAOException is a custom exception class that is responsible for handling exceptions that occur in the ProfileDAO class
    public ProfileDAOException(String message) {
        super(message);
    }
}
