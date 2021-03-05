import "../style/createCategoryPage.css";

function CreateTaskListPage() {
  function submitButtonOnClick() {
    console.log("Submit button on click");
  }

  return (
    <div id="create-category-page-wrapper">
      <div id="create-category-inner">
        <input
          type="text"
          id="create-category-category"
          placeholder="Insert Category name hear."
        />

        <div id="create-category-checkbox-wrapper">
          <label>
            Is this a mandatory list?
            <input type="checkbox" id="create-list-check-box" />
          </label>
        </div>
      </div>
      <button id="create-category-button" onClick={() => submitButtonOnClick()}>
        SUBMIT
      </button>
    </div>
  );
}

export default CreateTaskListPage;
