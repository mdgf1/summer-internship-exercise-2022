package com.premiumminds.internship.screenlocking.exceptions;

public class InvalidFirst extends Exception {

    /**
     * @param value invalid value of first node
     */
    public InvalidFirst(int value) {
        super(Integer.toString(value));
    }
}