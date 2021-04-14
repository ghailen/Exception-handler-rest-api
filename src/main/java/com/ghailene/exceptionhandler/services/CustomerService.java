package com.ghailene.exceptionhandler.services;

import com.ghailene.exceptionhandler.error.exception.BusinessException;
import com.ghailene.exceptionhandler.models.Customer;

public interface CustomerService {

    Customer addCustomerData(Customer in) throws BusinessException;
}
