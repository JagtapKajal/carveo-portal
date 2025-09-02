package carveo_portal.carveoManagement.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> generalException(Exception exception, WebRequest request) {
        String path = request.getDescription(false);

        // Handle exceptions for Swagger-related endpoints differently
        if (path.contains("/v3/api-docs") || path.contains("/swagger")) {
            Map<String, Object> response = new HashMap<>();
            response.put("time", LocalDateTime.now().toString());
            response.put("message", "Failed to load API documentation: " + exception.getMessage());
            response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Handle other exceptions
        Map<String, Object> response = new HashMap<>();
        response.put("time", LocalDateTime.now().toString());
        response.put("message", exception.getMessage());
        response.put("error", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> InvalidRegistrationHandling(InvalidRegistrationNumberException exception){
       return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> VehicleNotFound(InvalidRegistrationNumberException.VehicleNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
