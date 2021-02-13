import "../style/createClassroomPage.css";

function CreateClassroomPage() {
  return (
    <div id="create-class-page-wrapper">
      <section id="input-wrapper-create-class">
        <div id="message-create-class" className="row-create-class">
          Create Classroom
        </div>
        <div id="name-subject-create-class" className="row-create-class">
          <div className="name-subject">
            <div>Name</div>
            <input type="text"></input>
          </div>
          <div className="name-subject">
            <div>Subject</div>
            <input type="text"></input>
          </div>
        </div>

        <div className="row-create-class">
          <div>Class Icon URL</div>
          <input type="text"></input>
        </div>

        <div id="description-create-class" className="row-create-class">
          <div>Decription</div>
          <textarea id="input-description"></textarea>
        </div>
      </section>
    </div>
  );
}

export default CreateClassroomPage;
