package cftimesheet.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface ICandyFactoryValidator extends Validator {

    @Override
    boolean supports(Class<?> clazz);

    @Override
    void validate(Object target, Errors errors);

}