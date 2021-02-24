import "../style/viewAllTasksInClassroomPage.css";
import TaskList from "../components/TaskList";

function ViewAllTasksInClassroomPage({ mandatoryTasks, optionalTasks }) {
  return (
    <>
      <div id="view-all-task-in-class-page-wrapper">
        <TaskList listName="Mandatory" tasks={mandatoryTasks} />
        <TaskList listName="Optional" tasks={optionalTasks} />
      </div>
    </>
  );
}

export default ViewAllTasksInClassroomPage;
