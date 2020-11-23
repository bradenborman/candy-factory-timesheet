package cftimesheet.models;

public class ChangeShiftRequest {

    private int personId;
    private int shiftId;
    private String clockTime;
    private ShiftAction shiftAction;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getClockTime() {
        return clockTime;
    }

    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }

    public ShiftAction getShiftAction() {
        return shiftAction;
    }

    public void setShiftAction(ShiftAction shiftAction) {
        this.shiftAction = shiftAction;
    }
}