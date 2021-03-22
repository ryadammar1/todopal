import "../style/viewStudentTasksPage.css";
import StudentInfo from "../components/StudentInfo";

function ViewStudentsInClassroomPage({ studentList  }) {
  return (

    <div id="view-student-tasks-page-wrapper">

      <StudentInfo students={studentList} />
    </div>
  );
}

export default ViewStudentsInClassroomPage; 