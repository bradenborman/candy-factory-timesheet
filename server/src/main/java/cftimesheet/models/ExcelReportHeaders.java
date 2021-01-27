package cftimesheet.models;

import cftimesheet.utilities.TimeWorkedUtility;

public enum ExcelReportHeaders {

    NAME("Employee Name"),
    PHONE("Phone Number"),
    EMAIL("Email"),
    ADDRESS("Address/Venmo"),
    CLOCK_IN("Clocked In"),
    CLOCK_OUT("Clocked Out"),
    TOTAL_TIME_WORKED("Total Time Worked");

    private String displayName;

    ExcelReportHeaders(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public String getFieldAssociatedToEmployee(ShiftDetails shiftDetails) {
        switch (this) {
            case NAME:
                return shiftDetails.getName();
            case PHONE:
                return shiftDetails.getPhoneNumber();
            case EMAIL:
                return shiftDetails.getEmail();
            case ADDRESS:
                return shiftDetails.getAddress();
            case CLOCK_IN:
                return shiftDetails.getClockInTime();
            case CLOCK_OUT:
                return shiftDetails.getClockOutTime();
            case TOTAL_TIME_WORKED:
                return TimeWorkedUtility.getTimeWorked(shiftDetails);
            default:
                return "";
        }
    }

}