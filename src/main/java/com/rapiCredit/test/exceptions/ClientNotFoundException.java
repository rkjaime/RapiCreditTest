package com.rapiCredit.test.exceptions;



public class ClientNotFoundException extends ControlledException {

    private static final long serialVersionUID = 4351108743874380836L;

    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

}
