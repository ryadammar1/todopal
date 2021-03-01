import "../style/createTaskListPage.css";

function CreateTaskListPage() {
  function submitButtonOnClick() {
    console.log("Submit button on click");
  }

  return (
    <div id="create-task-list-page-wrapper">
      <div id="create-task-list-inner">
        <input
          type="text"
          id="create-task-list-category"
          placeholder="Insert Category name hear."
        />

        <div id="create-task-list-checkbox-wrapper">
          <label>
            Is this a mandatory list?
            <input type="checkbox" id="create-list-check-box" />
          </label>
        </div>
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

export default CreateTaskListPage;
