package cftimesheet.utilities;

import cftimesheet.models.ShiftDetails;
import cftimesheet.models.TimeWorkedBreakdown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

public class TimeWorkedUtility {

    private static final Logger logger = LoggerFactory.getLogger(TimeWorkedUtility.class);

    public static String getTimeWorked(ShiftDetails shiftDetails) {
        return getTimeWorked(shiftDetails.getClockInTime(), shiftDetails.getClockOutTime());
    }

    public static String getTimeWorked(String clockInTime, String clockOutTime) {
        if ((clockInTime != null && !clockInTime.equals("")) && (clockOutTime != null && !clockOutTime.equals(""))) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("hh:mm a"))
                    .appendOptional(DateTimeFormatter.ofPattern("h:mm a"))
                    .toFormatter();

            TimeWorkedBreakdown timeWorked = getTimeWorkedHours(
                    LocalTime.parse(clockInTime, formatter),
                    LocalTime.parse(clockOutTime, formatter)
            );

            logger.info("Time worked: {} Hours and {} Mins.", timeWorked.getHours(), timeWorked.getMinutes());
            return timeWorked.getMinutes() + "Hrs " + timeWorked.getMinutes() + "mins";
        }
        return "";
    }

    public static TimeWorkedBreakdown getTimeWorkedHours(LocalTime clockInTime, LocalTime clockOutTime) {
        TimeWorkedBreakdown worked = new TimeWorkedBreakdown();
        Duration timeWorked = Duration.between(clockInTime, clockOutTime);
        worked.setHours(timeWorked.toHours());
        Duration remaining = timeWorked.minus(Duration.of(worked.getHours(), ChronoUnit.HOURS));
        worked.setMinutes(remaining.toMinutes());
        return worked;
    }

}