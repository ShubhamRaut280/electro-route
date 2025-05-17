package raut.shubham.electroroute.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = EmailAlreadyUseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage emailAlreadyInUseException(EmailAlreadyUseException exception){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),new Date(),exception.getMessage(),"The email address you provided is already associated with an existing account. Please choose a different email address or log in to your existing account.");
    }

}