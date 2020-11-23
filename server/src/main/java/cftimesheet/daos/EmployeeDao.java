package cftimesheet.daos;

import cftimesheet.daos.dbmappers.EmployeeRowMapper;
import cftimesheet.daos.dbmappers.ShiftDetailsMapper;
import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.models.Employee;
import cftimesheet.models.ShiftDetails;
import cftimesheet.utilities.SqlParamHelperUtility;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

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
        return namedParameterJdbcTemplate.query(SELECT_TODAY_SHIFTS, new ShiftDetailsMapper());
    }

    public void startShift(ChangeShiftRequest request) {
        MapSqlParameterSource params = SqlParamHelperUtility.getStandardShiftActionParams(request);
        namedParameterJdbcTemplate.update(START_SHIFT, params);
    }

    public void endShift(ChangeShiftRequest request) {
        MapSqlParameterSource params = SqlParamHelperUtility.getStandardShiftActionParams(request);
        namedParameterJdbcTemplate.update(END_SHIFT, params);
    }
}