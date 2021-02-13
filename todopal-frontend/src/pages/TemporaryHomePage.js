function TemporaryHomePage({ setState }) {
  function CreateClassroomPageOnClick() {
    console.log("hello");
    setState({ currentPage: "CreateClassroomPage" });
  }

  return (
    <>
      <div>the following are clickables</div>

      <button onClick={() => CreateClassroomPageOnClick()}>
        CreateClassroomPage
      </button>
    </>
  );
}

export default TemporaryHomePage;
