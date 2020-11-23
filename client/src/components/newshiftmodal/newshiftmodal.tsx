import React, { useState } from "react";
import axios from "axios";
import { RosterSelection } from "./rosterselection";
import Employee from "../../models/employee";
import { formatTime } from "../../utilities/datehelper";
import classNames from "classnames";

require("./newshiftmodal.scss");

export interface INewShiftModalProps {
  show: boolean;
  roster: Employee[];
  closeModal: (close: boolean) => void;
}

// TODO change to render on bool and not class names
export const NewShiftModal: React.FC<INewShiftModalProps> = (
  props: INewShiftModalProps
) => {
  const [activeIdSelected, setActiveIdSelected] = useState<number | null>();

  const [newPersonModeActive, setNewPersonModeActive] = useState<boolean>(
    false
  );

  const [callInProgress, setCallInProgress] = useState<boolean>(false);

  const getRosterSelections = (): JSX.Element => {
    const rosterSelections = props.roster.map((person, index) => {
      return (
        <RosterSelection
          setSelected={setActiveIdSelected}
          key={index}
          selected={activeIdSelected == person.personId}
          person={person}
        />
      );
    });
    return <div className="rosterSelectionDiv">{rosterSelections}</div>;
  };

  const addEmployeeToNewShift = async () => {
    setCallInProgress(true);
    try {
      const res: any = await axios.post(`/api/shift-action`, {
        personId: activeIdSelected,
        clockTime: formatTime(new Date()),
        shiftId: -1,
        shiftAction: "START"
      });
      if (res.status === 200) location.reload();
    } catch (err) {
      const { data } = err.response;
      if (typeof data === "string") {
        alert(data);
      }
      setCallInProgress(false);
    }
  };

  const handleModalClose = () => {
    setNewPersonModeActive(false);
    setActiveIdSelected(null);
    props.closeModal(false);
  };

  const getAddPersonInputs = (): JSX.Element => {
    return (
      <div
        id="employeeInputsArea"
        className={classNames("section", { hidden: !newPersonModeActive })}
      >
        <p>
          <u>Please Enter All Fields</u>
        </p>
        <form>
          <label>Name:</label> <input type="phone" name="phoneNumber" />
          <label>Phone Number:</label> <input />
          <label>Email:</label> <input />
          <label>Address:</label> <input />
          <button type="submit">Create Employee</button>
        </form>
      </div>
    );
  };

  const getRosterSelections_Default = (): JSX.Element => {
    return (
      <div
        id="nameSelectionArea"
        className={classNames("section", { hidden: newPersonModeActive })}
      >
        <p>
          <u>Please select your name:</u>
        </p>
        {getRosterSelections()}
        <div className="button-wrapper">
          <button
            disabled={activeIdSelected == null}
            id="addSelectedBtn"
            onClick={() => addEmployeeToNewShift()}
          >
            {callInProgress ? "Updating..." : "Start Shift"}
          </button>
        </div>
        <u>Or dont see your name? </u>
        <button
          id="newEmployeeBtn"
          onClick={() => setNewPersonModeActive(true)}
        >
          New Person
        </button>
      </div>
    );
  };

  const getModal = (): JSX.Element => {
    if (props.show) {
      return (
        <div id="addEmployeeModal">
          <div id="activeSection">
            <h2>New Shift - Sign on</h2>
            {getRosterSelections_Default()}
            {getAddPersonInputs()}
            <div className="button-wrapper">
              <button id="closeBtn" onClick={() => handleModalClose()}>
                Cancel and Close
              </button>
            </div>
          </div>
        </div>
      );
    }
    return null;
  };

  return <div>{getModal()}</div>;
};
