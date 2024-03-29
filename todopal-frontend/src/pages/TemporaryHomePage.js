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

  function ViewStudentsInClassroomOnClick() {
    setState({ currentPage: "ViewStudentsInClassroomPage" });
  }
  
  function ViewTeacherProfileOnClick(isLoggedInAsTeacher) {
    if (isLoggedInAsTeacher) {
      setState({ currentPage: "ViewTeacherProfilePageAsTeacher" });
    } else {
      setState({ currentPage: "ViewTeacherProfilePageAsStudent" });
    }
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

      <div>View Students In Classroom</div>
      <button onClick={() => ViewStudentsInClassroomOnClick()}>View Students In Classroom</button>
      <div>View Student Profile As Teacher</div>
      <button onClick={() => ViewStudentProfileAsTeacherOnClick()}>View Student Profile As Teacher</button>
      <div>View Teacher Profile</div>
      <button onClick={() => ViewTeacherProfileOnClick(true)}>
        View Teacher Profile (as teacher)
      </button>

      <div>View Teacher Profile</div>
      <button onClick={() => ViewTeacherProfileOnClick(false)}>
        View Teacher Profile (as student)
      </button>
      
      <div>View Personal Profile as Student</div>
      <button onClick={() => ViewPersonalProfileStudentOnClick()}>View Personal Profile as Student</button>
    </>
  );
}

export default TemporaryHomePage;
