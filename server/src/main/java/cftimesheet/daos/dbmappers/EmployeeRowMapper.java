package cftimesheet.daos.dbmappers;

import cftimesheet.models.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper  implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("emp_id"));
        employee.setName(rs.getString("emp_name"));
        employee.setEmail(rs.getString("emp_email"));
        employee.setPhoneNumber(rs.getString("emp_phone"));
        employee.setAddress(rs.getString("emp_address"));
        return employee;
    }
}