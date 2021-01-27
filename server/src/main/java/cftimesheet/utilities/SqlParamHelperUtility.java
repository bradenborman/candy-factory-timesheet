package cftimesheet.utilities;

import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.models.NewEmployeeRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class SqlParamHelperUtility {

    private SqlParamHelperUtility() {
        throw new IllegalStateException("Utility class only");
    }

    public static MapSqlParameterSource getStandardShiftActionParams(ChangeShiftRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("personId", request.getPersonId());
        params.addValue("clockTime", request.getClockTime());
        params.addValue("shiftId", request.getShiftId());
        return params;
    }

    public static MapSqlParameterSource getStandardAddEmployeeParams(NewEmployeeRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstName", request.getEmployeeName()); //TODO fix
        params.addValue("lastName", request.getEmployeeName());
        params.addValue("phoneNumber", request.getPhoneNumber());
        params.addValue("email", request.getEmail());
        params.addValue("address", request.getAddress());
        return params;
    }

}
