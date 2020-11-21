import React, { useState, useEffect } from "react";
import { Navbar } from "../navbar/navbar";
import { Timesheet } from "../timesheet/timesheet";
import UserDetails from "../../models/userdetails";
import { formatTime } from "../../utilities/datehelper";

require("./app.scss");

export interface IAppProps {}

export const App: React.FC<IAppProps> = (props: IAppProps) => {
  
  const [employees, setEmployees] = useState<UserDetails[] | null>(
    [
      {
        personId: 1,
        name: "Braden Borman",
        phone: "(573) 826-1903",
        email: "bradenborman@hotmail.com",
        address: "3601 West Broadway Apt 21102 Columbia Missouri 65203",
        clockedIn: null,
        clockedOut: null,
      },
      {
        personId: 2,
        name: "Braden Borman",
        phone: "5558461201",
        email: "bradenborman@hotmail.com",
        address: "3601 West Broadway Apt 21102 Columbia Missouri 65203",
        clockedIn: null,
        clockedOut: null,
      },
      {
        personId: 3,
        name: "Braden Borman",
        phone: "(573) 826-1903",
        email: "bradenborman@hotmail.com",
        address: "3601 West Broadway Apt 21102 Columbia Missouri 65203",
        clockedIn: null,
        clockedOut: null,
      },
      {
        personId: 4,
        name: "Braden Borman",
        phone: "(573) 826-1903",
        email: "bradenborman@hotmail.com",
        address: "3601 West Broadway Apt 21102 Columbia Missouri 65203",
        clockedIn: null,
        clockedOut: null,
      },
    ]
  );

  useEffect(() => {

  }, []);


  const setClockInTime = (employee: UserDetails) => {
    console.log("Making call to update Clock in time")
    employee.clockedIn = formatTime(new Date)
    return employee;
  }

  const setClockOutTime = (employee: UserDetails) => {
    console.log("Making call to update Clock out time")
    employee.clockedOut = formatTime(new Date)
    return employee;
  }

  const updateEmployeeClockedInTime = (idToUpdate: number) => {
    const newState: UserDetails[] = employees.map((employee) => {
        return employee.personId != idToUpdate ? employee : setClockInTime(employee)
    })
    setEmployees(newState)
  }

  const updateEmployeeClockedOutTime = (idToUpdate: number) => {
    const newState: UserDetails[] = employees.map((employee) => {
        return employee.personId != idToUpdate ? employee : setClockOutTime(employee)
    })
    setEmployees(newState)
  }


  return (
    <div className="app-wrapper">
      <Navbar />
      <Timesheet employees={employees} updateClockIn={updateEmployeeClockedInTime} updateClockOut={updateEmployeeClockedOutTime} />
    </div>
  );
};
