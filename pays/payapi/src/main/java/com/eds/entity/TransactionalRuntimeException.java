package com.eds.entity;

public class TransactionalRuntimeException extends RuntimeException {
	public TransactionalRuntimeException(String msg) {
		super(msg);
	}

	public TransactionalRuntimeException() {
		super();
	}
}
