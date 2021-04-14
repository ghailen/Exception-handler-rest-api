package com.ghailene.exceptionhandler.utils;

import com.ghailene.exceptionhandler.error.exception.AuthException;
import com.ghailene.exceptionhandler.error.exception.BusinessException;
import com.ghailene.exceptionhandler.error.exception.TechnicalException;
import com.ghailene.exceptionhandler.error.responsedto.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by m.berriri on 03/12/2020.
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    //== Validation errors ==
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    List<ApiErrorResponse> onConstraintValidationException(ConstraintViolationException e) {
        List<ApiErrorResponse> errors =
            e.getConstraintViolations().stream().map(
                violation -> new ApiErrorResponse(
                    "ERR_VALIDATION",
                    violation.toString(),
                    violation.getMessage()
                )
            ).collect(Collectors.toList());
        return errors;
    }

    //== Validation errors ==
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    List<ApiErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ApiErrorResponse> errors =
            e.getBindingResult().getAllErrors().stream().map(
                objectError -> new ApiErrorResponse(
                    "ERR_VALIDATION",
                    objectError instanceof FieldError ? ((FieldError)objectError).getField() : "",
                    objectError.getDefaultMessage()
                )
            ).collect(Collectors.toList());
        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        String id = "ERR_VALIDATION";
        ApiErrorResponse body = new ApiErrorResponse(
                id,
                "Erreur de validation globale",
                "Erreur lors du parsing de requete"
        );
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
        return response;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponse body = ex.getResponse();
        String id = String.format("ERR_%s", getAPIName(request));
        if (ex.getResponse().getId() != null && !ex.getResponse().getId().equals(""))
            id = ex.getResponse().getId();
        body.setId(id);
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(ex.getStatus()).body(body);
        return response;
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ApiErrorResponse> handleTechnicalException(
            TechnicalException ex,
            HttpServletRequest request
    ) {
        String id = String.format("ERR_%s", getAPIName(request));
        ApiErrorResponse body = new ApiErrorResponse(
                id,
                "Erreur technique",
                ex.getLocalizedMessage()
        );
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        return response;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request
    ) {
        String id = "ERR_APPLICATION";
        ApiErrorResponse body = new ApiErrorResponse(
                id,
                "Erreur interne du serveur: " + ex.getClass(),
                ex.getLocalizedMessage()
        );
        ResponseEntity<ApiErrorResponse> response =
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        return response;
    }



    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthException(
            AuthException ex,
            HttpServletRequest request
    ) { return ResponseEntity.status(ex.getStatus()).body(ex.getResponse()); }

    //== private utils methods ==
    private String getAPIName(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String apiName = requestURI;
        if (requestURI.contains("emergencyContacts")) {
            apiName = "EMER";
        } else if (requestURI.contains("orders")) {
            apiName = "ORD";
        } else if (requestURI.contains("invoices")) {
            apiName = "INV";
        } else if (requestURI.contains("contracts")) {
            apiName = "CONT";
        } else if (requestURI.contains("customers")) {
            apiName = "CUSTO";
        } else if (requestURI.contains("subscription")) {
            apiName = "SUB";
        } else if (requestURI.contains("gateway")) {
            apiName = "GTWY";
        }
        return apiName;
    }

}
