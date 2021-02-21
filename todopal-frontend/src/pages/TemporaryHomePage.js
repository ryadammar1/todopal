function TemporaryHomePage({ setState }) {
  function CreateClassroomPageOnClick() {
    setState({ currentPage: "CreateClassroomPage" });
  }

  function CreateTeacherAccountOnClick() {
    console.log("hello");
    setState({ currentPage: "CreateTeacherAccountPage" });
  }

  function CreateStudentAccountOnClick() {
    setState({ currentPage: "CreateStudentAccountPage" });
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

      <div>Join TodoPal As a Student</div>
      <button onClick={() => CreateStudentAccountOnClick()}>
        Create Student Account
      </button>

    </>
  );
}

export default TemporaryHomePage;
