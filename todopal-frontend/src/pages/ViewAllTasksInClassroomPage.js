import "../style/viewAllTasksInClassroomPage.css";
import TaskList from "../components/TaskList";

function ViewAllTasksInClassroomPage({ mandatoryTasks, optionalTasks }) {
  return (
    <>
      <div id="view-all-task-in-class-page-wrapper">
        <TaskList
          listName="Mandatory"
          tasks={mandatoryTasks}
          isTeacher={false}
        />
        <TaskList listName="Optional" tasks={optionalTasks} isTeacher={false} />
      </div>
    </>
  );
}

export default ViewAllTasksInClassroomPage;
