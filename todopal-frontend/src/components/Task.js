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
    // (Teacher Side) RESTapi find this task and set this task as closed
    console.log(taskInfo.taskName + " is passed");
    taskOnClickHandler();
  }

  function denyButtonOnClick() {
    // TODO implement
    // (Teacher Side) RESTapi find this task and set this task as not done
    console.log(taskInfo.taskName + " is denied");
    taskOnClickHandler();
  }

  function checkBoxOnClick() {
    // TODO implement
    // (Student Side) RESTapi find this task and set this task as done, so that teacher can review it
    console.log(taskInfo.taskName + " is done by student");
    taskOnClickHandler();
  }
  function deleteOnClick() {
    // TODO implement
    // RESTapi find this task and remove this task from the student task list
    console.log(taskInfo.taskName + " is deleted by student");
    taskOnClickHandler();
  }

  return (
    <>
      <div id="task-wrapper" onClick={() => taskOnClickHandler()}>
        {taskInfo.taskName}
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
