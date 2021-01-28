import React, { useState, useEffect } from "react";
import { useParams } from "react-router";
import { Link } from "react-router-dom";
import axios from "axios";
import Employee from "../../models/employee";
require("./editUser.scss");

export interface IEditUserProps {}
export const EditUser: React.FC<IEditUserProps> = (props: IEditUserProps) => {
  const { personId } = useParams();

  const [firstName, setFirstName] = useState<string>();
  const [lastName, setLastName] = useState<string>();
  const [email, setEmail] = useState<string>();
  const [address, setAddress] = useState<string>();
  const [phoneNumber, setPhoneNumber] = useState<string>();

  useEffect(() => {
    fetchEmployeeData();
  }, []);

  const fetchEmployeeData = async () => {
    try {
      const res: any = await axios.get(`/api/employee-data/` + personId);
      if (res.data) {
        const response: Employee = res.data;
        setFirstName(response.firstName);
        setLastName(response.lastName);
        setEmail(response.email);
        setAddress(response.address);
        setPhoneNumber(response.phoneNumber);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    let response = axios
      .post("/api/update-employee-data", {
        personId: personId,
        firstName: firstName,
        lastName: lastName,
        phoneNumber: phoneNumber,
        email: email,
        address: address
      })
      .then(function(response) {
        location.replace("/");
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  const getInputs = (): JSX.Element => {
    if (firstName == null) return <h2>Loading...</h2>;
    return (
      <div id="personDetails">
        <form onSubmit={handleSubmit}>
          <div className="inputGroup">
            <label htmlFor="firstName">First Name:</label>
            <input
              id="firstName"
              name="firstName"
              type="text"
              value={firstName}
              onChange={e => setFirstName(e.target.value)}
            />
          </div>
          <div className="inputGroup">
            <label htmlFor="lastName">Last Name:</label>
            <input
              id="lastName"
              name="lastName"
              type="text"
              value={lastName}
              onChange={e => setLastName(e.target.value)}
            />
          </div>
          <div className="inputGroup">
            <label htmlFor="phone">Phone:</label>
            <input
              id="phone"
              name="phone"
              type="text"
              value={phoneNumber}
              onChange={e => setPhoneNumber(e.target.value)}
            />
          </div>
          <div className="inputGroup">
            <label htmlFor="email">Email:</label>
            <input
              id="email"
              name="email"
              type="text"
              value={email}
              onChange={e => setEmail(e.target.value)}
            />
          </div>
          <div className="inputGroup">
            <label htmlFor="address">Mailing Adress/Venmo Name/Paypal</label>
            <input
              size={60}
              name="address"
              id="address"
              type="text"
              value={address}
              onChange={e => setAddress(e.target.value)}
            />
          </div>
          <br />
          <button type="submit">Submit Changes</button>
        </form>
      </div>
    );
  };

  return (
    <div id="edit-user">
      <Link to={"/"}>Back to Timesheet</Link>
      <div>Person Id: {personId}</div>
      {getInputs()}
    </div>
  );
};
