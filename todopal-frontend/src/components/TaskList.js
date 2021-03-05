
import { useState } from "react";
import "../style/taskList.css";
import Task from "./Task";

function TaskList({ tasks, listName, isTeacher }) {
  return (
    <div id="task-list-wrapper">
      <h2>{listName}</h2>
      {tasks.map((task, index) => {
        return <Task taskInfo={task} isTeacher={isTeacher} key={index} />;

      })}
    </div>
  );
}

export default TaskList;
