package com.starriverdata.common.exception;


public class HostGroupNotExistsException extends Exception {

    public HostGroupNotExistsException(){
        super();
    }

    public HostGroupNotExistsException(String message){
        super(message);
    }

    public HostGroupNotExistsException(Throwable e){
        super(e);
    }

    public HostGroupNotExistsException(String msg, Throwable e){
        super(msg, e);
    }
}
