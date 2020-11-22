import Employee from "./employee";

export default interface ShiftDetails extends Employee {
  shiftId?: number;
  dateOfShift?: string;
  clockInTime?: string;
  clockOutTime?: string;
}
