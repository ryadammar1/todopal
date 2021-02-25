import { useState } from "react";
import FakeCheckBox from "./FakeCheckBox";
import "../style/task.css";

function Task({ taskInfo, isTeacher }) {
  const [isDisplay, setIsDisplay] = new useState("none");
  function taskOnClickHandler() {
    if (isDisplay === "none") {
      setIsDisplay("");
    } else {
      setIsDisplay("none");
    }
  }

  function passButtonOnClick() {
    // TODO implement
    taskOnClickHandler();
  }

  function denyButtonOnClick() {
    // TODO implement
    taskOnClickHandler();
  }

  function checkBoxOnClick() {
    // TODO implement
    taskOnClickHandler();
  }
  function deleteOnClick() {
    // TODO implement
    taskOnClickHandler();
  }

  return (
    <>
      <div id="task-wrapper">
        <lable id="task-name-lable" onClick={() => taskOnClickHandler()}>
          {taskInfo.taskName}
        </lable>
        {isTeacher ? (
          <></>
        ) : (
          <>
            <FakeCheckBox action={() => checkBoxOnClick()} />
            <div id="close-button" onClick={() => deleteOnClick()}>
              &#10006;
            </div>
          </>
        )}
      </div>

      <div id="task-info-wrapper" style={{ display: isDisplay }}>
        TAG: {taskInfo.tag}
        <br />
        CATEGORY: {taskInfo.category}
        <br />
        DESCRIPTION: {taskInfo.description}
        <br />
        START DATE: {taskInfo.startDate}
        <br />
        DUE DATE: {taskInfo.dueDate}
        <br />
        {isTeacher ? (
          <div>
            <button onClick={() => passButtonOnClick()} id="confirm-button">
              &#10004;
            </button>
            <button onClick={() => denyButtonOnClick()} id="deny-button">
              &#10006;
            </button>
          </div>
        ) : (
          <></>
        )}
      </div>
    </>
  );
}

export default Task;
