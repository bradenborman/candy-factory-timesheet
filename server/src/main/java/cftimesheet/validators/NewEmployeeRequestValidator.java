package cftimesheet.validators;

import cftimesheet.models.NewEmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class NewEmployeeRequestValidator implements ICandyFactoryValidator {

    private Logger logger = LoggerFactory.getLogger(NewEmployeeRequestValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return NewEmployeeRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewEmployeeRequest request = (NewEmployeeRequest) target;

        if (!errors.hasErrors())
            logger.info("Validation Passed for: NewEmployeeRequest.");
    }

}