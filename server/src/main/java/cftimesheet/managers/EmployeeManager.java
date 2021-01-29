package cftimesheet.managers;

import cftimesheet.daos.EmployeeDao;
import cftimesheet.models.Employee;
import cftimesheet.models.NewEmployeeRequest;
import cftimesheet.models.UpdateEmployeeRequest;
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
        logger.info("Creating new Employee: {}", newEmployeeRequest.getFirstName());
        employeeDao.createNewEmployee(newEmployeeRequest);
    }

    public Employee getEmployeeData(String employeeId) {
       return employeeDao.getEmployeeData(employeeId);
    }

    public void updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) {
        logger.info("Updating Employee: {}", updateEmployeeRequest.getFirstName());
        employeeDao.updateEmployee(updateEmployeeRequest);
    }

    public void deleteUser(String personId) {
        logger.info("Deleting Employee Id: {}", personId);
        employeeDao.deleteShiftsAssociatedToEmployee(personId);
        employeeDao.deleteEmployee(personId);
        logger.info("Employee Removed");
    }
}