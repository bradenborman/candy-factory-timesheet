import React from "react";
import { formatPhoneNumber } from "../../../utilities/phonenumberhelper";
import ShiftDetails from "../../../models/shiftDetials";

require("./table.scss");

export interface ITableRowProps {
  userdetails: ShiftDetails;
  updateClockIn: (number: number) => void;
  updateClockOut: (empId: number, shiftId: number) => void;
}

export const TableRow: React.FC<ITableRowProps> = (props: ITableRowProps) => {
  const getClockInDetails = (): JSX.Element => {
    if (
      props.userdetails.clockInTime != null &&
      props.userdetails.clockInTime != ""
    ) {
      return <span>{props.userdetails.clockInTime}</span>;
    } else {
      return (
        <i
          onDoubleClick={() => props.updateClockIn(props.userdetails.personId)}
          className="fa fa-play clockIn"
          aria-hidden="true"
        ></i>
      );
    }
  };

  const getClockOutDetails = (): JSX.Element => {
    if (
      props.userdetails.clockOutTime != null &&
      props.userdetails.clockOutTime != ""
    ) {
      return <span>{props.userdetails.clockOutTime}</span>;
    } else if (
      props.userdetails.clockInTime != null &&
      props.userdetails.clockInTime != ""
    ) {
      return (
        <i
          onDoubleClick={() =>
            props.updateClockOut(
              props.userdetails.personId,
              props.userdetails.shiftId
            )
          }
          className="fa fa-check-circle clockOut"
          aria-hidden="true"
        ></i>
      );
    } else {
      return <span></span>; //empty
    }
  };

  return (
    <tr>
      <td>
        <div className="name-phone">
          <span className="name">{props.userdetails.name}</span>
          <br />
          {formatPhoneNumber(props.userdetails.phoneNumber)}
        </div>
        {/* <div className="email">{props.userdetails.email}</div>
        <div className="address">{props.userdetails.address}</div> */}
      </td>
      <td>{getClockInDetails()}</td>
      <td>{getClockOutDetails()}</td>
    </tr>
  );
};
