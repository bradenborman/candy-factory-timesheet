package cftimesheet.managers;

import cftimesheet.daos.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShiftManager {

    private final Logger logger = LoggerFactory.getLogger(ShiftManager.class);

    EmployeeDao employeeDao;

    public ShiftManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void startNewShiftByEmployeeId(int empId, String startTime) {
        logger.info("Starting new shift for: {} at {}", empId, startTime);
        employeeDao.startShift(empId, startTime);
    }

}