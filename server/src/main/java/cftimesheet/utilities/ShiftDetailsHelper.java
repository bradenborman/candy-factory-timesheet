package cftimesheet.utilities;

import cftimesheet.models.ShiftDetails;
import org.springframework.util.StringUtils;

import java.util.function.Predicate;

public class ShiftDetailsHelper {

    private ShiftDetailsHelper() {
        throw new IllegalStateException("Utility class only");
    }

    static Predicate<ShiftDetails> isClockInEqualClockOut = shiftDetails ->
            shiftDetails.getClockInTime().equals(shiftDetails.getClockOutTime());

    static Predicate<ShiftDetails> clockOutEmpty = shiftDetails ->
            !StringUtils.isEmpty(shiftDetails.getClockOutTime());

    public static Predicate<ShiftDetails> sameTimeClockInAndOutTime = shiftDetails -> clockOutEmpty.and(isClockInEqualClockOut).test(shiftDetails);

}