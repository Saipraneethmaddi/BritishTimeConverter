package com.smartbear.assessment.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum to map http statuses and error messages in all the error scenarios
 */
@Getter
public enum ErrorCode {
    INCORRECT_TIME_STRING_LENGTH("Time string cannot be more than 5 characters", HttpStatus.BAD_REQUEST),
    INCORRECT_TIME_FORMAT("Time should follow 'hh:mm' format", HttpStatus.BAD_REQUEST),
    INVALID_HOURS_MINUTES("Hours and minutes should be whole numbers", HttpStatus.BAD_REQUEST),
    HOURS_MINUTES_OUT_OF_RANGE("Hours should be between 0 and 12. Minutes should be between 0 and 59", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
