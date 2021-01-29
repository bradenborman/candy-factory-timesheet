import React, { useState, useEffect } from "react";
import axios from "axios";
import { Navbar } from "../navbar/navbar";
import { Timesheet } from "../timesheet/timesheet";
import { formatTime } from "../../utilities/datehelper";
import ShiftDetails from "../../models/shiftDetials";
import Employee from "../../models/employee";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { EditUser } from "../editUser/editUser";

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
      const res: any = await axios.get(`/api/shifts:today`);
      if (res.data) {
        const response: ShiftDetails[] = res.data;
        setEmployeesShifts(response);
      }
    }
  };

  const fetchEmployeeRoster = async () => {
    try {
      const res: any = await axios.get(`/api/employees`);
      if (res.data) {
        const response: Employee[] = res.data;
        setEmployeeRoster(response);
        console.log("Roster");
        console.log(response);
      }
    } catch (err) {
      console.error(err);
      const res: any = await axios.get(`/api/employees`);
      if (res.data) {
        const response: Employee[] = res.data;
        setEmployeeRoster(response);
        console.log("Roster");
        console.log(response);
      }
    }
  };

  const setClockOutTime = (employee: ShiftDetails, timeOut?: string) => {
    console.log("Making call to update Clock out time");
    const clockOutTime: string =
      timeOut != null ? timeOut : formatTime(new Date());
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
    if (adminMode) {
      const response = prompt(
        "Admin mode Clockout -> Please enter a time: (XX:XX AM/PM)"
      );
      if (response != null && response != undefined) {
        const newState: ShiftDetails[] = employeesShifts.map(shift => {
          return shift.personId == idToUpdate && shift.shiftId == shiftId
            ? setClockOutTime(shift, response.toUpperCase())
            : shift;
        });
        setEmployeesShifts(newState);
      }
    } else {
      const newState: ShiftDetails[] = employeesShifts.map(shift => {
        return shift.personId == idToUpdate && shift.shiftId == shiftId
          ? setClockOutTime(shift)
          : shift;
      });
      setEmployeesShifts(newState);
    }
  };

  return (
    <Router>
      <Navbar />
      <Switch>
        <Route exact path={["/"]}>
          <Timesheet
            adminMode={adminMode}
            roster={employeeRoster}
            shifts={employeesShifts}
            updateClockOut={updateEmployeeClockedOutTime}
          />
        </Route>
        <Route exact path="/edit-user/:personId">
          <EditUser />
        </Route>
      </Switch>
    </Router>
  );
};
