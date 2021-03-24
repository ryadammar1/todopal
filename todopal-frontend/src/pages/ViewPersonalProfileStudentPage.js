import "../style/viewStudentTasksPage.css";
import "../style/createClassroomPage.css";
import TaskList from "../components/TaskList";

function ViewPersonalProfileStudentPage({ student, mandatoryList, optionalList }) {
  return (

    <div id="view-student-tasks-page-wrapper">

      <div  id="name-subject-student-profile" className="row-create-class">
          <div className="name-subject">
            <div>Full Name : {student.studentName}</div>
          </div>

          <div className="name-subject">
            <div>Email :  {student.studentEmail}</div>
          </div>
        </div>

        <div id="name-subject-student-profile" className="row-create-class">
          <div className="name-subject">
            <div>Password : {student.studentPassword}</div>
          </div>

          <div className="name-subject">
            <div>Bio : {student.studentBio}</div>
          </div>
        </div>

        <div id="name-subject-student-profile" className="row-create-class">
          <div className="name-subject">
            <div>Points : {student.studentPoints}</div>
          </div>

          <div className="name-subject">
            <div>Classroom : {student.studentClassroom}</div>
          </div>
        </div>

      <div id="tasks-wrapper">
        <TaskList tasks={mandatoryList} listName="Mandatory" isTeacher={false} />

        <TaskList tasks={optionalList} listName="Optional" isTeacher={false} />
      </div>
    </div>
  );
}

export default ViewPersonalProfileStudentPage;