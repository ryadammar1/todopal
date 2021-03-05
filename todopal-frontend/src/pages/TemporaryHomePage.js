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

      <div>Join TodoPal As a Student</div>
      <button onClick={() => CreateStudentAccountOnClick()}>
        Create Student Account
      </button>

      <div>Create Category</div>
      <button onClick={() => CreateCategoryOnClick()}>Create Category</button>
    </>
  );
}

export default TemporaryHomePage;
