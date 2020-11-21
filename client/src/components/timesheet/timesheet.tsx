import React, { useState } from "react";
import { TimeSheetTable } from "./table/timeSheetTable";
import { TableRow } from "./table/tableRow";
import Employee from "../../models/userdetails";
import ShiftDetails from "../../models/shiftDetials";

require("./timesheet.scss");

export interface ITimesheetProps {
  shifts: ShiftDetails[];
  roster: Employee[];
  updateClockIn: (number: number) => void;
  updateClockOut: (number: number) => void;
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

  const getRosterSelections = (): JSX.Element => {
    const rosterSelections = props.roster.map((person, index) => {
      return <span className="rosterSelectionName">{person.name}</span>;
    });
    return <div className="rosterSelectionDiv">{rosterSelections}</div>;
  };

  const addNewShiftModal = (): JSX.Element | null => {
    if (addNewShiftModalActive) {
      return (
        <div id="addEmployeeModal">
          <div id="activeSection">
            <h2>New Shift - Sign on</h2>
            <p>
              <u>Please select your name:</u>
            </p>
            {getRosterSelections()}
            <p>
              <u>Or dont see your name? </u>
            </p>
            <div className="button-wrapper">
              <button onClick={() => setAddNewShiftModalActive(false)}>
                Cancel
              </button>
            </div>
          </div>
        </div>
      );
    }
    return null;
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
      {addNewShiftModal()}
    </div>
  );
};
