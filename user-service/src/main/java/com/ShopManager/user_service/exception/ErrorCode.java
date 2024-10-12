package com.ShopManager.user_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    CIC_USED(1002, "CIC has been used", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1004, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1005, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    INVALID_START_DATE(1006, "Start date must not be after the current date. ", HttpStatus.BAD_REQUEST),
    POSITION_NOT_FOUND(1007,"Position not found", HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
