package com.ghailene.exceptionhandler.error.exception;

import com.ghailene.exceptionhandler.error.responsedto.ApiErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by m.berriri on 03/12/2020.
 */
@AllArgsConstructor
public class BusinessException extends CRMException {

    @Getter
    @Setter
    private ApiErrorResponse response;
    @Getter
    @Setter
    private HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

    public BusinessException(ApiErrorResponse response) {
        super(response.getLabel());
        this.response = response;
    }

    public BusinessException(String message, String detail) {
        super(message);
        this.response = new ApiErrorResponse("", message, detail);
    }

    public BusinessException(String message) {
        this(message, message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.response = new ApiErrorResponse("", message, message);
    }

    public BusinessException(Throwable cause) {
        this(cause.getLocalizedMessage(), cause);
    }

    public BusinessException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

    public BusinessException(String message, String detail, HttpStatus status) {
        this(message, detail);
        this.status = status;
    }

}
