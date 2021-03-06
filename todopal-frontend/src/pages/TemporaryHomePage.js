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

  function ViewStudenTaskOnClick() {
    setState({ currentPage: "ViewStudentTasksPage" });
  }

  function CreateStudentAccountOnClick() {
    setState({ currentPage: "CreateStudentAccountPage" });
  }

  function CreateTaskListOnClick() {
    setState({ currentPage: "CreateTaskListPage" });
  }
  
  function CreateCategoryOnClick() {
    setState({ currentPage: "CreateCategoryPage" });
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

      <div>View Studen Task</div>
      <button onClick={() => ViewStudenTaskOnClick()}>View Studen Task</button>

      <div>Join TodoPal As a Student</div>
      <button onClick={() => CreateStudentAccountOnClick()}>
        Create Student Account
      </button>

      <div>Make a task list</div>
      <button onClick={() => CreateTaskListOnClick()}>Create Task List</button>

      <div>Create Category</div>
      <button onClick={() => CreateCategoryOnClick()}>Create Category</button>
    </>
  );
}

export default TemporaryHomePage;
