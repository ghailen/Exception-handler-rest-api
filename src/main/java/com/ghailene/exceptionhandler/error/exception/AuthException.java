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
public class AuthException extends CRMException {

    @Getter @Setter
    private ApiErrorResponse response;
    @Getter @Setter
    private HttpStatus status = HttpStatus.UNAUTHORIZED;

    public AuthException(ApiErrorResponse response) {
        super(response.getLabel());
        this.response = response;
    }

    public AuthException(String message, String detail) {
        super(message);
        this.response = new ApiErrorResponse("ERR_AUTHENT", message, detail);
    }

    public AuthException(String message) {
        this(message, message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
        this.response = new ApiErrorResponse("ERR_AUTHENT", message, message);
    }

    public AuthException(Throwable cause) {
        this(cause.getLocalizedMessage(), cause);
    }

    public AuthException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

    public AuthException(String message, String detail, HttpStatus status) {
        this(message, detail);
        this.status = status;
    }

}
