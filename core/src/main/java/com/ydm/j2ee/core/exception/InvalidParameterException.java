package com.ydm.j2ee.core.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(String message){
        super(message);
    }
}
