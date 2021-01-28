package cftimesheet.utilities;

import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.models.NewEmployeeRequest;
import cftimesheet.models.UpdateEmployeeRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Date;

public class SqlParamHelperUtility {

    private SqlParamHelperUtility() {
        throw new IllegalStateException("Utility class only");
    }

    public static MapSqlParameterSource getStandardShiftActionParams(ChangeShiftRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("today", new Date(System.currentTimeMillis()));
        params.addValue("personId", request.getPersonId());
        params.addValue("clockTime", request.getClockTime());
        params.addValue("shiftId", request.getShiftId());
        return params;
    }

    public static MapSqlParameterSource getStandardAddEmployeeParams(NewEmployeeRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstName", request.getFirstName());
        params.addValue("lastName", request.getLastName());
        params.addValue("phoneNumber", request.getPhoneNumber());
        params.addValue("email", request.getEmail());
        params.addValue("address", request.getAddress());
        return params;
    }

    public static MapSqlParameterSource updateEmployeeRequestParams(UpdateEmployeeRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", request.getPersonId());
        params.addValue("firstName", request.getFirstName());
        params.addValue("lastName", request.getLastName());
        params.addValue("phoneNumber", request.getPhoneNumber());
        params.addValue("email", request.getEmail());
        params.addValue("address", request.getAddress());
        return params;
    }

}
