import React, { useState } from "react";
import classNames from "classnames";

require("./ContextMenu.scss");

export interface IMenuProps {
  children: any;
  allowedClasses?: string[];
}

export const ContextMenu: React.FC<IMenuProps> = (props: IMenuProps) => {
  const [showMenu, setShowMenu] = useState<boolean>(false);
  const [xAxis, setxAxis] = useState<number>(0);
  const [yAxis, setyAxis] = useState<number>(0);

  const onMouseDown = (e: any) => {
    setShowMenu(false);
    document.removeEventListener("click", onMouseDown);
  };

  document.addEventListener(
    "contextmenu",
    (e: any) => {
      if (clickInsideElement(e) || props.allowedClasses == null) {
        e.preventDefault();
        setxAxis(e.pageX);
        setyAxis(e.pageY);
        setShowMenu(true);
        document.addEventListener("click", onMouseDown, false);
      }
    },
    false
  );

  const clickInsideElement = (e: any): boolean => {
    let isValid: boolean = false;
    props.allowedClasses?.forEach(element => {
      if (clickInsideElementY(e, element)) {
        isValid = true;
      }
    });
    return isValid;
  };
  const clickInsideElementY = (e: any, className: String): boolean => {
    var el = e.srcElement || e.target;
    if (el.classList.contains(className)) {
      return el;
    } else {
      while ((el = el.parentNode)) {
        if (el.classList && el.classList.contains(className)) {
          return el;
        }
      }
    }
    return false;
  };

  return (
    <ul
      className={classNames("menu", { "menu-show": showMenu })}
      style={{ left: xAxis - 10 + "px", top: yAxis - 10 + "px" }}
    >
      {props.children}
    </ul>
  );
};
