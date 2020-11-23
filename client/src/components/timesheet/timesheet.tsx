import React, { useState } from "react";
import { TimeSheetTable } from "./table/timeSheetTable";
import { TableRow } from "./table/tableRow";
import Employee from "../../models/employee";
import ShiftDetails from "../../models/shiftDetials";
import { NewShiftModal } from "../newshiftmodal/newshiftmodal";

require("./timesheet.scss");

export interface ITimesheetProps {
  shifts: ShiftDetails[];
  roster: Employee[];
  updateClockIn: (number: number) => void;
  updateClockOut: (empId: number, shiftId: number) => void;
  addEmployee?: () => void;
}

export const Timesheet: React.FC<ITimesheetProps> = (
  props: ITimesheetProps
) => {
  const [addNewShiftModalActive, setAddNewShiftModalActive] = useState<boolean>(
    false
  );

  const mapEmployees = () => {
    return props.shifts.map((emp: ShiftDetails) => {
      return (
        <TableRow
          userdetails={emp}
          updateClockOut={props.updateClockOut}
          updateClockIn={props.updateClockIn}
        ></TableRow>
      );
    });
  };

  return (
    <div className="time-sheet-wrapper">
      <div className="time-sheet">
        <h2>Valentines Help</h2>
        <br />
        <h6>{new Date().toDateString()}</h6>
        <div id="sheetControls">
          <button
            className="newRow"
            onClick={() => setAddNewShiftModalActive(true)}
          >
            New Shift
          </button>
        </div>
        <TimeSheetTable>{mapEmployees()}</TimeSheetTable>
      </div>
      <NewShiftModal
        roster={props.roster}
        show={addNewShiftModalActive}
        closeModal={setAddNewShiftModalActive}
      />
    </div>
  );
};
