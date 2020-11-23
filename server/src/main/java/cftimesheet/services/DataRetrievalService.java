package cftimesheet.services;

import cftimesheet.daos.EmployeeDao;
import cftimesheet.models.Employee;
import cftimesheet.models.ShiftDetails;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return employeeDao.fetchShiftsToday();
    }

    public boolean doesEmployeeHaveUnfulfilledShift(int employeeIdToCheck) {
        Integer openCount = employeeDao.countUnfulfilledShift(employeeIdToCheck);
        return (openCount != null && openCount > 0);
    }

}