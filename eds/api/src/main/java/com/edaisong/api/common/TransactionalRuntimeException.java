package com.edaisong.api.common;

public class TransactionalRuntimeException extends RuntimeException{
public TransactionalRuntimeException(String msg) {
	super(msg);
}
public TransactionalRuntimeException() {
	super();
}
}
