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

export const NewShiftModal: React.FC<INewShiftModalProps> = (
  props: INewShiftModalProps
) => {
  const [activeIdSelected, setActiveIdSelected] = useState<number | null>();

  const [newPersonModeActive, setNewPersonModeActive] = useState<boolean>(
    false
  );

  const [firstName, setFirstName] = useState<string>(null);
  const [lastName, setLastName] = useState<string>(null);
  const [phoneInput, setPhoneInput] = useState<string>("");
  const [emailInput, setEmailInput] = useState<string>("");

  const [addressInput, setAddressInput] = useState<string>("");
  const [paypalInput, setPaypalInput] = useState<string>("");
  const [venmoInput, setVenmoInput] = useState<string>("");

  const [callInProgress, setCallInProgress] = useState<boolean>(false);

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

  const addEmployeeToRoster = async () => {
    setCallInProgress(true);
    try {
      const res: any = await axios.post(`/api/employee:create`, {
        firstName: firstName,
        lastName: lastName,
        phoneNumber: phoneInput,
        email: emailInput,
        address: addressInput,
        venmo: venmoInput,
        paypal: paypalInput
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
    const handleSubmitNewEmployee = (e: any) => {
      e.preventDefault();
      addEmployeeToRoster();
    };

    return (
      <div id="employeeInputsArea" className="section">
        <p>
          <u>Please Enter All Fields</u>
        </p>
        <form onSubmit={e => handleSubmitNewEmployee(e)}>
          <div id="wrapper">
            <input
              type="text"
              value={firstName}
              placeholder=" "
              onChange={e => setFirstName(e.target.value)}
              required
            />
            <label>First Name</label>
            {/*  */}
            <input
              type="text"
              value={lastName}
              placeholder=" "
              onChange={e => setLastName(e.target.value)}
              required
            />
            <label>Last Name</label>
            {/*  */}
            <input
              type="text"
              value={phoneInput}
              placeholder=" "
              onChange={e => setPhoneInput(e.target.value)}
              required
            />
            <label>Phone Number</label>
            {/*  */}
            <input
              type="email"
              value={emailInput}
              placeholder=" "
              onChange={e => setEmailInput(e.target.value)}
            />
            <label>Email Adress - Not required</label>
          </div>
          <div id="wrapperRight">
            <p>Please enter one of the following:</p>
            <div id="paymentWrapper">
              <label>
                <i className="fa fa-envelope" /> Mailing Address
              </label>
              <input
                type="text"
                value={addressInput}
                placeholder=" "
                onChange={e => setAddressInput(e.target.value)}
              />
              <label>
                <i className="fa fa-paypal" /> Paypal username
              </label>
              <input
                type="text"
                value={paypalInput}
                placeholder=" "
                onChange={e => setPaypalInput(e.target.value)}
              />
              <label>Venmo username</label>
              <input
                type="text"
                value={venmoInput}
                placeholder=" "
                onChange={e => setVenmoInput(e.target.value)}
              />
            </div>
          </div>
          <br />
          <button id="submitEmpBtn" type="submit">
            Create Employee
          </button>
        </form>
      </div>
    );
  };

  const getRosterSelections = (): JSX.Element => {
    const getRosterSelectionsDivs = (): JSX.Element => {
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

    return (
      <div id="nameSelectionArea" className="section">
        <p>
          <u>Please select your name:</u>
          <br />
        </p>
        {getRosterSelectionsDivs()}
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
            {newPersonModeActive ? getAddPersonInputs() : getRosterSelections()}
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
