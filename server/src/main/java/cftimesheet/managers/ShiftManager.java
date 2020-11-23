package cftimesheet.managers;

import cftimesheet.daos.EmployeeDao;
import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.models.ShiftAction;
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

    public void newShiftAction(ChangeShiftRequest request) {
        logger.info("Doing shiftAction of {} for: {} at {}. {} was the shift Id",
                request.getShiftAction().name(), request.getPersonId(), request.getClockTime(), request.getShiftId());

        if (ShiftAction.START == request.getShiftAction())
            employeeDao.startShift(request);

        else if (ShiftAction.END == request.getShiftAction())
            employeeDao.endShift(request);

    }

}