package com.uncledemy.the_thinking_space.exception;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(String message){
        super(message);
    }
}
