import { useState } from "react";
import "../style/topbar.css";
import TaskCreater from "./TaskCreater";

function Topbar({ setState, name }) {
  const [isDisplay, setIsDisplay] = useState("none");

  function homeOnClickHandler() {
    setState({ currentPage: "TemporaryHomePage" });
  }
  return (
    <>
      <div id="topbar-wrapper">
        <button id="home-button" onClick={() => homeOnClickHandler()}>
          Todopal
        </button>

        <div id="small-button-wrapper">
          <button className="small-button">TO-DO</button>
          <button className="small-button">Race</button>
          <button className="small-button">Profile</button>
          <button className="small-button">Settings</button>

          <button id="profile-button" onClick={() => setIsDisplay("block")}>
            Hello {name}
          </button>
        </div>
      </div>
      <TaskCreater isDisplay={isDisplay} setIsDisplay={setIsDisplay} />
    </>
  );
}

export default Topbar;
