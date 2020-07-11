package org.jason.util.exception;

@SuppressWarnings("serial")
public class PageNotFoundException extends Exception {
    public PageNotFoundException(Exception e) {
        super(e);
    }

    public PageNotFoundException(String message) {
        super(message);
    }

    public PageNotFoundException() {
        super("Not Page Found");
    }
}
