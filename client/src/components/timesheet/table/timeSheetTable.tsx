import React, { useState, useEffect } from "react";

require("./table.scss");

export interface ITableProps {
  children?: any;
}

export const TimeSheetTable: React.FC<ITableProps> = (props: ITableProps) => {
  return (
    <table className="table">
      <thead>
        <tr>
          <th className="emp-info">
            <i className="fa fa-user" aria-hidden="true"></i> Employee
          </th>
          <th>Time-In</th>
          <th>
            Time-Out
            <span className="tip">Double click checkmark</span>
          </th>
        </tr>
      </thead>
      <tbody>{props.children}</tbody>
    </table>
  );
};
