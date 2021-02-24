import { useState } from "react";
import "../style/task.css";

function Task({ taskInfo }) {
  const [isDisplay, setIsDisplay] = new useState("none");
  function taskOnClickHandler() {
    if (isDisplay === "none") {
      setIsDisplay("");
    } else {
      setIsDisplay("none");
    }
  }

  return (
    <>
      <div id="task-wrapper" onClick={() => taskOnClickHandler()}>
        {taskInfo.taskName}
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
      </div>
    </>
  );
}

export default Task;
