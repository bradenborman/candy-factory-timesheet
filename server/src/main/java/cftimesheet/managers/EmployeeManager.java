package cftimesheet.managers;

import cftimesheet.models.NewEmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeManager {

    private final Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

    public void createNewEmployee(NewEmployeeRequest newEmployeeRequest) {
        logger.info("Creating new Employee: {}", newEmployeeRequest.getEmployeeName());
    }

}