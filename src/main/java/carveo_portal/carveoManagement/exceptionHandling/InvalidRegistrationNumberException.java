package carveo_portal.carveoManagement.exceptionHandling;

public class InvalidRegistrationNumberException extends RuntimeException {

    public InvalidRegistrationNumberException(String message){
        super(message);
    }
}
