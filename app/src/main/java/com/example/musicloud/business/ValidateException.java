package com.example.musicloud.business;

public class ValidateException extends Exception{
    /**
     * ValidateException
     * @param message: The message that want to Show.
     * */
    public ValidateException(String message)
    {
        super(message);
    }
}
