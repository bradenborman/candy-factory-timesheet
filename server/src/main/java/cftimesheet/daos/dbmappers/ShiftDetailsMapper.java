package cftimesheet.daos.dbmappers;

import cftimesheet.models.ShiftDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftDetailsMapper implements RowMapper<ShiftDetails> {

    @Override
    public ShiftDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShiftDetails details = new ShiftDetails();
        details.setPersonId(rs.getInt("emp_id"));
        details.setName(rs.getString("emp_name"));
        details.setEmail(rs.getString("emp_email"));
        details.setPhoneNumber(rs.getString("emp_phone"));
        details.setAddress(rs.getString("emp_address"));

        details.setShiftId(rs.getInt("shift_id"));
        details.setClockInTime(rs.getString("shift_clockIn"));
        details.setClockOutTime(rs.getString("shift_clockOut"));
        details.setDateOfShift(rs.getDate("shift_date"));

        return details;
    }

}