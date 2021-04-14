package com.ghailene.exceptionhandler.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD,ElementType.LOCAL_VARIABLE,ElementType.METHOD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidatorTitle.class)
public @interface EnumValidateTitle {

    /**
     * Message string.
     *
     * @return the string
     */
    String message() default "INVALID";

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    Class<?>[] groups() default { };

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends Payload>[] payload() default { };
}
