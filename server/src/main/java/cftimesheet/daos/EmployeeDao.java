package cftimesheet.daos;

import cftimesheet.daos.dbmappers.EmployeeRowMapper;
import cftimesheet.daos.dbmappers.ShiftDetailsMapper;
import cftimesheet.models.Employee;
import cftimesheet.models.ShiftDetails;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static cftimesheet.daos.Queries.*;

@Component
public class EmployeeDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Employee> fetchAllActiveEmployees() {
        return namedParameterJdbcTemplate.query(SELECT_EMPLOYEES, new EmployeeRowMapper());
    }

    public List<ShiftDetails> fetchShiftsToday() {
        return namedParameterJdbcTemplate.query(SELECT_TODAY_SHIFTS, new ShiftDetailsMapper());
    }

}