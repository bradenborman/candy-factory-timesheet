import React, { useState } from "react";
import { TimeSheetTable } from "./table/timeSheetTable";
import { TableRow } from "./table/tableRow";
import UserDetails from "../../models/userdetails";

require("./timesheet.scss");

export interface ITimesheetProps {
  employees: UserDetails[];
  updateClockIn: (number: number) => void;
  updateClockOut: (number: number) => void;
  addEmployee?: () => void;
}

export const Timesheet: React.FC<ITimesheetProps> = (
  props: ITimesheetProps
) => {
  const [addEmployeeModalActive, setAddEmployeeModalActive] = useState<boolean>(
    false
  );

  const mapEmployees = () => {
    return props.employees.map((emp) => {
      return (
        <TableRow
          userdetails={emp}
          updateClockOut={props.updateClockOut}
          updateClockIn={props.updateClockIn}
        ></TableRow>
      );
    });
  };

  const addEmployeeModal = (): JSX.Element | null => {
    if (addEmployeeModalActive) {
      return (
        <div id="addEmployeeModal">
          <div id="activeSection">
            <h2>Add New Employee</h2>
            <div className="inputs"></div>
            <div className="button-wrapper">
              <button>Add Employee</button>
              <button onClick={() => setAddEmployeeModalActive(false)}>
                Cancel
              </button>
            </div>
          </div>
        </div>
      );
    }
    return null
  };

  return (
    <div className="time-sheet-wrapper">
      <div className="time-sheet">
        <h2>Valentines Help</h2>
        <br />
        <h6>{new Date().toDateString()}</h6>
        <div id="sheetControls">
          <button className="newRow" onClick={() => setAddEmployeeModalActive(true)}>New Employee</button>
        </div>
        <TimeSheetTable>{mapEmployees()}</TimeSheetTable>
      </div>
      {addEmployeeModal()}
    </div>
  );
};
