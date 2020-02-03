package com.rapiCredit.test.exceptions;

public class InvalidClientException extends ControlledException {

    private static final long serialVersionUID = 979995196153303709L;

    public InvalidClientException() {
        super();
    }

    public InvalidClientException(String message) {
        super(message);
    }

}
