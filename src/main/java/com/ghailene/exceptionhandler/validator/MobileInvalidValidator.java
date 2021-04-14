package com.ghailene.exceptionhandler.validator;

import com.ghailene.exceptionhandler.models.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileInvalidValidator implements ConstraintValidator<MobileValidate, Customer> {

    @Override
    public boolean isValid(Customer customerData, ConstraintValidatorContext constraintValidatorContext) {
        if (customerData.getContact().getMobile() == null || customerData.getContact().getMobile().isEmpty() || customerData.getContact().getMobile().length() > 8 || customerData.getContact().getMobile().length() < 1) {
            return false;
        } else
            return true;
    }
}
