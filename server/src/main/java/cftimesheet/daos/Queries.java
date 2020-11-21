package cftimesheet.daos;

public interface Queries {

    String SELECT_EMPLOYEES = "SELECT * FROM valentines_seasonal_help";

    String SELECT_TODAY_SHIFTS = "SELECT * FROM EMPLOYEE_SHIFT " +
            "LEFT JOIN valentines_seasonal_help " +
            "ON " +
            "valentines_seasonal_help.emp_id= employee_shift.emp_id " +
            "WHERE shift_date = CURDATE()";

}