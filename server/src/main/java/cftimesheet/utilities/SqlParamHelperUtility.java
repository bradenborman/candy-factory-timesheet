package cftimesheet.utilities;

import cftimesheet.models.ChangeShiftRequest;
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

}
