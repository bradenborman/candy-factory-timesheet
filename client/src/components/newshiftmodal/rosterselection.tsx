import React from "react";
import classNames from "classnames";
import Employee from "../../models/employee";

export interface IRosterSelectionProps {
  person: Employee;
  selected: boolean;
  setSelected: (personId: number) => void;
}

export const RosterSelection: React.FC<IRosterSelectionProps> = (
  props: IRosterSelectionProps
) => {
  return (
    <div
      onClick={() => props.setSelected(props.person.personId)}
      className={classNames("rosterSelectionName", { active: props.selected })}
    >
      {props.person.lastName}, {props.person.firstName}
    </div>
  );
};
