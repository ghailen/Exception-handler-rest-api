package com.ghailene.exceptionhandler.controllers;

import com.ghailene.exceptionhandler.error.exception.BusinessException;
import com.ghailene.exceptionhandler.models.Customer;
import com.ghailene.exceptionhandler.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer in) throws BusinessException {
        Customer res =  customerService.addCustomerData(in);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
    }

}
