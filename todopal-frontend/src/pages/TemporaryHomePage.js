function TemporaryHomePage({ setState }) {
  function CreateClassroomPageOnClick() {
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
