import React from "react";

export interface IMenuGroupProps {
  groupLableTxt: string;
  children: any;
}

export const MenuGroup: React.FC<IMenuGroupProps> = (
  props: IMenuGroupProps
) => {
  return (
    <li className="menu-item menu-item-submenu">
      <button type="button" className="menu-btn">
        <i className="fa fa-users"></i>
        <span className="menu-text">{props.groupLableTxt}</span>
      </button>
      <ul className="menu">{props.children}</ul>
    </li>
  );
};
