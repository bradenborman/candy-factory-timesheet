package cftimesheet.daos;

public interface Queries {

    String SELECT_EMPLOYEES = "SELECT * FROM valentines_seasonal_help ORDER BY emp_name_last";

    String SELECT_EMPLOYEE = "SELECT * FROM valentines_seasonal_help WHERE emp_id = :empId";

    String DELETE_EMPLOYEE = "DELETE FROM valentines_seasonal_help WHERE emp_id = :empId";

    String DELETE_EMP_SHIFTS = "DELETE FROM employee_shift WHERE emp_id = :empId";

    String UPDATE_EMPLOYEE_DATA = "UPDATE valentines_seasonal_help " +
            "SET emp_name_first = :firstName, " +
            "emp_name_last = :lastName, emp_phone = :phoneNumber, " +
            "emp_address = :address, emp_email = :email " +
            "WHERE emp_id = :empId";

    String SELECT_TODAY_SHIFTS = "SELECT * FROM EMPLOYEE_SHIFT " +
            "LEFT JOIN valentines_seasonal_help " +
            "ON " +
            "valentines_seasonal_help.emp_id= employee_shift.emp_id " +
            "WHERE shift_date = :today " +
            "ORDER BY shift_clockOut";

    String START_SHIFT = "INSERT INTO employee_shift " +
            "(emp_id, shift_date, shift_clockIn) " +
            "VALUES (:personId, :today, :clockTime)";

    String CREATE_NEW_EMPLOYEE = "INSERT INTO valentines_seasonal_help " +
            "(emp_name_first, emp_name_last, emp_phone, emp_address, emp_email) " +
            "VALUES (:firstName, :lastName, :phoneNumber, :address, :email)";

    String END_SHIFT = "UPDATE employee_shift " +
            "SET shift_clockOut = :clockTime " +
            "WHERE emp_id = :personId AND shift_id = :shiftId;";

    String COUNT_UN_FULL_FILLED_SHIFTS = "SELECT count(emp_id) FROM employee_shift WHERE shift_clockOut is null AND emp_id = :empId";


    String DELETE_SHIFT_BY_SHIFT_ID_PERSON_ID = "DELETE FROM employee_shift WHERE shift_id = :shiftToDelete AND emp_id = :empId";

}