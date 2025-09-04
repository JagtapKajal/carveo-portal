package carveo_portal.carveoManagement.exceptionHandling;

public class InvalidRegistrationNumberException extends RuntimeException {

    // method for custom invalid registration number
    public InvalidRegistrationNumberException(String message){
        super(message);
    }

    // method for custom vehicle not found exception
    public static class VehicleNotFoundException extends  RuntimeException{

        public VehicleNotFoundException(String message){
            super(message);
        }
    }
}

