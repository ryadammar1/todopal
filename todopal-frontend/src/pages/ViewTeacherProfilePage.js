import { useState } from "react";
import "../style/viewTeacherProfilePage.css";

function ViewTeacherProfilePage({ teacher, isLoggedInAsTeacher }) {
  const maskedEmail =
    teacher.email[0] +
    "******" +
    teacher.email.slice(teacher.email.indexOf("@"));

  const [email, setEmail] = useState(maskedEmail);

  return (
    <>
      <div id="view-teacher-profile-page-wrapper">
        <div id="view-teacher-profile-content">
          <p>Name: {teacher.name}</p>
          <p
            onMouseEnter={() => setEmail(teacher.email)}
            onMouseLeave={() => setEmail(maskedEmail)}
          >
            Email: {email}
          </p>
          <p>Bio: {teacher.bio}</p>
          {isLoggedInAsTeacher ? (
            <div>
              <p>
                Password: <label id="password-label">{teacher.password}</label>{" "}
                (hover to see)
              </p>
              <p>Approval Code: {teacher.apprCode}</p>
              <button id="show-my-class-button">Show me my classes</button>
              <div id="view-teacher-profile-page-classes-wrapper">
                {teacher.classes.map((item) => (
                  <button className="class-button">{item.id}</button>
                ))}
              </div>
            </div>
          ) : (
            <div></div>
          )}
        </div>
      </div>
    </>
  );
}

export default ViewTeacherProfilePage;
