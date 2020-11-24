package cftimesheet.managers;

import cftimesheet.daos.EmployeeDao;
import cftimesheet.models.NewEmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeManager {

    private final Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

    EmployeeDao employeeDao;

    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void createNewEmployee(NewEmployeeRequest newEmployeeRequest) {
        logger.info("Creating new Employee: {}", newEmployeeRequest.getEmployeeName());
        employeeDao.createNewEmployee(newEmployeeRequest);
    }

}