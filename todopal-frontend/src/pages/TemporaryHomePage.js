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
    </>
  );
}

export default TemporaryHomePage;
