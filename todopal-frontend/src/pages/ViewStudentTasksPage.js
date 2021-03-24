import "../style/viewStudentTasksPage.css";
import TaskList from "../components/TaskList";

function ViewStudentTaskPage({ mandatoryList, optionalList }) {
  return (
    <div id="view-student-tasks-page-wrapper">
      <div id="tasks-wrapper">
        <TaskList
          tasks={mandatoryList}
          listName="Mandatory"
          isTeacher={false}
        />

        <TaskList tasks={optionalList} listName="Optional" isTeacher={false} />
      </div>
    </div>
  );
}

export default ViewStudentTaskPage;
