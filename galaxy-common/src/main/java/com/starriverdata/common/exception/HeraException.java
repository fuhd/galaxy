package com.starriverdata.common.exception;

public class HeraException extends Exception {

    public HeraException(){
        super();
    }

    public HeraException(String message){
        super(message);
    }

    public HeraException(Throwable e){
        super(e);
    }

    public HeraException(String msg,Throwable e){
        super(msg, e);
    }
}
