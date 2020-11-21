import React, { useState, useEffect } from "react";
import UserDetails from "../../../models/userdetails";
import { formatPhoneNumber } from "../../../utilities/phonenumberhelper";

require("./table.scss");

export interface ITableRowProps {
  userdetails: UserDetails;
  updateClockIn: (number: number) => void
  updateClockOut: (number: number) => void
}

export const TableRow: React.FC<ITableRowProps> = (props: ITableRowProps) => {
  

  const getClockInDetails = (): JSX.Element => {
    if (props.userdetails.clockedIn != null && props.userdetails.clockedIn != "") {
      return <span>{props.userdetails.clockedIn}</span>
    } else {
      return <i onDoubleClick={() => props.updateClockIn(props.userdetails.personId)} className="fa fa-play clockIn" aria-hidden="true"></i>
    }
  }

  const getClockOutDetails = (): JSX.Element => {
    if (props.userdetails.clockedOut != null && props.userdetails.clockedOut != "") {
      return <span>{props.userdetails.clockedOut}</span>
    } else if(props.userdetails.clockedIn != null && props.userdetails.clockedIn != "") {
      return <i onDoubleClick={() => props.updateClockOut(props.userdetails.personId)} className="fa fa-check-circle clockOut" aria-hidden="true"></i>
    } else {
      return <span></span> //empty
    }
  }
  
  return (
    <tr>
      <td>
        <div className="name-phone"><span className="name">{props.userdetails.name}</span>
          <br />{formatPhoneNumber(props.userdetails.phone)}         
        </div>
        <div className="email">{props.userdetails.email}</div>
        <div className="address">{props.userdetails.address}</div>
      </td>
      <td>{getClockInDetails()}</td>
      <td>{getClockOutDetails()}</td>  
    </tr>
  );
};
