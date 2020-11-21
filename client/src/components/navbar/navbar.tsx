import React, { useState, useEffect } from "react";

require("./navbar.scss");

export interface INavbarProps {}
export const Navbar: React.FC<INavbarProps> = (props: INavbarProps) => {
    return (
        <div className="nav-bar">
            <div className="logo">
                <h2>The Candy Factory - <small>Timesheet</small></h2>
                </div>
        </div>
    );
};
