import { useState } from "react";
import "../style/taskList.css";
import Task from "./Task";

function TaskList({ tasks, listName, isTeacher }) {
  return (
    <div id="task-list-wrapper">
      <h2>{listName}</h2>
      {tasks.map((task) => {
        return <Task taskInfo={task} isTeacher={isTeacher} />;
      })}
    </div>
  );
}

export default TaskList;
