import React, { useState, useEffect } from "react";
import axios from "axios";
import { Navbar } from "../navbar/navbar";
import { Timesheet } from "../timesheet/timesheet";
import { formatTime } from "../../utilities/datehelper";
import ShiftDetails from "../../models/shiftDetials";
import Employee from "../../models/employee";

require("./app.scss");

export interface IAppProps {}

export const App: React.FC<IAppProps> = (props: IAppProps) => {
  const [employeesShifts, setEmployeesShifts] = useState<ShiftDetails[]>([]);
  const [employeeRoster, setEmployeeRoster] = useState<Employee[]>([]);

  const [adminMode, setAdminMode] = useState<boolean>(false);

  let keyMap: any = {};

  useEffect(() => {
    fetchShiftsToday();
    fetchEmployeeRoster();
  }, []);

  useEffect(() => {
    window.addEventListener("keydown", keyDownListener);
    return () => {
      window.removeEventListener("keydown", keyDownListener);
    };
  }, []);

  const keyDownListener = (e: any): void => {
    keyMap[e.key] = e.type === "keydown";
    if (e.shiftKey && e.ctrlKey && keyMap["A"]) {
      // alert("TOGGLE ADMIN MODE") WHY THIS NO WORK
      setAdminMode(!adminMode);
    }
    keyMap = {};
  };

  const fetchShiftsToday = async () => {
    try {
      const res: any = await axios.get(`/api/shifts:today`);
      if (res.data) {
        const response: ShiftDetails[] = res.data;
        setEmployeesShifts(response);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const fetchEmployeeRoster = async () => {
    try {
      const res: any = await axios.get(`/api/employees`);
      if (res.data) {
        const response: Employee[] = res.data;
        setEmployeeRoster(response);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const setClockOutTime = (employee: ShiftDetails) => {
    console.log("Making call to update Clock out time");
    const clockOutTime: string = formatTime(new Date());
    employee.clockOutTime = clockOutTime;
    callForClockout(employee, clockOutTime);
    return employee;
  };

  const callForClockout = async (employee: ShiftDetails, time: string) => {
    try {
      const res: any = await axios.post(`/api/shift-action`, {
        personId: employee.personId,
        shiftId: employee.shiftId,
        clockTime: time,
        shiftAction: "END"
      });
      if (res.status === 200) console.log("Success call to clock out");
    } catch (err) {
      alert("ERROR");
    }
  };

  const updateEmployeeClockedOutTime = (
    idToUpdate: number,
    shiftId: number
  ) => {
    const newState: ShiftDetails[] = employeesShifts.map(shift => {
      return shift.personId == idToUpdate && shift.shiftId == shiftId
        ? setClockOutTime(shift)
        : shift;
    });
    setEmployeesShifts(newState);
  };

  return (
    <div className="app-wrapper">
      <Navbar />
      <Timesheet
        adminMode={adminMode}
        roster={employeeRoster}
        shifts={employeesShifts}
        updateClockOut={updateEmployeeClockedOutTime}
      />
    </div>
  );
};
