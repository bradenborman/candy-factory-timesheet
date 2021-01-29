package cftimesheet.daos;

import cftimesheet.daos.dbmappers.EmployeeRowMapper;
import cftimesheet.daos.dbmappers.ShiftDetailsMapper;
import cftimesheet.models.*;
import cftimesheet.utilities.SqlParamHelperUtility;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

import static cftimesheet.daos.Queries.*;

@Component
public class EmployeeDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Employee> fetchAllActiveEmployees() {
        return namedParameterJdbcTemplate.query(SELECT_EMPLOYEES, new EmployeeRowMapper());
    }

    public List<ShiftDetails> fetchShiftsToday() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("today", new Date(System.currentTimeMillis()));
        return namedParameterJdbcTemplate.query(SELECT_TODAY_SHIFTS, params, new ShiftDetailsMapper());
    }

    public void startShift(ChangeShiftRequest request) {
        MapSqlParameterSource params = SqlParamHelperUtility.getStandardShiftActionParams(request);
        namedParameterJdbcTemplate.update(START_SHIFT, params);
    }

    public void endShift(ChangeShiftRequest request) {
        MapSqlParameterSource params = SqlParamHelperUtility.getStandardShiftActionParams(request);
        namedParameterJdbcTemplate.update(END_SHIFT, params);
    }

    public void createNewEmployee(NewEmployeeRequest request) {
        MapSqlParameterSource params = SqlParamHelperUtility.getStandardAddEmployeeParams(request);
        namedParameterJdbcTemplate.update(CREATE_NEW_EMPLOYEE, params);
    }

    public Integer countUnfulfilledShift(int empId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", empId);
        return namedParameterJdbcTemplate.queryForObject(COUNT_UN_FULL_FILLED_SHIFTS, params, Integer.class);
    }

    public void deleteShift(String shiftToDelete, String empId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shiftToDelete", shiftToDelete);
        params.addValue("empId", empId);
        namedParameterJdbcTemplate.update(DELETE_SHIFT_BY_SHIFT_ID_PERSON_ID, params);
    }

    public Employee getEmployeeData(String employeeId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", employeeId);
        return namedParameterJdbcTemplate.queryForObject(SELECT_EMPLOYEE, params, new EmployeeRowMapper());
    }

    public void updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) {
        MapSqlParameterSource params = SqlParamHelperUtility.updateEmployeeRequestParams(updateEmployeeRequest);
        namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_DATA, params);
    }

    public void deleteShiftsAssociatedToEmployee(String personId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", personId);
        namedParameterJdbcTemplate.update(DELETE_EMP_SHIFTS, params);
    }

    public void deleteEmployee(String personId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", personId);
        namedParameterJdbcTemplate.update(DELETE_EMPLOYEE, params);
    }
}