package com.starriverdata.common.exception;

public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException(){
        super();
    }

    public UnsupportedTypeException(String message){
        super(message);
    }

    public UnsupportedTypeException(Throwable e){
        super(e);
    }

    public UnsupportedTypeException(String msg, Throwable e){
        super(msg, e);
    }
}
