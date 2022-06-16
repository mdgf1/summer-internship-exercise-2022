package com.premiumminds.internship.screenlocking.exceptions;

public class InvalidLength extends Exception {

    /**
     * @param length invalid length
     */
    public InvalidLength(int length) {
        super(Integer.toString(length));
    }
}