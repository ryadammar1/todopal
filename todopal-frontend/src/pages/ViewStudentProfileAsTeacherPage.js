import "../style/viewStudentTasksPage.css";
import "../style/createClassroomPage.css";
import TaskList from "../components/TaskList";

function ViewStudentProfileAsTeacherPage({mandatoryList, optionalList }) {
  return (

    <div id="view-student-tasks-page-wrapper">

      <div  id="name-subject-student-profile" className="row-create-class">
          <div className="name-subject">
            <div>Full Name : Johnny</div>
          </div>
        </div>

      <div id="tasks-wrapper">
        <TaskList tasks={mandatoryList} listName="Mandatory" isTeacher={false} />

        <TaskList tasks={optionalList} listName="Optional" isTeacher={false} />
      </div>
    </div>
  );
}

export default ViewStudentProfileAsTeacherPage;