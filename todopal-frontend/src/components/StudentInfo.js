import "../style/taskList.css";


function StudentInfo({ students }) {
  return (
    <div >
      {students.map((student, index) => {
        return (<div id="student-list-wrapper">
                    <h2>{student.fullName}</h2>
                    <h3>{student.email}</h3>
                    <h2>View Profile</h2>

                </div>)
      })}
    </div>
  );
}

export default StudentInfo;
