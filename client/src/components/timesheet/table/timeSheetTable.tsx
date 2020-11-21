import React, { useState, useEffect } from "react";

require("./table.scss");

export interface ITableProps {
  children?: any;
}

export const TimeSheetTable: React.FC<ITableProps> = (props: ITableProps) => {
  return (
    <table className="table">
      <tr>
        <th className="emp-info"><i className="fa fa-user" aria-hidden="true"></i> Employee</th>
        <th>Time-In</th>
        <th>Time-Out</th>
      </tr>
      {props.children}
    </table>
  );
};
