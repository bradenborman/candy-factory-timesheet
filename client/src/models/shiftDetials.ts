import Employee from "./userdetails";

export default interface ShiftDetails extends Employee {
  shiftId?: number;
  dateOfShift?: string;
  clockInTime?: string;
  clockOutTime?: string;
}
