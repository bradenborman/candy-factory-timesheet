import React from "react";
import classNames from "classnames";
import Employee from "../../models/employee";
import { ContextMenu } from "../context-menu/ContextMenu";
import { MenuItem } from "../context-menu/menuItem";
import { Redirect } from "react-router-dom";

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

  const getContextMenu = (): JSX.Element => {
    if (props.selected)
      return (
        <ContextMenu allowedClasses={["active"]}>
          <MenuItem action={editUser} iconClass="fa-pencil">
            Edit User
          </MenuItem>
          <MenuSeparator />
          <MenuItem disabled iconClass="fa-trash">
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
