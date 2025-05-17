package raut.shubham.electroroute.error;

import java.io.IOException;

public class EmailAlreadyUseException extends IOException {

    public EmailAlreadyUseException(String message){
        super(message);
    }

}