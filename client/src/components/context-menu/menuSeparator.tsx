import React from "react";

export interface IMenuSeparatorProps {}

export const MenuSeparator: React.FC<IMenuSeparatorProps> = (
  props: IMenuSeparatorProps
) => {
  return <li className="menu-separator"></li>;
};
