package cftimesheet.validators;

import cftimesheet.models.ChangeShiftRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.thymeleaf.util.StringUtils;

@Component
public class ChangeShiftRequestValidator implements ICandyFactoryValidator {

    private Logger logger = LoggerFactory.getLogger(ChangeShiftRequestValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangeShiftRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangeShiftRequest request = (ChangeShiftRequest) target;

        if (StringUtils.isEmpty(request.getClockTime()))
            errors.rejectValue("clockTime", "INVALID_CLOCK_TIME", "Clock Time cannot be null or empty");

        if (request.getPersonId() == 0)
            errors.rejectValue("personId", "INVALID_PERSON_ID", "Person Id cannot be null or empty");

        if (!errors.hasErrors())
            logger.info("Validation Passed for: ChangeShiftRequest");
    }

}