package cftimesheet.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ValidationRouter implements Validator {

    private List<ICandyFactoryValidator> allValidators;

    public ValidationRouter(List<ICandyFactoryValidator> allValidators) {
        this.allValidators = allValidators;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return allValidators.stream().anyMatch(validator -> validator.supports(clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        allValidators.stream().filter(validator -> validator.supports(target.getClass()))
                .forEach(validator -> validator.validate(target, errors));
    }

}