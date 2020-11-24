package cftimesheet.services;

import cftimesheet.daos.EmployeeDao;
import cftimesheet.models.Employee;
import cftimesheet.models.ShiftDetails;
import cftimesheet.utilities.ShiftDetailsHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DataRetrievalService {

    EmployeeDao employeeDao;

    public DataRetrievalService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllActiveEmployees() {
        return employeeDao.fetchAllActiveEmployees();
    }

    public List<ShiftDetails> fetchShiftsToday() {
        return employeeDao.fetchShiftsToday()
                .stream()
                .filter(ShiftDetailsHelper.sameTimeClockInAndOutTime.negate()) //Filters out records that are same min and 0 time
                .collect(Collectors.toList());
    }

    public boolean doesEmployeeHaveUnfulfilledShift(int employeeIdToCheck) {
        Integer openCount = employeeDao.countUnfulfilledShift(employeeIdToCheck);
        return (openCount != null && openCount > 0);
    }

}