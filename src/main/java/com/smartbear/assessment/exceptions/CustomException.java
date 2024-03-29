package com.smartbear.assessment.exceptions;

import lombok.Getter;

/**
 * Class to give out custom exceptions with tailored http statuses and error messages
 */
@Getter
public class CustomException extends Exception{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
