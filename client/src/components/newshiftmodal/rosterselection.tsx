import React from "react";
import classNames from "classnames";
import axios from "axios";

import Employee from "../../models/employee";
import { ContextMenu } from "../context-menu/ContextMenu";
import { MenuItem } from "../context-menu/menuItem";

import { MenuSeparator } from "../context-menu/menuSeparator";

export interface IRosterSelectionProps {
  person: Employee;
  selected: boolean;
  setSelected: (personId: number) => void;
}

export const RosterSelection: React.FC<IRosterSelectionProps> = (
  props: IRosterSelectionProps
) => {
  const editUser = () => {
    location.replace("/edit-user/" + props.person.personId);
  };

  const deleteUser = () => {
    console.log("Calling to detete user.");
    if (
      confirm(
        "Are you sure you want to delete: " +
          props.person.firstName +
          " " +
          props.person.lastName
      )
    )
      deleteUserCall();
  };

  const startCustomShift = () => {
    const customClockIn: string = prompt(
      "Admin Clockin -> Please enter a time: (XX:XX AM/PM)"
    );
    startShiftCall(customClockIn);
  };

  const startShiftCall = async (customClockIn: string) => {
    const res: any = await axios
      .post(`/api/shift-action`, {
        personId: props.person.personId,
        clockTime: customClockIn,
        shiftId: -1,
        shiftAction: "START"
      })
      .then(function(response) {
        location.replace("/");
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  const deleteUserCall = async () => {
    let response = axios
      .delete("/api/user", { params: { personId: props.person.personId } })
      .then(function(response) {
        location.replace("/");
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  const getContextMenu = (): JSX.Element => {
    if (props.selected)
      return (
        <ContextMenu>
          <MenuItem action={editUser} iconClass="fa-pencil">
            Edit{" "}
            {props.person.firstName +
              " " +
              props.person.lastName.substring(0, 1)}
          </MenuItem>
          <MenuItem action={startCustomShift} iconClass="fa-fast-forward">
            Custom Start Shift
          </MenuItem>
          <MenuSeparator />
          <MenuItem action={deleteUser} iconClass="fa-trash">
            Delete User
          </MenuItem>
        </ContextMenu>
      );
    return null;
  };

  return (
    <div
      onClick={() => props.setSelected(props.person.personId)}
      className={classNames("rosterSelectionName", { active: props.selected })}
    >
      {props.person.lastName}, {props.person.firstName}
      {getContextMenu()}
    </div>
  );
};
