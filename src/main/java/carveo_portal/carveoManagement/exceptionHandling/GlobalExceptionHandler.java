package carveo_portal.carveoManagement.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // Exception to handle invalid registration number
    @ExceptionHandler(InvalidRegistrationNumberException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(InvalidRegistrationNumberException ex) {
        return new ResponseEntity<>(Map.of("error: Invalid Registration Number..", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    //Handle Validation errors of Visitor
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<String> InvalidRegistrationHandling(InvalidRegistrationNumberException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    public ResponseEntity<String> VehicleNotFound(InvalidRegistrationNumberException.VehicleNotFoundException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
