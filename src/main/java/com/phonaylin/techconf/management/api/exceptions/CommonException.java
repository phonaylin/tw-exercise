package com.phonaylin.techconf.management.api.exceptions;

import java.util.Locale;

/**
 * Common exception superclass 
 */
public class CommonException extends RuntimeException {

    private static final String UNKNOWN_EXCEPTION_CODE = "exception-unknown";
    
    private static final String UNKNOWN_EXCEPTION_MESSAGE = "Common exception";
    
    protected static final String SPACE = " ";
    
    private static final long serialVersionUID = 1L;
    
    private String code = UNKNOWN_EXCEPTION_CODE;
    
    private String message = UNKNOWN_EXCEPTION_MESSAGE;
    
    private Locale messageLocale = new Locale("en", "US");
    
    public CommonException() {
    }
    
    public CommonException(final Throwable e) {
        setMessage(e.getMessage());
        setCode(getDefaultCode());
    }

    public CommonException(final String message) {
        setMessage(message);
        setCode(getDefaultCode());
    }
    
    /**
     * Sets the code and message, locale would default to US
     * @param code
     * @param message
     */
    public CommonException(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
    
    public CommonException(final String code, final String message, final Locale messageLocale) {
        this.code = code;
        this.message = message;
        this.messageLocale = messageLocale;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Locale getMessageLocale() {
        return messageLocale;
    }

    public void setMessageLocale(Locale messageLocale) {
        this.messageLocale = messageLocale;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getDefaultCode() {
        return UNKNOWN_EXCEPTION_CODE;
    }
}
