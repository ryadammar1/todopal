function TemporaryHomePage({ setState }) {
  function CreateClassroomPageOnClick() {
    setState({ currentPage: "CreateClassroomPage" });
  }

  function CreateTeacherAccountOnClick() {
    setState({ currentPage: "CreateTeacherAccountPage" });
  }

  function ViewAllTasksInClassroomOnClick() {
    setState({ currentPage: "ViewAllTasksInClassroomPage" });
  }

  function CreateStudentAccountOnClick() {
    setState({ currentPage: "CreateStudentAccountPage" });
  }

  function CreateTaskListOnClick() {
    setState({ currentPage: "CreateTaskListPage" });
  }

  return (
    <>
      <div>the following are clickables</div>

      <button onClick={() => CreateClassroomPageOnClick()}>
        CreateClassroomPage
      </button>

      <div>Join TodoPal As a Teacher</div>
      <button onClick={() => CreateTeacherAccountOnClick()}>
        Create Teacher Account
      </button>

      <div>Join TodoPal As a Teacher</div>
      <button onClick={() => ViewAllTasksInClassroomOnClick()}>
        View All Tasks In Classroom
      </button>

      <div>Join TodoPal As a Student</div>
      <button onClick={() => CreateStudentAccountOnClick()}>
        Create Student Account
      </button>

      <div>Make a task list</div>
      <button onClick={() => CreateTaskListOnClick()}>Create Task List</button>
    </>
  );
}

export default TemporaryHomePage;
