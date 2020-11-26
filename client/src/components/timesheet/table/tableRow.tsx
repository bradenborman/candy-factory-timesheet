import React from "react";
import { formatPhoneNumber } from "../../../utilities/phonenumberhelper";
import ShiftDetails from "../../../models/shiftDetials";

require("./table.scss");

export interface ITableRowProps {
  userdetails: ShiftDetails;
  updateClockOut: (empId: number, shiftId: number) => void;
  deleteShift: (empId: number, shiftId: number) => void;
}

export const TableRow: React.FC<ITableRowProps> = (props: ITableRowProps) => {
  const getClockInDetails = (): JSX.Element | JSX.Element => {
    return (
      <div>
        <span>{props.userdetails.clockInTime}</span>
        <div
          onDoubleClick={() =>
            props.deleteShift(
              props.userdetails.personId,
              props.userdetails.shiftId
            )
          }
          className="deleteRowDiv"
        >
          <i className="fa fa-exclamation-circle" aria-hidden="true"></i> Double
          Click Me To Delete Row
        </div>
      </div>
    );
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
      </td>
      <td className="clockInTd">{getClockInDetails()}</td>
      <td>{getClockOutDetails()}</td>
    </tr>
  );
};
