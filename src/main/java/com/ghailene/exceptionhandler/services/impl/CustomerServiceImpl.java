package com.ghailene.exceptionhandler.services.impl;

import com.ghailene.exceptionhandler.error.exception.BusinessException;
import com.ghailene.exceptionhandler.models.Customer;
import com.ghailene.exceptionhandler.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer addCustomerData(Customer in) throws BusinessException {
        if (in.getContact().getMobile().equalsIgnoreCase("0000")){
            throw new BusinessException("mobile invdalid","mobile invdalid", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return in;
    }
}
