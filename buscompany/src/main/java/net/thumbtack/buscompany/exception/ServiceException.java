package net.thumbtack.buscompany.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@ToString
public class ServiceException extends Exception {

    private String message;

    public ServiceException(ErrorCode errorCode) {
        this.message = "field:  " + errorCode.getField() + " ,cause:  " + errorCode.getMessage();

    }
}
