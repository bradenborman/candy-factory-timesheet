package cftimesheet.models;

import cftimesheet.utilities.TimeWorkedUtility;

public enum ExcelReportHeaders {

    F_NAME("First Name"),
    L_NAME("Last Name"),
    PHONE("Phone Number"),
    EMAIL("Email"),
    ADDRESS("Address"),
    VENMO("Venmo"),
    PAYPAL("PayPal"),
    CLOCK_IN("Clocked In"),
    CLOCK_OUT("Clocked Out"),
    TOTAL_TIME_WORKED("Total Time Worked"),
    TOTAL_TIME_WORKED_MIN_TOTAL("Total Time Worked");

    String displayName;

    ExcelReportHeaders(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFieldAssociatedToEmployee(ShiftDetails shiftDetails) {
        switch (this) {
            case F_NAME:
                return shiftDetails.getFirstName();
            case L_NAME:
                return shiftDetails.getLastName();
            case PHONE:
                return shiftDetails.getPhoneNumber();
            case EMAIL:
                return shiftDetails.getEmail();
            case ADDRESS:
                return shiftDetails.getAddress();
            case PAYPAL:
                return shiftDetails.getPaypal();
            case VENMO:
                return shiftDetails.getVenmo();
            case CLOCK_IN:
                return shiftDetails.getClockInTime();
            case CLOCK_OUT:
                return shiftDetails.getClockOutTime();
            case TOTAL_TIME_WORKED:
                return TimeWorkedUtility.getTimeWorked(shiftDetails);
            case TOTAL_TIME_WORKED_MIN_TOTAL:
                return TimeWorkedUtility.getTimeWorkedTotalMins(shiftDetails);
            default:
                return "";
        }
    }

}