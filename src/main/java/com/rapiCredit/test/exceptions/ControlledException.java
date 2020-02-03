package com.rapiCredit.test.exceptions;

public abstract class ControlledException extends Exception {

    private static final long serialVersionUID = 5310598403158374052L;

    public ControlledException() {
        super();
    }

    public ControlledException(String message) {
        super(message);
    }
}
