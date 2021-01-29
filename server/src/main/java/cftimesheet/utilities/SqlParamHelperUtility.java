package cftimesheet.utilities;

import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.models.NewEmployeeRequest;
import cftimesheet.models.UpdateEmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Date;
import java.time.LocalDate;

public class SqlParamHelperUtility {

    private static final Logger logger = LoggerFactory.getLogger(SqlParamHelperUtility.class);

    private SqlParamHelperUtility() {
        throw new IllegalStateException("Utility class only");
    }

    public static MapSqlParameterSource getStandardShiftActionParams(ChangeShiftRequest request) {

        Date date = Date.valueOf(LocalDate.now());
        logger.info("Using Date of: {}", date.toString());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("today", date);
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
        params.addValue("paypal", request.getPaypal());
        params.addValue("venmo", request.getVenmo());
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
