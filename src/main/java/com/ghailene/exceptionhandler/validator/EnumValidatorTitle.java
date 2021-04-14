package com.ghailene.exceptionhandler.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidatorTitle implements ConstraintValidator<EnumValidateTitle, String> {

    List<String> titleList = Stream.of("MR", "MS").collect(Collectors.toList());

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            return titleList.contains(s.toUpperCase());

        } else {
            return false;
        }
    }

}
