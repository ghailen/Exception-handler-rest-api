package com.ghailene.exceptionhandler;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@SpringBootApplication
public class ExceptionHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionHandlerApplication.class, args);

	}
/*	@Autowired
	AutowireCapableBeanFactory autowireCapableBeanFactory;
	@Bean
	public Validator validator () {

		ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )

				.configure().constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
				.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		return validator;
	}*/

}
