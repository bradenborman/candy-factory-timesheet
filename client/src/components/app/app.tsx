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

  useEffect(() => {
    fetchShiftsToday();
    fetchEmployeeRoster();
  }, []);

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

  const setClockInTime = (employee: ShiftDetails) => {
    console.log("Making call to update Clock in time");
    employee.clockInTime = formatTime(new Date());
    return employee;
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
        clockTime: time,
        shiftAction: "END"
      });
      if (res.status === 200) location.reload();
    } catch (err) {
      alert("ERROR");
    }
  };

  const updateEmployeeClockedInTime = (idToUpdate: number) => {
    const newState: ShiftDetails[] = employeesShifts.map(employee => {
      return employee.personId != idToUpdate
        ? employee
        : setClockInTime(employee);
    });
    setEmployeesShifts(newState);
  };

  const updateEmployeeClockedOutTime = (idToUpdate: number) => {
    const newState: ShiftDetails[] = employeesShifts.map(employee => {
      return employee.personId != idToUpdate
        ? employee
        : setClockOutTime(employee);
    });
    setEmployeesShifts(newState);
  };

  return (
    <div className="app-wrapper">
      <Navbar />
      <Timesheet
        roster={employeeRoster}
        shifts={employeesShifts}
        updateClockIn={updateEmployeeClockedInTime}
        updateClockOut={updateEmployeeClockedOutTime}
      />
    </div>
  );
};
