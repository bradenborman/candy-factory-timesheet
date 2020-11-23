package cftimesheet.daos;

public interface Queries {

    String SELECT_EMPLOYEES = "SELECT * FROM valentines_seasonal_help";

    String SELECT_TODAY_SHIFTS = "SELECT * FROM EMPLOYEE_SHIFT " +
            "LEFT JOIN valentines_seasonal_help " +
            "ON " +
            "valentines_seasonal_help.emp_id= employee_shift.emp_id " +
            "WHERE shift_date = CURDATE()";

    String START_SHIFT = "INSERT INTO employee_shift " +
            "(emp_id, shift_date, shift_clockIn) " +
            "VALUES (:personId, NOW(), :clockTime)";

    String END_SHIFT = "UPDATE employee_shift " +
            "SET shift_clockOut = :clockTime " +
            "WHERE emp_id = :personId AND shift_id = :shiftId;";

}