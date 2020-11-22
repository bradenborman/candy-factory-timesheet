import React, { useState } from "react";
import axios from "axios";
import { RosterSelection } from "./rosterselection";
import Employee from "../../models/employee";
import { formatTime } from "../../utilities/datehelper";

require("./newshiftmodal.scss");

export interface INewShiftModalProps {
  show: boolean;
  roster: Employee[];
  closeModal: (close: boolean) => void;
}

export const NewShiftModal: React.FC<INewShiftModalProps> = (
  props: INewShiftModalProps
) => {
  const [activeIdSelected, setActiveIdSelected] = useState<number | null>();

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
      const res: any = await axios.post(`/api/start-shift`, {
        personId: activeIdSelected,
        clockInTime: formatTime(new Date())
      });
      if (res.status === 200) location.reload();
    } catch (err) {
      alert("ERROR");
    }
  };

  const getModal = (): JSX.Element => {
    if (props.show) {
      return (
        <div id="addEmployeeModal">
          <div id="activeSection">
            <h2>New Shift - Sign on</h2>
            <p>
              <u>Please select your name:</u>
            </p>
            {getRosterSelections()}
            <p>
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
            </p>
            <div className="button-wrapper">
              <button onClick={() => props.closeModal(false)}>
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
