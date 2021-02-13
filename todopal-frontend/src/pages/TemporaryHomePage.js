function TemporaryHomePage({ setState }) {
  function CreateClassroomPageOnClick() {
    console.log("hello");
    setState({ currentPage: "CreateClassroomPage" });
  }

  function CreateTeacherAccountOnClick() {
    console.log("hello");
    setState({ currentPage: "CreateTeacherAccountPage" });
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

    </>
  );
}

export default TemporaryHomePage;
