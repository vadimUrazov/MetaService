package net.thumbtack.traincompany.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceException extends Exception {
    private String message;

    public ServiceException(ErrorCode errorCode) {
        this.message = "field:  " + errorCode.getField() + " ,cause:  " + errorCode.getMessage();

    }
}
