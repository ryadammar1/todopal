import "../style/taskCreater.css";

const TaskCreater = ({ isDisplay, setIsDisplay }) => {
  return (
    <div id="task-creater-wrapper" style={{ display: isDisplay }}>
      <div id="task-creater-background" onClick={() => setIsDisplay("none")} />
      <div id="task-creater-model">
        <div id="task-creater-content">
          <div id="task-creater-message" className="row-item-task-creater">
            Create Task
          </div>

          <div id="task-creator-row">
            <div className="task-creator-column">
              <p className="row-item-task-creater small-text">Task Name</p>
              <input
                type="text"
                className="row-item-task-creater row-item-2"
              ></input>
            </div>
            <div className="task-creator-column">
              <p className="row-item-task-creater small-text">Task Tag</p>
              <input
                type="text"
                className="row-item-task-creater row-item-2"
              ></input>
            </div>
          </div>

          <p className="row-item-task-creater small-text">Category</p>
          <input
            type="text"
            className="row-item-task-creater row-item-1"
          ></input>

          <div id="task-creator-row">
            <div className="task-creator-column">
              <p className="row-item-task-creater small-text">start</p>
              <input
                type="date"
                className="row-item-task-creater row-item-3"
              ></input>
            </div>
            <div className="task-creator-column">
              <p className="row-item-task-creater small-text">due</p>
              <input
                type="date"
                className="row-item-task-creater row-item-3"
              ></input>
            </div>
            <div className="task-creator-column">
              <p className="row-item-task-creater small-text">isMandatory</p>
              <select id="is-mandatory-select" defaultValue="Mandatory">
                <option>Mandatory</option>
                <option>Optional</option>
              </select>
            </div>
          </div>
          <p className="row-item-task-creater small-text">Description</p>
          <textarea id="task-creater-description"></textarea>

          <button id="submit-button">SUBMIT</button>
        </div>
      </div>
    </div>
  );
};

export default TaskCreater;
