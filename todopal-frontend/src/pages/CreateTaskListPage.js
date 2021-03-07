import "../style/createTaskListPage.css";

function CreateClassroomPage() {
  let isMandatory = false;

  function submitButtonOnClick() {
    console.log(isMandatory);
  }

  return (
    <div id="create-task-list-page-wrapper">
      <div id="create-task-list-inner">
        <label id="create-task-list-page-name">Name</label>
        <input type="text" placeholder="Name of this list."></input>
      </div>

      <div id="create-task-list-checkbox-wrapper">
        <label>
          Is this a mandatory list?
          <input
            type="checkbox"
            id="create-list-check-box"
            onChange={(event) => (isMandatory = event.target.checked)}
          />
        </label>
      </div>

      <button
        id="create-task-list-button"
        onClick={() => submitButtonOnClick()}
      >
        SUBMIT
      </button>
    </div>
  );
}

export default CreateClassroomPage;
