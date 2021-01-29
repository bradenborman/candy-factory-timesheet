import React from "react";
import { formatPhoneNumber } from "../../../utilities/phonenumberhelper";
import ShiftDetails from "../../../models/shiftDetials";
import axios from "axios";

require("./table.scss");

export interface ITableRowProps {
  adminMode: boolean;
  userdetails: ShiftDetails;
  updateClockOut: (empId: number, shiftId: number) => void;
}

export const TableRow: React.FC<ITableRowProps> = (props: ITableRowProps) => {
  const deleteShift = (idToUpdate: number, shiftId: number) => {
    const reallyDelete: boolean = confirm(
      "Are you sure you want to delete? You cannot undo action but you can start a new shift."
    );
    if (reallyDelete) {
      let success: Promise<boolean> = makeRestCallToDelete(idToUpdate, shiftId);
      success.then(() => {
        location.reload();
      });
    }
  };

  const makeRestCallToDelete = async (
    idToUpdate: number,
    shiftId: number
  ): Promise<boolean> => {
    try {
      const res: any = await axios.delete(
        `/api/shift/` + idToUpdate + "/" + shiftId
      );
      if (res.data) {
        const response: any = res.data;
        console.log(res);
        return true;
      }
    } catch (err) {
      console.error(err);
      return false;
    }
  };

  const getClockInDetails = (): JSX.Element | JSX.Element => {
    const getDeleteRow = () => {
      if (props.adminMode) {
        return (
          <div
            onDoubleClick={() =>
              deleteShift(props.userdetails.personId, props.userdetails.shiftId)
            }
            className="deleteRowDiv"
          >
            <i className="fa fa-exclamation-circle" aria-hidden="true"></i>{" "}
            Double Click Me To Delete Row
          </div>
        );
      }
    };

    return (
      <div>
        <span>{props.userdetails.clockInTime}</span>
        {getDeleteRow()}
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
          <span className="name">
            {props.userdetails.firstName + " " + props.userdetails.lastName}
          </span>
          <br />
          {formatPhoneNumber(props.userdetails.phoneNumber)}
        </div>
      </td>
      <td className="clockInTd">{getClockInDetails()}</td>
      <td className="clockOutTd">{getClockOutDetails()}</td>
    </tr>
  );
};
